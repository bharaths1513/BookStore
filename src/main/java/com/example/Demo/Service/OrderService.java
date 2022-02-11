package com.example.Demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.Dto.OrderServiceDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Exception.UserRegistrationException;
import com.example.Demo.Model.BookStoreModel;
import com.example.Demo.Model.OrderServiceModel;
import com.example.Demo.Model.UserRegistrationModel;
import com.example.Demo.Repository.BookStoreRepository;
import com.example.Demo.Repository.OrderRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Util.TokenUtil;

@Service
public class OrderService implements OrderServiceInterface {

	@Autowired
	private OrderRepository orderrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private BookStoreService service;
	
	@Autowired
	private BookStoreRepository bookrepo;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Override
	public OrderServiceModel PlaceOrder(String Token,int BookId, OrderServiceDto orderdto) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		Optional<BookStoreModel> model = bookrepo.findById(BookId);
		if(user.isPresent() && model.isPresent()) {
			OrderServiceModel order = new OrderServiceModel(user.get(),model.get(),orderdto);
			return orderrepo.save(order);

		} throw new UserRegistrationException("Invalid User Data or Book Data");
		
	}

	@Override
	public OrderServiceModel CancleOrder(String Token, int OrderId) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		if(user.isPresent()) {
			OrderServiceModel model = orderrepo.getById(OrderId);
			model.setCancel(true);
			return orderrepo.save(model);
			
		}else {
			throw new UserRegistrationException("Order Not Found");
		}
	}

	@Override
	public List<OrderServiceModel> FindAllOrders(String Token) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		boolean cancel=false;
		if(user.isPresent()) {
			return orderrepo.findActiveOrders(cancel);
		}else {
			throw new UserRegistrationException("Orders Not Found");
		}
	}

	@Override
	public List<OrderServiceModel> FindUserOrders(String Token) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		
		if(user.isPresent()) {
			List<OrderServiceModel> orders = orderrepo.findAllUserOrders(Id);
			return orders;
		}else {
			throw new UserRegistrationException("Orders Not Found");
		}
		
	}

}
