package com.sunbeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dtos.CategoryDTO;
import com.sunbeam.services.CategoryService;



@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping		
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(dto));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(new RuntimeException("Error while adding Category"));
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	
	
	
}
