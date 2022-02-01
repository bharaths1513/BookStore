package com.example.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.BookStoreModel;

public interface BookStoreRepository extends JpaRepository<BookStoreModel,Integer> {

//	@Query(value="SELECT * FROM bookstorage WHERE book_name= :BookName",nativeQuery = true)
//	BookStoreModel getBookByName(String BookName);
	@Query(value="SELECT * FROM book_storage WHERE book_name=:bookName",nativeQuery = true)
	BookStoreModel findByBookName(String bookName);

	

	
	

}
