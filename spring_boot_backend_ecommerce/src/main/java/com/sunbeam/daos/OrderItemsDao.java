package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.OrderItem;

public interface OrderItemsDao extends JpaRepository<OrderItem, Long> {

}
