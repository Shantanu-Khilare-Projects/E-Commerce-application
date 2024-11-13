package com.sunbeam.dtos;

import lombok.Data;

@Data
public class ProductDTO {
	
	private String name;
	private double price;
	private int inventory;
	private Long categoryId;
	
}
