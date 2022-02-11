package com.example.Demo.Model;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.Demo.Dto.UserRegistrationDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="User_Registration_Data")
public @Data class UserRegistrationModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long UserId;
	private String Firstname;
	private String Lastname;
	private String KYC;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate RegistredDate;
	
	private LocalDate DOB; 
	private String EmailId;
	private String Password;
	private boolean verify;

	public UserRegistrationModel() {
		
	}
	
	public UserRegistrationModel(UserRegistrationDto userregistration) {
		this.UpdateUserData(userregistration);
	}
	public void UpdateUserData(UserRegistrationDto userregistration) {
		
//		this.UserId = userregistration.UserId;
		this.Firstname = userregistration.Firstname;
		this.Lastname = userregistration.Lastname;
		this.KYC = userregistration.KYC;
		this.RegistredDate = LocalDate.now();
		this.DOB = userregistration.DOB;
		this.EmailId = userregistration.EmailId;
		this.Password = userregistration.Password;
		this.verify = userregistration.verify;
	}
}
