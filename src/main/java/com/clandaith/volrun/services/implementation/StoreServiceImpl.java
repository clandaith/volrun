package com.clandaith.volrun.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.Store;
import com.clandaith.volrun.helpers.repositories.StoreRepository;
import com.clandaith.volrun.services.StoreService;
import com.google.common.collect.Lists;

@Service
public class StoreServiceImpl implements StoreService {
	private StoreRepository storeRepository;

	public void setRepository(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Override
	public List<Store> getAll() {
		return Lists.newArrayList(storeRepository.findAll());
	}

	@Override
	public Store getStore(Integer id) {
		return storeRepository.findOne(id);
	}

	@Override
	public Store saveStore(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public Store updateStore(Store store) {
		return storeRepository.save(store);
	}

	@Override
	public void deleteStore(Store store) {
		storeRepository.delete(store);

	}
}
