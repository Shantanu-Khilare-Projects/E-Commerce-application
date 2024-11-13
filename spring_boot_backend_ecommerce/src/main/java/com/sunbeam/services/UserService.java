package com.sunbeam.services;

import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.LoginDTO;
import com.sunbeam.dtos.RegisterDTO;
import com.sunbeam.entities.User;

public interface UserService {

	public User login(LoginDTO dto);
	public ApiResponse registerUser(RegisterDTO dto);
}
