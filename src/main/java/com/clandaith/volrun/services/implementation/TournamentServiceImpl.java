package com.clandaith.volrun.services.implementation;

import org.springframework.stereotype.Service;

import com.clandaith.volrun.helpers.repositories.TournamentRepository;
import com.clandaith.volrun.services.TournamentService;

@Service
public class TournamentServiceImpl implements TournamentService {
	private TournamentRepository tournamentRepository;

	public void setRepository(TournamentRepository tournamentRepository) {
		this.tournamentRepository = tournamentRepository;
	}
}
