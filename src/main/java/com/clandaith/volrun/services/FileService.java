package com.clandaith.volrun.services;

import java.util.List;

import com.clandaith.volrun.entities.File;

public interface FileService {

	List<File> getAll();

	File getFile(Integer id);

	File saveFile(File file);
}
