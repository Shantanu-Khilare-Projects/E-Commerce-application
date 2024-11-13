package com.sunbeam.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.OrderDao;
import com.sunbeam.daos.OrderItemsDao;
import com.sunbeam.daos.ProductDao;
import com.sunbeam.daos.UserDao;
import com.sunbeam.dtos.ApiResponse;
import com.sunbeam.entities.Order;
import com.sunbeam.entities.OrderItem;
import com.sunbeam.entities.Product;
import com.sunbeam.entities.Status;
import com.sunbeam.entities.User;
import com.sunbeam.services.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemsDao orderItemsDao;
	
	@Override
	public ApiResponse buyProduct(Long productId, int quantity, Long userId) {
		User user=userDao.findById(userId)
							.orElseThrow(()->new RuntimeException("User not found..."));
		
		Product product=productDao.findById(productId)
				.orElseThrow(()->new RuntimeException("Product not found..."));
		
		if(product.getInventory()>=quantity) {
			Order order=new Order();
			order.setStatus(Status.ORDERED);
			order.setTotalAmount(product.getPrice()*quantity);
			order.setUser(user);
			
			orderDao.save(order);
			
			OrderItem orderItem=new OrderItem();
			orderItem.setProduct(product);
			orderItem.setQuantity(quantity);
			orderItem.setOrder(order);
			
			orderItemsDao.save(orderItem);
			
			product.setInventory(product.getInventory()-quantity);
			productDao.save(product);
			
			return new ApiResponse("Order Placed. Please check your mail for invoice!!!");
		}
		else {
			return new ApiResponse("Sorry inventory does not have enough products...");
		}				
	}

	@Override
	public ApiResponse cancelOrder(Long orderId) {
		Order order=orderDao.findById(orderId)
						.orElseThrow(()->new RuntimeException("Order does not exist..."));
		if(order.getStatus()==Status.ORDERED
				|| order.getStatus()==Status.DISPATHCHED
				|| order.getStatus()==Status.SHIPPED) {
			order.setStatus(Status.CANCELED);
			orderDao.save(order);
			return new ApiResponse("Order Canceled. please check email for cancelation fees...");
		}
		else if(order.getStatus()==Status.CANCELED)
			return new ApiResponse("This order is already canceled");
		else
			return new ApiResponse("Sorry your order is already delivered, contact customer service for further quarruies");
	}

	@Override
	public List<Order> orderHistory(Long userId) {
		User user=userDao.findById(userId)
				.orElseThrow(()->new RuntimeException("User not found..."));

		List<Status> statuses=new ArrayList<>();
		statuses.add(Status.CANCELED);
		
		return orderDao.findByUserAndStatusNotIn(user, statuses);
	}

}
