package com.sunbeam.dtos;

import lombok.Data;

@Data
public class RegisterDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
