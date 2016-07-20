package com.clandaith.volrun.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.Tournament;
import com.clandaith.volrun.helpers.repositories.TournamentRepository;
import com.clandaith.volrun.services.TournamentService;
import com.google.common.collect.Lists;

@Service
public class TournamentServiceImpl implements TournamentService {
	private TournamentRepository tournamentRepository;

	@Autowired
	public void setRepository(TournamentRepository tournamentRepository) {
		this.tournamentRepository = tournamentRepository;
	}

	@Override
	public Tournament saveTournament(Tournament tournament) {
		return tournamentRepository.save(tournament);
	}

	@Override
	public Tournament getTournament(Integer id) {
		return tournamentRepository.findOne(id);
	}

	@Override
	public List<Tournament> getAllTournaments() {
		return Lists.newArrayList(tournamentRepository.findAll());
	}

	@Override
	public List<Tournament> getAllTournamentsByUser(Integer userId) {
		return Lists.newArrayList(tournamentRepository.findAllTournamentsByUserId(userId));
	}

	@Override
	public List<Tournament> getCompletedTournamentsByUser(Integer userId) {
		return Lists.newArrayList(tournamentRepository.findCompletedTournamentsByUserId(userId));
	}

	@Override
	public List<Tournament> getUncompletedTournamentsByUser(Integer userId) {
		return Lists.newArrayList(tournamentRepository.findUncompletedTournamentsByUserId(userId));
	}

	@Override
	public List<Tournament> getAllTournamentsByStore(Integer storeId) {
		return Lists.newArrayList(tournamentRepository.findTournamentsByStoreId(storeId));
	}
}
