package com.clandaith.volrun.services;

import java.util.List;

import com.clandaith.volrun.entities.Store;

public interface StoreService {

	public List<Store> getAll();

	public Store getStore(Integer id);

	public Store saveStore(Store store);

	public Store updateStore(Store store);

	public void deleteStore(Store store);
}
