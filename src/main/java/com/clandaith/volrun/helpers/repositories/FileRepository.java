package com.clandaith.volrun.helpers.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clandaith.volrun.entities.File;

@Repository
public interface FileRepository extends CrudRepository<File, Integer> {

}
