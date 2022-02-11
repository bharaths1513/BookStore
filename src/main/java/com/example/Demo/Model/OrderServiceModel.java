package com.example.Demo.Model;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.Demo.Dto.OrderServiceDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderServiceModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate orderDate;
	private int price;
	private int quantity;
	private String address;
	private boolean cancel = false;


	@ManyToOne
	@JoinColumn(name = "userFKey", referencedColumnName = "UserId")
	private UserRegistrationModel user;
	
	@ManyToOne
	@JoinColumn(name="bookFKey",referencedColumnName="bookId")
	private BookStoreModel book;
	
	
	public OrderServiceModel(UserRegistrationModel user,BookStoreModel book,OrderServiceDto orderdto) {
		this.user=user;
		this.book=book;
		this.orderDate=LocalDate.now();
		this.price=orderdto.price;
		this.quantity = orderdto.quantity;
		this.address=orderdto.address;
		this.cancel=orderdto.cancel;
	}
	

}
