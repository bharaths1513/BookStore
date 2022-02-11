package com.example.Demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.Dto.OrderServiceDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.OrderServiceModel;
import com.example.Demo.Service.OrderService;

@RestController
@RequestMapping("/Order")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@PostMapping("/PlaceOrder/{BookId}")
	public ResponseEntity<ResponseDto> PlaceOrder(@RequestHeader(name="Token")String Token, @PathVariable("BookId")int BookId,@RequestBody OrderServiceDto Orderdto){
		OrderServiceModel order = service.PlaceOrder(Token, BookId, Orderdto);
		ResponseDto response = new ResponseDto("Order Placed Successfully",order);
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@PutMapping("/CancelOrder/{OrderId}")
	public ResponseEntity<ResponseDto> CancelOrder(@RequestHeader(name="Token")String Token,@PathVariable("OrderId")int OrderId){
		OrderServiceModel model = service.CancleOrder(Token, OrderId);
		ResponseDto dto = new ResponseDto("Order Cancelled Successfully",model);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/GetOrders")
	public ResponseEntity<List<OrderServiceModel>> FindAllOrders(@RequestHeader(name="Token")String Token){
		List<OrderServiceModel> model = service.FindAllOrders(Token);
		return new ResponseEntity<List<OrderServiceModel>>(model,HttpStatus.OK);
	}
	
	@GetMapping("/GetUserOrders")
	public ResponseEntity<List<OrderServiceModel>>findUserOrders(@RequestHeader(name="Token")String Token){
		List<OrderServiceModel> model= service.FindUserOrders(Token);
		return new ResponseEntity<List<OrderServiceModel>>(model,HttpStatus.OK);
	}
	
}
