package com.example.Demo.Dto;

import lombok.Data;

public @Data class ResponseDto {

	private String message;
	private Object data;
	
	
	public ResponseDto(String message, Object data) {
		this.message = message;
		this.data = data;
	}
	
	
}
