package com.example.Demo.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.Dto.LoginDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Dto.UserRegistrationDto;
import com.example.Demo.Model.UserRegistrationModel;
import com.example.Demo.Service.UserRegistrationServiceInterface;

@RestController
@RequestMapping("/UserRegistration")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationServiceInterface service;
	
	@PostMapping("/Register")
	public ResponseEntity<ResponseDto> AddUser(@Valid @RequestBody UserRegistrationDto userdto){
		ResponseDto resdto = null;
		resdto = service.CreateUserregistration(userdto);
		ResponseDto DTO = new ResponseDto("User Registration Successfull",resdto);
		return new ResponseEntity<ResponseDto>(DTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/GetUser/{UserId}")
	public ResponseEntity<ResponseDto> GetUserById(@PathVariable("UserId") long UserId ){
		UserRegistrationModel model = service.GetUserById(UserId);
		ResponseDto dto = new ResponseDto("User Found",model);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/GetAllUsers")
	public ResponseEntity<List<UserRegistrationModel>> getAllUser(){
		List<UserRegistrationModel> model = service.GetAllUser();
		return new ResponseEntity<List<UserRegistrationModel>>(model,HttpStatus.OK);
	}
	
	@DeleteMapping("/DeleteUser/{UserId}")
	public ResponseEntity<ResponseDto> deleteUser(@PathVariable("UserId") long UserId){
		ResponseDto dto = service.DeletingUser(UserId);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/UpdateUser/{UserId}")
	public ResponseEntity<ResponseDto> updateUser(@PathVariable("UserId") long UserId, @RequestBody UserRegistrationDto userdto ){
		ResponseDto dto = service.UpdateUser(UserId, userdto);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@PostMapping("/Login")
	public ResponseEntity<ResponseDto> LoginUser(@RequestBody LoginDto logindto){
		ResponseDto dto = service.loginValidation(logindto);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@PostMapping("/VerifyLogin/{Token}")
	public boolean LoginVerification(@PathVariable("Token") String Token){
		boolean model = service.verifyUser(Token);
		return model;
	}
}



