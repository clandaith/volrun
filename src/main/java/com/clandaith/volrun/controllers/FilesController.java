package com.clandaith.volrun.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.clandaith.volrun.entities.File;
import com.clandaith.volrun.services.FileService;

@Controller
@RequestMapping("/files")
public class FilesController {
	private static final Logger LOGGER = LogManager.getLogger(FilesController.class);

	@Autowired
	private FileService fileService;

	@RequestMapping("/list")
	public String listFiles(Model model) {
		LOGGER.info("listFiles");

		model.addAttribute("files", fileService.getAll());

		return "files/index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/download/{id}")
	public void getFile(@PathVariable Integer id, HttpServletResponse response) {
		File fileToDownload = fileService.getFile(id);

		if (fileToDownload != null) {
			try {
				AmazonS3 s3client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());

				S3Object s3Object = s3client.getObject(new GetObjectRequest(System.getenv("S3_BUCKET_NAME"), "DemoCompany/"
								+ fileToDownload.getFileName()));

				response.addHeader("Content-disposition", "attachment;filename=" + fileToDownload.getFileName());
				response.setContentType("txt/plain");

				IOUtils.copy(s3Object.getObjectContent(), response.getOutputStream());
				response.flushBuffer();
			} catch (Exception e) {
				LOGGER.error("Error: ", e);
			}
		} else {
			LOGGER.error("File is null");
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
	public String deleteFile(@PathVariable Integer id, Model model) {
		File fileToDelete = fileService.getFile(id);

		if (fileToDelete != null) {
			LOGGER.info("File to delete: " + fileToDelete.getFileName());

			try {
				AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(System.getenv("AWS_ACCESS_KEY_ID"),
								System.getenv("AWS_SECRET_ACCESS_KEY")));

				s3client.deleteObject(new DeleteObjectRequest(System.getenv("S3_BUCKET_NAME"), "DemoCompany/"
								+ fileToDelete.getFileName()));
				fileService.deleteFile(id);

				model.addAttribute("files", fileService.getAll());
				model.addAttribute("message", "You've successfully deleted " + fileToDelete.getFileName() + "!");
				LOGGER.info("File deleted");
			} catch (Exception e) {
				LOGGER.error("Error: ", e);
				model.addAttribute("message", "There was a problem deleting file " + fileToDelete.getFileName() + ".  Please try again.");
			}
		} else {
			LOGGER.info("File not found");
			model.addAttribute("message", "That file wasn't found.  Please try again.");
		}

		return "files/index";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String handleFileUpload(@RequestParam("file") MultipartFile uploadedFile,
					@RequestParam("description") String description, RedirectAttributes redirectAttributes) {

		LOGGER.info("In save: " + uploadedFile.getOriginalFilename() + " :: " + description);

		if (!uploadedFile.isEmpty()) {
			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(System.getenv("AWS_ACCESS_KEY_ID"),
							System.getenv("AWS_SECRET_ACCESS_KEY")));
			try {
				LOGGER.info("Uploading a new object to S3 from a file");

				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(uploadedFile.getSize());
				metadata.setLastModified(new Date());

				s3client.putObject(new PutObjectRequest(System.getenv("S3_BUCKET_NAME"), "DemoCompany/"
								+ uploadedFile.getOriginalFilename(), uploadedFile.getInputStream(), metadata));

				File file = new File();
				file.setFileSize(uploadedFile.getSize());
				file.setDateAdded(new Date());
				file.setDescription(description);
				file.setFileName(uploadedFile.getOriginalFilename());
				file.setFilePath("DemoCompany/" + file.getFileName());
				fileService.saveFile(file);

				redirectAttributes.addFlashAttribute("message", "You've successfully uploaded " + uploadedFile.getOriginalFilename()
								+ "!");
			} catch (AmazonServiceException ase) {
				LOGGER.error("Caught an AmazonServiceException, which means your request made it "
								+ "to Amazon S3, but was rejected with an error response for some reason.");
				LOGGER.error("Error Message:    " + ase.getMessage());
				LOGGER.error("HTTP Status Code: " + ase.getStatusCode());
				LOGGER.error("AWS Error Code:   " + ase.getErrorCode());
				LOGGER.error("Error Type:       " + ase.getErrorType());
				LOGGER.error("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace) {
				LOGGER.error("Caught an AmazonClientException, which means the client encountered "
								+ "an internal error while trying to communicate with S3, such as not being able to access the network.");
				LOGGER.error("Error Message: " + ace.getMessage());
			} catch (IOException e) {
				LOGGER.error("Error: ", e);
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "Failed to upload " + uploadedFile.getOriginalFilename()
							+ " because it was empty");
		}

		return "redirect:/files/list";
	}
}
