package com.example.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Demo.Model.UserRegistrationModel;


public interface UserRepository extends JpaRepository<UserRegistrationModel, Long> {

}
