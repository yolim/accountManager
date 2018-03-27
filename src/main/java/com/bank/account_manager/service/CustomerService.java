package com.bank.account_manager.service;

import java.util.List;

import com.bank.account_manager.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer);
	
	Customer findOneWithoutAccount(Long id);
	
	Customer findOneWithAccount(Long id);
	
	List<Customer> findAll();
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(Customer customer);
}
