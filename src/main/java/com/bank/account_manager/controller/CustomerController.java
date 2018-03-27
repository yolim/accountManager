package com.bank.account_manager.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account_manager.model.Customer;
import com.bank.account_manager.service.CustomerService;

@RestController
@RequestMapping("api/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "customers", method = RequestMethod.GET)
	public List<Customer> list() {
		return customerService.findAll();
	}

	@RequestMapping(value = "customers", method = RequestMethod.POST)
	public Customer create(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@RequestMapping(value = "customers/{id}", method = RequestMethod.GET)
	public Customer get(@PathVariable Long id) {
		return customerService.findOneWithAccount(id);
	}

	@RequestMapping(value = "customers/{id}", method = RequestMethod.PUT)
	public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
		Customer existingCustomer = customerService.findOneWithoutAccount(id);
		BeanUtils.copyProperties(customer, existingCustomer);
		return customerService.updateCustomer(existingCustomer);
	}

	@RequestMapping(value = "customers/{id}", method = RequestMethod.DELETE)
	public Customer delete(@PathVariable Long id) {
		Customer existingCustomer = customerService.findOneWithoutAccount(id);
		customerService.deleteCustomer(existingCustomer);
		return existingCustomer;
	}
	
	
	
	
	
	
}
