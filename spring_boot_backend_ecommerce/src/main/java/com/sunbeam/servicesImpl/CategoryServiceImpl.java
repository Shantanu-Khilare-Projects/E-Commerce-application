package com.sunbeam.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.CategoryDao;
import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.CategoryDTO;
import com.sunbeam.entities.Category;
import com.sunbeam.services.CategoryService;

@Service
@Transactional

public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addCategory(CategoryDTO dto) {
		Category category= mapper.map(dto, Category.class);
		categoryDao.save(category);
		return new ApiResponse("Category added Successfully!!!");
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAll();
	}

}
