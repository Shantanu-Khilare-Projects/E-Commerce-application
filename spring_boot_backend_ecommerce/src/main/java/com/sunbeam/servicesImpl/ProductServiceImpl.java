package com.sunbeam.servicesImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.CategoryDao;
import com.sunbeam.daos.ProductDao;
import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.dtos.ProductDTO;
import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;
import com.sunbeam.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public ApiResponse addProduct(ProductDTO dto) {
		Category category=categoryDao.findById(dto.getCategoryId())
									.orElseThrow(()->new RuntimeException("Category not found"));
		
		Product product = mapper.map(dto, Product.class);
		product.setCategory(category);
		product.setDeleted(false);
		productDao.save(product);
		return new ApiResponse("Product Added Successfully");
	}

	@Override
	public List<Product> getProductsByCategory(Long categoryId) {
		Category category=categoryDao.findById(categoryId)
				.orElseThrow(()->new RuntimeException("Category not found"));

		return productDao.findByCategory(category);
	}

	@Override
	public Product getProductById(Long id) {
		Product product=productDao.findById(id)
									.orElseThrow(()->new RuntimeException("Invalid product Id"));
		return product;
	}

	
	@Override
	public ApiResponse updateProductById(Long id,ProductDTO dto) {
		Product product=productDao.findById(id)
				.orElseThrow(()->new RuntimeException("Product not found"));
		
		Category category=categoryDao.findById(dto.getCategoryId())
				.orElseThrow(()->new RuntimeException("Category not found"));
		
		mapper.map(dto, product);
		
		product.setCategory(category);
		
		productDao.save(product);
	
		return new ApiResponse("updation successfull!!!");
	}

	@Override
	public ApiResponse deleteProductById(Long id) {
		Product product=productDao.findById(id)
				.orElseThrow(()->new RuntimeException("Product not found"));
		
		product.setDeleted(true);
		productDao.save(product);
		return new ApiResponse("Product "+ product.getName()+" deleted successful!");
	}

	@Override
	public List<Product> viewProducts() {
		return productDao.findByDeleted(false);
	}

}
