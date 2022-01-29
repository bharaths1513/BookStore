package com.example.Demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

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
}
