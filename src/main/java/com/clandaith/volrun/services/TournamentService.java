package com.clandaith.volrun.services;

import java.util.List;

import com.clandaith.volrun.entities.Tournament;

public interface TournamentService {
	public Tournament saveTournament(Tournament tournament);

	public Tournament getTournament(Integer id);

	public List<Tournament> getAllTournaments();

	public List<Tournament> getAllTournamentsByUser(Integer userId);

	public List<Tournament> getCompletedTournamentsByUser(Integer userId);

	public List<Tournament> getUncompletedTournamentsByUser(Integer userId);

	public List<Tournament> getAllTournamentsByStore(Integer storeId);
}
