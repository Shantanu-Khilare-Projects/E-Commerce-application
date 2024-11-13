package com.sunbeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.services.OrderService;




@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/buy/{productId}/{quantity}/{userId}")
	public ResponseEntity<?> buyProduct(@RequestParam Long productId, @RequestParam int quantity, @RequestParam Long userId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.buyProduct(productId, quantity, userId));
	}
	@PutMapping("cancel/{id}")
	public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.cancelOrder(id));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> orderHistory(@RequestParam Long userId) {
		return ResponseEntity.ok(orderService.orderHistory(userId));
	}
	
}
