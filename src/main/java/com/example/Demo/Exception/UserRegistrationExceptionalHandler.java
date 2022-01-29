package com.example.Demo.Exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Demo.Dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class UserRegistrationExceptionalHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errMesg = errorList.stream().map(objErr -> objErr.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseDto responseDTO = new ResponseDto("Exception While Processing REST Request", errMesg);
		return new ResponseEntity<ResponseDto>(responseDTO, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserRegistrationException.class)
	public ResponseEntity<ResponseDto> handleEmployeePayrollException(UserRegistrationException exception){
		ResponseDto responseDTO = new ResponseDto("Exception While Processing REST Request", exception.getMessage());
		return new ResponseEntity<ResponseDto>(responseDTO,HttpStatus.BAD_REQUEST);
	}
}
