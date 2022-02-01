package com.example.Demo.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.Dto.LoginDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Dto.UserRegistrationDto;
import com.example.Demo.Exception.UserRegistrationException;
import com.example.Demo.Model.UserRegistrationModel;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Util.TokenUtil;

@Service
public class UserRegistrationService implements UserRegistrationServiceInterface {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenutil;
	
	
	@Override
	public ResponseDto CreateUserregistration(UserRegistrationDto userdto) {
		Optional<UserRegistrationModel> isPresent = userrepo.findById(userdto.UserId);
		if(isPresent.isPresent()) {
			System.out.println("User Already exist");
		}else {
			UserRegistrationModel model = modelmapper.map(userdto,UserRegistrationModel.class);
			userrepo.save(model);
			return new ResponseDto("User Registration Successful",model);
		}
		return null;
	}


	@Override
	public UserRegistrationModel GetUserById(long UserId) {
		
		return userrepo.findById(UserId).orElseThrow(()-> new UserRegistrationException("User Not Found"));
	}


	@Override
	public List<UserRegistrationModel> GetAllUser() {
		
		return userrepo.findAll();
	}


	@Override
	public ResponseDto DeletingUser(long UserId) {
		Optional<UserRegistrationModel> isUserPresent = userrepo.findById(UserId);
		if(isUserPresent.isPresent()) {
			userrepo.delete(isUserPresent.get());
			return new ResponseDto("User Deleted Successfully",UserId);
		}else {
			throw new UserRegistrationException("User Not Found...!");
		}
		
	}


	@Override
	public ResponseDto UpdateUser(long UserId, UserRegistrationDto userdto) {
		UserRegistrationModel model = this.GetUserById(UserId);
		Optional<UserRegistrationModel> isUserPresent = userrepo.findById(UserId);
			if(isUserPresent.isPresent()) {
				model.UpdateUserData(userdto);
				userrepo.save(isUserPresent.get());
				return new ResponseDto("User Updated Successfully",model);
				
			}else {
				throw new UserRegistrationException("User Not Found"); 
			}
		
	}


	@Override
	public ResponseDto loginValidation(LoginDto logindto) {
		String token;
		Optional<UserRegistrationModel> isPresent = userrepo.findByEmailId(logindto.getEmailId());
		if(isPresent.isPresent()) {
			String pass = isPresent.get().getPassword();
			if(pass.equals(logindto.getPassword())){
				token= tokenutil.createToken(isPresent.get().getUserId());
				return new ResponseDto("Token Generated for EmailId "+isPresent.get().getEmailId() ,token);
			}else {
				throw new UserRegistrationException("Invalid Password...!");
			}
		}else {
			throw new UserRegistrationException("Incorrect EmailId or Password...!");
		}
		
	}


	public Boolean verifyUser(String token) {
		long id = tokenutil.decodeToken(token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(id);
		if (isPresent.isPresent()) {
			isPresent.get().setVerify(true);
			userrepo.save(isPresent.get());
			return true;
		} else {
			return false;
		}
	}
	
	

	
}
