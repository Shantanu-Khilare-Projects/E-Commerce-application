package com.sunbeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.ProductDTO;
import com.sunbeam.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(dto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse("Error while adding product..."));
		}
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<?> getProductsByCategory(@RequestParam Long id) {
		return ResponseEntity.ok(productService.getProductsByCategory(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@RequestParam Long id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductDetails(@PathVariable Long id, @RequestBody ProductDTO dto) {
		return ResponseEntity.ok(productService.updateProductById(id, dto));
	}
	
	@PutMapping("delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.deleteProductById(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> viewProducts(){
		return ResponseEntity.ok(productService.viewProducts());
	}
	
	

}
