package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity{

	@ManyToOne
	@JoinColumn(name = "order_id")
	
	private Order order;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "Qty")
	private int quantity;
}
