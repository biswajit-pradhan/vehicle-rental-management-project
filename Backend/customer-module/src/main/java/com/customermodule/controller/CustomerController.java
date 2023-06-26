package com.customermodule.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customermodule.exception.BusinessException;
import com.customermodule.exception.ControllerException;
import com.customermodule.model.Customer;
import com.customermodule.serviceImpl.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@PostMapping("/AddCustomer")
	public ResponseEntity<?> addCustomerDetails(@RequestBody @Valid Customer customer){
		try {
			Customer addcustomer = customerServiceImpl.addCustomer(customer);
			return new ResponseEntity<Customer>(addcustomer,HttpStatus.CREATED); 
		}catch(BusinessException e) {
			ControllerException CE = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException CE = new ControllerException("615","something went worng in controller");
			return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<?> updateCustomerDetails(@RequestBody @Valid Customer customer){
		try {
			Customer updatecustomer = customerServiceImpl.updateCustomer(customer);
			return new ResponseEntity<Customer>(updatecustomer,HttpStatus.CREATED); 
		}catch(BusinessException e) {
			ControllerException CE = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException CE = new ControllerException("616","something went worng in controller");
			return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/viewAllCustomer")
	public ResponseEntity<?> viewAllCustomerDetails(){
		try {
		List<Customer> getAll = customerServiceImpl.viewAllCustomer();
		return new ResponseEntity<List<Customer>>(getAll,HttpStatus.OK);
	}catch(BusinessException e) {
		ControllerException CE = new ControllerException(e.getErrorCode(),e.getErrorMessage());
		return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
	}catch(Exception e) {
		ControllerException CE = new ControllerException("617","something went worng in controller");
		return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
		
	   }
   }
	
	@GetMapping("viewOneCustomerById/{id}")
	public ResponseEntity<?> viewOneCustomerByIddata(@PathVariable int id){
		try {
			Customer getOne = customerServiceImpl.viewOneCustomerById(id);
			return new ResponseEntity<Customer> (getOne,HttpStatus.OK);
			}
			catch(BusinessException e) {
				ControllerException CE = new ControllerException(e.getErrorCode(),e.getErrorMessage());
				return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				ControllerException CE = new ControllerException("618","something went worng in controller");
				return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
				
			}
	}
	
	
	@DeleteMapping("/removeCustomerById/{id}")
	public ResponseEntity<?> removeCustomerByIddata(@PathVariable int id){
	try {
		 customerServiceImpl.removeCustomerById(id);
		return new ResponseEntity<Void> (HttpStatus.ACCEPTED);
		}
		catch(BusinessException e) {
			ControllerException CE = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException CE = new ControllerException("619","something went worng in controller");
			return new ResponseEntity<ControllerException>(CE,HttpStatus.BAD_REQUEST);
			
		}

	}
	

	
	
	

}
