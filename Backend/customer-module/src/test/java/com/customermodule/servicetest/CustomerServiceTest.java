package com.customermodule.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.customermodule.model.Customer;
import com.customermodule.repository.CustomerRepo;
import com.customermodule.serviceImpl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@MockBean
	private CustomerRepo customerRepo;

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("pravin");
		customer.setLastName("kumar");
		customer.setEmailId("pravin123@gmail.com");
		customer.setPhoneNumber("9876543210");
		customer.setAddress("chennai");
		Mockito.when(customerRepo.save(customer)).thenReturn(customer);
		assertThat(customerServiceImpl.addCustomer(customer)).isEqualTo(customer);
	}

	@Test
	public void testGetCustomerById() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("pravin");
		customer.setLastName("kumar");
		customer.setEmailId("pravin123@gmail.com");
		customer.setPhoneNumber("9876543210");
		customer.setAddress("chennai");
		Mockito.when(customerRepo.findById(1).get()).thenReturn(customer);
		assertThat(customerServiceImpl.viewOneCustomerById(1)).isEqualTo(customer);

	}

	@Test
	public void testGetAllCustomers() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("pravin");
		customer.setLastName("kumar");
		customer.setEmailId("pravin123@gmail.com");
		customer.setPhoneNumber("9876543210");
		customer.setAddress("chennai");

		Customer customer1 = new Customer();
		customer1.setCustomerId(2);
		customer1.setFirstName("kiruba");
		customer1.setLastName("a");
		customer1.setEmailId("kiruba123@gmail.com");
		customer1.setPhoneNumber("7540097611");
		customer1.setAddress("bangalore");

		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		customerList.add(customer1);

		Mockito.when(customerRepo.findAll()).thenReturn(customerList);
		assertThat(customerServiceImpl.viewAllCustomer()).isEqualTo(customerList);

	}

	@Test

	public void testDeleteCustomer() {
		Customer customer1 = new Customer();
		customer1.setCustomerId(2);
		customer1.setFirstName("kiruba");
		customer1.setLastName("a");
		customer1.setEmailId("kiruba123@gmail.com");
		customer1.setPhoneNumber("7540097611");
		customer1.setAddress("bangalore");

		Mockito.when(customerRepo.findById(2).get()).thenReturn(customer1);
		Mockito.when(customerRepo.existsById(customer1.getCustomerId())).thenReturn(false);
		assertFalse(customerRepo.existsById(customer1.getCustomerId()));
	}

	@Test

	public void testUpdateCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("pravin");
		customer.setLastName("kumar");
		customer.setEmailId("pravin123@gmail.com");
		customer.setPhoneNumber("9876543210");
		customer.setAddress("chennai");

		Mockito.when(customerRepo.findById(1).get()).thenReturn(customer);
		customer.setPhoneNumber("9767573255");
		Mockito.when(customerRepo.save(customer)).thenReturn(customer);
		assertThat(customerServiceImpl.updateCustomer(customer)).isEqualTo(customer);

	}

}
