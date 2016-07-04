package com.clandaith.volrun.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.Demo;
import com.clandaith.volrun.helpers.repositories.DemoRepository;
import com.clandaith.volrun.services.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	private DemoRepository demoRepository;

	@Autowired
	public void setRepository(DemoRepository sr) {
		this.demoRepository = sr;
	}

	@Override
	public Demo saveDemo(Demo demo) {
		return demoRepository.save(demo);
	}

	@Override
	public Demo getDemo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Demo> getAllDemos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Demo> getAllDemosByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Demo> getAllDemosByStore(Integer storeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
