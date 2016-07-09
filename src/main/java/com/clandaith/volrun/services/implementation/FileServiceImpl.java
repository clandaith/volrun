package com.clandaith.volrun.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.File;
import com.clandaith.volrun.helpers.repositories.FileRepository;
import com.clandaith.volrun.services.FileService;
import com.google.common.collect.Lists;

@Service
public class FileServiceImpl implements FileService {
	private FileRepository fileRepository;

	@Autowired
	public void setRepository(FileRepository fr) {
		this.fileRepository = fr;
	}

	@Override
	public List<File> getAll() {
		return Lists.newArrayList(fileRepository.findAll());
	}
}