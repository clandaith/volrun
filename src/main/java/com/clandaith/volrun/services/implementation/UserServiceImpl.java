package com.clandaith.volrun.services.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.helpers.AddressFormatter;
import com.clandaith.volrun.helpers.DistanceHandler;
import com.clandaith.volrun.helpers.repositories.UserRepository;
import com.clandaith.volrun.services.UserService;
import com.google.common.collect.Lists;
import com.google.maps.model.LatLng;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

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
	public User saveUser(User user) {
		LatLng latLong = DistanceHandler.getLatLng(AddressFormatter.formatUserAddress(user));

		if (latLong != null) {
			user.setLatitude(new BigDecimal(latLong.lat));
			user.setLongitude(new BigDecimal(latLong.lng));
		} else {
			user.setLatitude(BigDecimal.ZERO);
			user.setLongitude(BigDecimal.ZERO);
		}

		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.delete(id);
	}
}
