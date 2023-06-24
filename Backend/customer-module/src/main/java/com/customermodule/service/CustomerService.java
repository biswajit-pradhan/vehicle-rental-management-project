package com.customermodule.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.customermodule.model.Customer;



@Service
public interface CustomerService {
	
	 
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public List<Customer> viewAllCustomer();
	
	public Customer viewOneCustomerById(int id);
	
	public void removeCustomerById(int id);
	
	
	

}
