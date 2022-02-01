package com.example.Demo.Dto;

import lombok.Data;
import lombok.ToString;

@ToString
public @Data class LoginDto {

	public String Password;
	public String EmailId;
}
