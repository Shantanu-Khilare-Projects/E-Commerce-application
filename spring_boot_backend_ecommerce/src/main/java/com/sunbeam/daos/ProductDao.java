package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Category;
import com.sunbeam.entities.Product;
import java.util.List;


public interface ProductDao extends JpaRepository<Product, Long>{

	public List<Product> findByCategory(Category category);
	public List<Product> findByDeleted(boolean deleted);
}
