package com.example.Demo;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication

public class BookStoreApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BookStoreApplication.class, args);
			ApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);
		
		//log.info("Employee Payroll App Started in {} Environment",context.getEnvironment().getProperty("environment"));
	}

}
