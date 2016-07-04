package com.clandaith.volrun.services;

import java.util.List;

import com.clandaith.volrun.entities.Demo;

public interface DemoService {
	public Demo saveDemo(Demo demo);

	public Demo getDemo(Integer id);

	public List<Demo> getAllDemos();

	public List<Demo> getAllDemosByUser(Integer userId);

	public List<Demo> getAllDemosByStore(Integer storeId);
}
