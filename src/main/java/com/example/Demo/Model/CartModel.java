package com.example.Demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book_store_cart")
public @Data class CartModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CartId;
	
	@OneToOne
	@JoinColumn(name = "user_fk_id", referencedColumnName = "UserId")
	private UserRegistrationModel usermodel;
	
	@ManyToOne
	@JoinColumn(name = "book_fk_id", referencedColumnName = "bookId")
	private BookStoreModel bookmodel;
	
	private int Quantity;
	
	public CartModel(UserRegistrationModel usermodel,BookStoreModel bookmodel,int Quantity) {
		this.usermodel=usermodel;
		this.bookmodel=bookmodel;
		this.Quantity=Quantity;
	}
}
