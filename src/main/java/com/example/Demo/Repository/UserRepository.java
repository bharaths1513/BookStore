package com.example.Demo.Repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Demo.Model.UserRegistrationModel;

@Repository
public interface UserRepository extends JpaRepository<UserRegistrationModel, Long> {

	//Optional<UserRegistrationModel> findByEmailId(String emailId);

	
	//Optional<UserRegistrationModel> findByEmailId(String emailId);
	@Query(value = "SELECT * FROM user_registration_data WHERE email_id= :emailId",nativeQuery = true)
	Optional<UserRegistrationModel> findByEmailId(String emailId);


//	void save(Optional<UserRegistrationModel> isPresent);

	//void save(Optional<UserRegistrationModel> isPresent);

}
