package com.example.Demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.Dto.CartDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Exception.UserRegistrationException;
import com.example.Demo.Model.BookStoreModel;
import com.example.Demo.Model.CartModel;
import com.example.Demo.Model.UserRegistrationModel;
import com.example.Demo.Repository.CartRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Util.TokenUtil;

@Service
public class CartService implements CartServiceInterface {

	@Autowired
	private CartRepository cartrepo;
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private BookStoreService service;
	
	
	
	@Override
	public CartModel AddCart(String Token, int BookId, CartDto dto) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		if(user.isPresent()) {
			BookStoreModel model = service.GetBookById(Token, BookId);
			CartModel cart = new CartModel(user.get(),model,dto.getQuantity());
			return cartrepo.save(cart);
		}
		return null;
	}



	@Override
	public ResponseDto RemoveFromCart(String Token, int CartId) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		if(user.isPresent()) {
			cartrepo.deleteById(CartId);
			return new ResponseDto("Book Removed from Cart successfull",CartId);
		}
			throw new UserRegistrationException("Cart Not Found");
	}



	@Override
	public List<CartModel> FindAllInCart(String Token) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		if(user.isPresent()) {
			List<CartModel> model = cartrepo.findAllByUserId(Id);
			return model;
		}
		throw new UserRegistrationException("User Not Found");
	}



	@Override
	public CartModel UpdateQuantity(String Token, int CartId, int Quantity) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		if(user.isPresent()) {
			CartModel model = cartrepo.getById(CartId);
			model.setQuantity(Quantity);
			return cartrepo.save(model);
		}else {
			throw new UserRegistrationException("User Not Found");

		}
		
	}



	@Override
	public List<CartModel> FindAllCarts(String Token) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> user = userrepo.findById(Id);
		if(user.isPresent()) {
			return cartrepo.findAll();
		}else {
			throw new UserRegistrationException("User Not Found");

		}

	}

	
}
