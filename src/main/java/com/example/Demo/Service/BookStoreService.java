package com.example.Demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.Dto.BookStoreDto;
import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Exception.UserRegistrationException;
import com.example.Demo.Model.BookStoreModel;
import com.example.Demo.Model.UserRegistrationModel;
import com.example.Demo.Repository.BookStoreRepository;
import com.example.Demo.Repository.UserRepository;
import com.example.Demo.Util.TokenUtil;

@Service
public class BookStoreService implements BookStoreServiceInterface  {

	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private BookStoreRepository bookrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Override
	public BookStoreModel AddBook(String Token, BookStoreDto bookdto) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		if(isPresent.isPresent()) {
			BookStoreModel model = new BookStoreModel();
			model.createBook(bookdto);
			return bookrepo.save(model);
		}
		return null;
	}

	@Override
	public List<BookStoreModel> GetAllBooks(String Token) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		if(isPresent.isPresent()) {
			return bookrepo.findAll();
		}else {
			throw new UserRegistrationException("Invalid Token");
		}
		
	}

	@Override
	public BookStoreModel GetBookById(String Token,int BookId) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		if(isPresent.isPresent()) {
			return bookrepo.findById(BookId).orElseThrow(()-> new UserRegistrationException("Book Not Found"));
		}else {
			throw new UserRegistrationException("Invalid Token");
		}
		
	}

	@Override
	public ResponseDto DeleteBook(String Token, int BookId) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		//Optional<BookStoreModel> model = bookrepo.findById(BookId);
		if(isPresent.isPresent()) {
			BookStoreModel model = this.GetBookById(Token, BookId);
			 bookrepo.delete(model);
			 return new ResponseDto("Book Deleted Successfully ",BookId);
		}else {
			throw new UserRegistrationException("Book Not Found");
			//return new ResponseDto("Book Not Found For id: ",BookId);
		}
		
	}

	@Override
	public ResponseDto UpdateBook(String Token, int BookId, BookStoreDto bookdto) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		
		if(isPresent.isPresent()) {
			BookStoreModel model = this.GetBookById(Token, BookId);
			model.updateBook(bookdto);
			bookrepo.save(model);
			return new ResponseDto("Book Updated Successfully",model);
		}else {
			throw new UserRegistrationException("Book Not Found");
		}
	}

	@Override
	public BookStoreModel ChangeBookQuantity(String Token, int BookId, int BookQuantity) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		
		if(isPresent.isPresent()) {
			BookStoreModel model = this.GetBookById(Token, BookId);
			model.setBookQuantity(BookQuantity);
			return bookrepo.save(model);
		}else {
			throw new UserRegistrationException("Book Not Found");
		}
		
		
	}

	@Override
	public BookStoreModel ChangeBookPrice(String Token, int BookId, int BookPrice) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		if(isPresent.isPresent()) {
			BookStoreModel model = this.GetBookById(Token, BookId);
			model.setBookPrice(BookPrice);
			return bookrepo.save(model);
		}else {
			throw new UserRegistrationException("Book Not Found");
		}
		
	}

	@Override
	public BookStoreModel GetBookByName(String Token, String BookName) {
		long Id = tokenutil.decodeToken(Token);
		Optional<UserRegistrationModel> isPresent = userrepo.findById(Id);
		if(isPresent.isPresent()) {
			if(bookrepo.findByBookName(BookName)==null) {
				throw new UserRegistrationException("Book Not Found");
			}
			return bookrepo.findByBookName(BookName);
			
		}else {
			
			
			throw new UserRegistrationException("Invalid Book Name");
		}
		
	}

}
