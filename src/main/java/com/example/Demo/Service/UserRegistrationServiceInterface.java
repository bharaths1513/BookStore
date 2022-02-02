package com.example.Demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Demo.Dto.LoginDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Dto.UserRegistrationDto;
import com.example.Demo.Model.UserRegistrationModel;

@Service
public interface UserRegistrationServiceInterface {

	public ResponseDto CreateUserregistration(UserRegistrationDto userdto);
	UserRegistrationModel GetUserById(long UserId);
	List<UserRegistrationModel> GetAllUser();
	ResponseDto DeletingUser(long UserId);
	ResponseDto UpdateUser(long UserId, UserRegistrationDto userdto );
	ResponseDto loginValidation(LoginDto logindto);
	public Boolean verifyUser(String token);
	ResponseDto ForgotPassword(String EmailId,String NewPassword);
}
