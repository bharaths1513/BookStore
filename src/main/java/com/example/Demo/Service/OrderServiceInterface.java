package com.example.Demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Demo.Dto.OrderServiceDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.OrderServiceModel;

@Service
public interface OrderServiceInterface {

	OrderServiceModel PlaceOrder(String Token ,int BookId,OrderServiceDto orderdto);
	OrderServiceModel CancleOrder(String Token,int OrderId);
	List<OrderServiceModel> FindAllOrders(String Token);
	List<OrderServiceModel> FindUserOrders(String Token);
}
