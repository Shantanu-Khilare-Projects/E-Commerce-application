package com.sunbeam.services;

import java.util.List;

import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.CategoryDTO;
import com.sunbeam.entities.Category;

public interface CategoryService {

	public ApiResponse addCategory(CategoryDTO dto);
	public List<Category> getAllCategories();
}
