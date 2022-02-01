package com.example.Demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.Dto.BookStoreDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.BookStoreModel;
import com.example.Demo.Service.BookStoreService;

@RestController
@RequestMapping("/BookStore")
public class BootstoreController {
	
	@Autowired
	private BookStoreService service;

	@PostMapping("/AddBook")
	public ResponseEntity<ResponseDto> AddBook(@RequestHeader(name="Token") String Token, @RequestBody BookStoreDto bookdto){
		BookStoreModel model = service.AddBook(Token, bookdto);
		ResponseDto Dto = new ResponseDto("Book Added Successfully", model);
		return new ResponseEntity<ResponseDto>(Dto,HttpStatus.OK);
	}
	
	@GetMapping("/GetAllBooks")
	public ResponseEntity<List<BookStoreModel>> GetBooks(@RequestHeader(name="Token") String Token){
		List<BookStoreModel> model = service.GetAllBooks(Token);
		return new ResponseEntity<List<BookStoreModel>>(model,HttpStatus.OK);
		
	}
	
	@GetMapping("/GetBook/{BookId}")
	public ResponseEntity<ResponseDto> GetBookById(@RequestHeader(name="Token") String Token,@PathVariable("BookId") int BookId){
		BookStoreModel model = service.GetBookById(Token, BookId);
		ResponseDto dto = new ResponseDto("Get Book By Id Successfull",model);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK); 
	}
	
	@DeleteMapping("/DeleteBook/{BookId}")
	public ResponseEntity<ResponseDto> DeleteBook(@RequestHeader(name="Token") String Token,@PathVariable("BookId") int BookId){
		ResponseDto dto = service.DeleteBook(Token, BookId);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/UpdateBook/{BookId}")
	public ResponseEntity<ResponseDto> Updatingbook(@RequestHeader(name="Token")String Token,@PathVariable("BookId") int BookId, @RequestBody BookStoreDto bookdto){
		ResponseDto dto = service.UpdateBook(Token, BookId, bookdto);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/UpdateQuantity/{BookId}")
	public ResponseEntity<ResponseDto> ChangeQuantity(@RequestHeader(name="Token")String Token,@PathVariable("BookId")int BookId,@RequestParam("Quantity")int Quantity){
		BookStoreModel model= service.ChangeBookQuantity(Token, BookId, Quantity);
		ResponseDto dto = new ResponseDto("Book Quantity Updated Successfully",model);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@PutMapping("/UpdatePrice/{BookId}")
	public ResponseEntity<ResponseDto> ChangePrice(@RequestHeader(name="Token")String Token,@PathVariable("BookId")int BookId,@RequestParam("BookPrice")int BookPrice){
		BookStoreModel model = service.ChangeBookPrice(Token, BookId, BookPrice);
		ResponseDto dto = new ResponseDto("Price Updated Successfully",model);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/GetByName/{BookName}")
	public ResponseEntity<ResponseDto> GetBookByName(@RequestHeader(name="Token")String Token,@PathVariable("BookName")String BookName){
		BookStoreModel model = service.GetBookByName(Token, BookName);
		ResponseDto dto = new ResponseDto("Get Books By Name Successfull",model);
		return new ResponseEntity<ResponseDto>(dto,HttpStatus.OK);
	}
}
