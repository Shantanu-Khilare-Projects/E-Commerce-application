package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "prices")
	private double price;
	
	@Column(name = "inventory")
	private int inventory;
	
	@Column(name = "deleted_status")
	private boolean deleted;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
