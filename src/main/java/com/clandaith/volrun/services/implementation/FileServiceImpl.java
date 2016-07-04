package com.clandaith.volrun.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.helpers.repositories.FileRepository;
import com.clandaith.volrun.services.FileService;

@Service
public class FileServiceImpl implements FileService {
	private FileRepository fileRepository;

	@Autowired
	public void setRepository(FileRepository fr) {
		this.fileRepository = fr;
	}
}
