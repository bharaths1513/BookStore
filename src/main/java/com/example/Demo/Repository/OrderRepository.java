package com.example.Demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Demo.Model.OrderServiceModel;

public interface OrderRepository extends JpaRepository<OrderServiceModel,Integer> {

	@Query(value="SELECT * FROM order_details WHERE userFKey=:id",nativeQuery = true)
	List<OrderServiceModel> findAllUserOrders(long id);

	@Query(value="SELECT * FROM order_details WHERE cancel=:cancel",nativeQuery=true)
	List<OrderServiceModel> findActiveOrders(boolean cancel);

	

}
