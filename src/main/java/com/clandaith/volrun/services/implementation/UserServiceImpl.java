package com.clandaith.volrun.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.helpers.AddressFormatter;
import com.clandaith.volrun.helpers.DistanceHandler;
import com.clandaith.volrun.helpers.repositories.UserRepository;
import com.clandaith.volrun.services.UserService;
import com.google.common.collect.Lists;
import com.google.maps.model.LatLng;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public void setRepository(UserRepository sr) {
		this.userRepository = sr;
	}

	@Override
	public List<User> getAllUsers() {
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		LatLng latLong = DistanceHandler.getLatLng(AddressFormatter.formatUserAddress(user));

		if (latLong != null) {
			user.setLatitude(latLong.lat);
			user.setLatitude(latLong.lng);
		} else {
			user.setLatitude(0D);
			user.setLongitude(0D);
		}

		return userRepository.save(user);
	}
}
