package com.example.Demo.Service;

import java.util.List;

import com.example.Demo.Dto.BookStoreDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.BookStoreModel;

public interface BookStoreServiceInterface {

	BookStoreModel AddBook(String Token, BookStoreDto bookdto );
	List<BookStoreModel> GetAllBooks(String Token);
	BookStoreModel GetBookById(String Token,int BookId);
	ResponseDto DeleteBook(String Token,int BookId);
	ResponseDto UpdateBook(String Token,int BookId,BookStoreDto bookdto);
	BookStoreModel ChangeBookQuantity(String Token,int BookId,int BookQuantity);
	BookStoreModel ChangeBookPrice(String Token,int BookId,int BookPrice);
	BookStoreModel GetBookByName(String Token, String BookName);
}
