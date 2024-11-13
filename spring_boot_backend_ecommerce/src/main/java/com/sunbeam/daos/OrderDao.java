package com.sunbeam.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Order;
import com.sunbeam.entities.Status;
import com.sunbeam.entities.User;

import java.util.List;


public interface OrderDao extends JpaRepository<Order, Long> {
	List<Order> findByUserAndStatusNotIn(User user,List<Status> statuses);
}
