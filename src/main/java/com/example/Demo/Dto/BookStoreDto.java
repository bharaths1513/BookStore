package com.example.Demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStoreDto {

	private String bookName;
	private String bookAuthor;
	private String bookDescription;
	private String bookLogo;
	private int bookPrice;
	private int bookQuantity;
}
