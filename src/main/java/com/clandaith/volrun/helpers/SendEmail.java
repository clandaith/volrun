package com.clandaith.volrun.helpers;

import java.util.List;

import org.apache.log4j.Logger;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class SendEmail {
	private static final Logger LOGGER = Logger.getLogger(SendEmail.class);

	static final String FROM = "troy@801dev.com"; // Replace with your "From" address. This address must be verified.
	static final String TO = "troy@801dev.com"; // Replace with a "To" address. If your account is still in the
																							// sandbox, this address must be verified.
	static final String BODY = "This email was sent through Amazon SES by using the AWS SDK for Java.";
	static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";

	public static void sendEmail(List<String> emailAddresses, String htmlMessage, String textMessage, String fromAddress) {
		Destination destination = new Destination().withToAddresses(new String[] { TO });

		Content subject = new Content().withData(SUBJECT);
		Content textBody = new Content().withData(BODY);
		Content htmlBody = new Content().withData(htmlMessage);
		Body body = new Body().withText(textBody).withHtml(htmlBody);

		Message message = new Message().withSubject(subject).withBody(body);

		SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

		try {
			LOGGER.info("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(new EnvironmentVariableCredentialsProvider());
			client.setRegion(Region.getRegion(Regions.US_WEST_2));

			client.sendEmail(request);
			LOGGER.info("Email sent!");
		} catch (Exception ex) {
			LOGGER.error("Error message: " + ex.getMessage(), ex);
		}
	}
}
