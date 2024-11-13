package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.User;
import java.util.List;
import java.util.Optional;


public interface UserDao extends JpaRepository<User, Long>{
	public Optional<User> findByEmailAndPassword(String email, String password);
	
}
