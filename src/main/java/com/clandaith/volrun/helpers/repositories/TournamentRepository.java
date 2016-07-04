package com.clandaith.volrun.helpers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.clandaith.volrun.entities.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {
	@Query("select t from Tournament t where t.userId= :id")
	public List<Tournament> findTournamentsByUserId(@Param("id") Integer id);

	@Query("select t from Tournament t where t.storeId= :id")
	public List<Tournament> findTournamentsByStoreId(@Param("id") Integer id);

}
