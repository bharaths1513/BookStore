package com.example.Demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.Dto.CartDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.CartModel;
import com.example.Demo.Service.CartService;

@RestController
@RequestMapping("/CartService")
public class CartController {
	
	@Autowired
	private CartService service;
	
	
	@PostMapping("/AddToCart/{BookId}")
	public ResponseEntity<ResponseDto> AddCart(@RequestHeader(name="Token") String Token,@PathVariable("BookId")int BookId,@RequestBody CartDto dto){
	
		CartModel model = service.AddCart(Token, BookId, dto);
		ResponseDto resdto = new ResponseDto("Book Added To cart Successfully",model);
		return new ResponseEntity<ResponseDto>(resdto,HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteFromcart")
	public ResponseEntity<ResponseDto> Deletefromcart(@RequestHeader(name="Token") String Token,@RequestParam("CartId")int CartId){
		ResponseDto dto = service.RemoveFromCart(Token, CartId);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/FindAllBooks")
	public ResponseEntity<List<CartModel>> FindAllFromCart(@RequestHeader(name="Token") String Token){
		List<CartModel> model = service.FindAllInCart(Token);
		return new ResponseEntity<List<CartModel>>(model,HttpStatus.OK);
	}
	
	@PutMapping("/UpdateQuantity/{CartId}")
	public ResponseEntity<ResponseDto> UpdateQuantity(@RequestHeader(name="Token") String Token,@PathVariable("CartId") int CartId, @RequestParam("Quantity") int Quantity){
		CartModel model = service.UpdateQuantity(Token, CartId, Quantity);
		ResponseDto dto = new ResponseDto("Quantity Updated Successfuly",model);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/GetAllCarts")
	public ResponseEntity<List<CartModel>> FindAllCarts(@RequestHeader(name="Token") String Token){
		List<CartModel> model = service.FindAllCarts(Token);
		return new ResponseEntity<List<CartModel>>(model,HttpStatus.OK);

	}
	

}
