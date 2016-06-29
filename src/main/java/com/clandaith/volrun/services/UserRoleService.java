package com.clandaith.volrun.services;

import com.clandaith.volrun.entities.UserRole;

public interface UserRoleService {

	UserRole getUserRoleById(Integer id);

	UserRole getUserRoleByUsername(String username);

	UserRole saveUserRole(UserRole userRole);

}
