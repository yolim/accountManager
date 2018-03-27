package com.bank.account_manager.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account_manager.model.Account;
import com.bank.account_manager.model.Customer;
import com.bank.account_manager.repository.AccountRepository;
import com.bank.account_manager.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Transactional
	public Customer addCustomer(Customer customer) {
		Set<Account> accountList = new HashSet<Account>();
		accountList.add(new Account("Debit", 0L));
		accountList.add(new Account("Credit", 0L));
		
		accountRepository.save(accountList);
		customer.setAccountList(accountList);
		
		return customerRepository.save(customer);
	}

	@Transactional
	public Customer findOneWithoutAccount(Long id) {
		return customerRepository.findOne(id);
	}

	@Transactional
	public Customer findOneWithAccount(Long id) {
		return customerRepository.findByIdWithAccount(id);
	}

	@Transactional
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Transactional
	public Customer updateCustomer(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

	@Transactional
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

}
