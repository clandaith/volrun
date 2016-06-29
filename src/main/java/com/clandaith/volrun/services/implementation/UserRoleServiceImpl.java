package com.clandaith.volrun.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clandaith.volrun.entities.UserRole;
import com.clandaith.volrun.helpers.repositories.UserRoleRepository;
import com.clandaith.volrun.services.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	private UserRoleRepository userRoleRepository;

	@Autowired
	public void setRepository(UserRoleRepository sr) {
		this.userRoleRepository = sr;
	}

	@Override
	public UserRole getUserRoleById(Integer id) {
		return userRoleRepository.findOne(id);
	}

	@Override
	public UserRole getUserRoleByUsername(String username) {
		return userRoleRepository.findUserRoleByUsername(username);
	}

	@Override
	public UserRole saveUserRole(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}
}
