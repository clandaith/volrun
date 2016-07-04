package com.clandaith.volrun.helpers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.clandaith.volrun.entities.File;

public interface FileRepository extends CrudRepository<File, Integer> {

}
