package com.example.Demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Demo.Dto.ResponseDto;
import com.example.Demo.Model.BookStoreModel;
import com.example.Demo.Model.CartModel;

public interface CartRepository extends JpaRepository<CartModel,Integer> {

	@Query(value = "SELECT * FROM book_store_cart WHERE user_fk_id=:id",nativeQuery = true)
	List<CartModel> findAllByUserId(long id);



}
