package com.sunbeam.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.UserDao;
import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.LoginDTO;
import com.sunbeam.dtos.RegisterDTO;
import com.sunbeam.entities.User;
import com.sunbeam.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User login(LoginDTO dto) {
		User user = userDao.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
							.orElseThrow(()->new RuntimeException("User Not Found"));
		return user;		
	}

	@Override
	public ApiResponse registerUser(RegisterDTO dto) {
		User user= mapper.map(dto, User.class);
		
		userDao.save(user);
		
		return new ApiResponse("User Added Successfully!!!");
	}

}
