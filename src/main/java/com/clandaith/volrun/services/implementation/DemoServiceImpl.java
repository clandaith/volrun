package com.clandaith.volrun.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.Demo;
import com.clandaith.volrun.helpers.repositories.DemoRepository;
import com.clandaith.volrun.services.DemoService;
import com.google.common.collect.Lists;

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
		return demoRepository.findOne(id);
	}

	@Override
	public List<Demo> getAllDemos() {
		return Lists.newArrayList(demoRepository.findAll());
	}

	@Override
	public List<Demo> getAllDemosByUser(Integer userId) {
		return demoRepository.findAllDemosByUserId(userId);
	}

	@Override
	public List<Demo> getAllDemosByStore(Integer storeId) {
		return null;
	}

	@Override
	public List<Demo> getCompletedDemosByUser(Integer userId) {
		return demoRepository.findCompletedDemosByUserId(userId);
	}

	@Override
	public List<Demo> getUncompletedDemosByUser(Integer userId) {
		return demoRepository.findUncompletedDemosByUserId(userId);
	}
}
