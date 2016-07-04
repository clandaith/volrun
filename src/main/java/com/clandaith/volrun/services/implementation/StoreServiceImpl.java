package com.clandaith.volrun.services.implementation;

import org.springframework.stereotype.Service;

import com.clandaith.volrun.helpers.repositories.StoreRepository;
import com.clandaith.volrun.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	private StoreRepository storeRepository;

	public void setRepository(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
}
