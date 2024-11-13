package com.sunbeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.LoginDTO;
import com.sunbeam.dtos.RegisterDTO;
import com.sunbeam.services.UserService;
import com.sunbeam.servicesImpl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO dto){
		
		return ResponseEntity.ok(userService.login(dto));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(dto));
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Email already exists!"));
		}
		
	}
	
	
}
