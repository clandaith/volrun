package com.clandaith.volrun.helpers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.clandaith.volrun.entities.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {
	@Query("select t from Tournament t where t.userId= :id")
	public List<Tournament> findAllTournamentsByUserId(@Param("id") Integer id);

	@Query("select t from Tournament t where t.userId= :id and t.completed= true")
	public List<Tournament> findCompletedTournamentsByUserId(@Param("id") Integer id);

	@Query("select t from Tournament t where t.userId= :id and t.completed= false")
	public List<Tournament> findUncompletedTournamentsByUserId(@Param("id") Integer id);

	@Query("select t from Tournament t where t.completed= false")
	public List<Tournament> findAllUncompletedTournaments();

	@Query("select t from Tournament t where t.completed= true")
	public List<Tournament> findAllCompletedTournaments();

	@Query("select t from Tournament t where t.storeId= :id")
	public List<Tournament> findTournamentsByStoreId(@Param("id") Integer id);
}
