package com.bookingmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingmodule.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
