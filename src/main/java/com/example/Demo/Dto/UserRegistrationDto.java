package com.example.Demo.Dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
public @ToString class UserRegistrationDto {
	
	public long UserId;
	
	public String Firstname;
	public String Lastname;
	public String KYC;
	
	@JsonFormat(pattern = "dd MM yyyy")
	public LocalDate RegistredDate;
	
	@JsonFormat(pattern = "dd MM yyyy")
	public LocalDate DOB;
	
	public String EmailId;
	public String Password;
	public boolean verify;

}
