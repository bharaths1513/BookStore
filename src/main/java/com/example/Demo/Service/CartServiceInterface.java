package com.example.Demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Demo.Dto.CartDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.CartModel;

@Service
public interface CartServiceInterface {

	CartModel AddCart(String Token, int BookId, CartDto dto);
	ResponseDto RemoveFromCart(String Token,int CartId);
	List<CartModel> FindAllInCart(String Token);
	List<CartModel> FindAllCarts(String Token);

	CartModel UpdateQuantity(String Token,int CartId,int Quantity);
	
}
