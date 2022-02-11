package com.example.Demo.Dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceDto {

	public int price;
	public int quantity;
	public String address;
	public boolean cancel = false;
}
