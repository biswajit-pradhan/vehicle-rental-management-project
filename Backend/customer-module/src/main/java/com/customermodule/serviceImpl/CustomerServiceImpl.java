package com.customermodule.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.customermodule.exception.BusinessException;
import com.customermodule.model.Customer;
import com.customermodule.repository.CustomerRepo;
import com.customermodule.service.CustomerService;


@Configuration
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		if(customer.getFirstName().length()==0 || customer.getLastName().length()==0 || customer.getEmailId().length()==0||customer.getPhoneNumber().length()==0 ||customer.getAddress().length()==0) {
			throw new BusinessException("601","Please Send Proper Data,Something is Blank");
		}
		try {
			
		return customerRepo.save(customer);
		
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","given Data is null "+e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603","somthing went worng in service layer"+e.getMessage());
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(customer.getFirstName().length()==0 || customer.getLastName().length()==0 || customer.getEmailId().length()==0||customer.getPhoneNumber().length()==0 ||customer.getAddress().length()==0) {
			throw new BusinessException("604","Please Send Proper Data When your Updating ,Something is Blank");
		}
		try {
			
		return customerRepo.save(customer);
		
		}catch(IllegalArgumentException e) {
			throw new BusinessException("605","given Data is null while Updating "+e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("606","somthing went worng in service layer"+e.getMessage());
		}
	}

	@Override
	public List<Customer> viewAllCustomer() {
		// TODO Auto-generated method stub
		
		List<Customer> data = customerRepo.findAll();
		if(data.isEmpty()) {
			throw new BusinessException("607","there's not data in the table");
		}
		try {
		return data;
		}catch(Exception e) {
			throw new BusinessException("608","somthing went worng in service layer"+e.getMessage());
		}
	}

	@Override
	public Customer viewOneCustomerById(int id) {
		// TODO Auto-generated method stub
		try {
			return customerRepo.findById(id).get() ;
			}
			catch(IllegalArgumentException e) {
				throw new BusinessException("609","give some proper id to search"+e.getMessage());
				
			}catch(NoSuchElementException e) {
				throw new BusinessException("610","the data not fount"+e.getMessage());
				
			}
	}

	@Override
	public void removeCustomerById(int id) {
		// TODO Auto-generated method stub
		try {
			customerRepo.deleteById(id);
			}catch(IllegalArgumentException e) {
				throw new BusinessException("611","give some proper id to delete"+e.getMessage());
				
			}catch(NoSuchElementException e) {
				throw new BusinessException("612","there's no data to delete"+e.getMessage());
				
			}
		}
		
	

		
		 

}