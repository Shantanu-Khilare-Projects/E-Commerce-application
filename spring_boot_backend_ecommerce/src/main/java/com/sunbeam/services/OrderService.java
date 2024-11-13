package com.sunbeam.services;

import java.util.List;

import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.entities.Order;

public interface OrderService {

	public ApiResponse buyProduct(Long productId, int quantity, Long userId);
	public ApiResponse cancelOrder(Long orderId);
	public List<Order> orderHistory(Long userId); 
}
