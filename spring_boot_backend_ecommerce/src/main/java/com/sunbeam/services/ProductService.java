package com.sunbeam.services;

import java.util.List;

import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.ProductDTO;
import com.sunbeam.entities.Product;

public interface ProductService {
	
	public ApiResponse addProduct(ProductDTO dto);
	public List<Product> getProductsByCategory(Long categoryId);
	public Product getProductById(Long id);
	public ApiResponse updateProductById(Long id,ProductDTO dto);
	public ApiResponse deleteProductById(Long id);
	public List<Product> viewProducts();
	
}
