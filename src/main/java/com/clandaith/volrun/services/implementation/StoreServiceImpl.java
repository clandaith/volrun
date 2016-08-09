package com.clandaith.volrun.services.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.Store;
import com.clandaith.volrun.helpers.AddressFormatter;
import com.clandaith.volrun.helpers.DistanceHandler;
import com.clandaith.volrun.helpers.repositories.StoreRepository;
import com.clandaith.volrun.services.StoreService;
import com.google.common.collect.Lists;
import com.google.maps.model.LatLng;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
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
		return saveUpdate(store);
	}

	@Override
	public Store updateStore(Store store) {
		return saveUpdate(store);
	}

	private Store saveUpdate(Store store) {
		LatLng latLong = DistanceHandler.getLatLng(AddressFormatter.formatStoreAddress(store));

		if (latLong != null) {
			store.setLatitude(new BigDecimal(latLong.lat));
			store.setLongitude(new BigDecimal(latLong.lng));
		} else {
			store.setLatitude(BigDecimal.ZERO);
			store.setLongitude(BigDecimal.ZERO);
		}

		return storeRepository.save(store);
	}

	@Override
	public void deleteStore(Store store) {
		storeRepository.delete(store);
	}
}
