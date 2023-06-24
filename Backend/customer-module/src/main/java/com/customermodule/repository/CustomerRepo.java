package com.customermodule.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customermodule.model.Customer;



@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer>{

	

	

	

}