package com.clandaith.volrun.helpers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.clandaith.volrun.entities.Demo;

public interface DemoRepository extends CrudRepository<Demo, Integer> {
	@Query("select d from Demo d where d.userId= :id")
	public List<Demo> findAllDemosByUserId(@Param("id") Integer id);

	@Query("select d from Demo d where d.userId= :id")
	public List<Demo> findCompletedDemosByUserId(@Param("id") Integer id);

	@Query("select d from Demo d where d.userId= :id")
	public List<Demo> findUncompletedDemosByUserId(@Param("id") Integer id);

	@Query("select d from Demo d where d.storeId= :id")
	public List<Demo> findDemosByStoreId(@Param("id") Integer id);

}
