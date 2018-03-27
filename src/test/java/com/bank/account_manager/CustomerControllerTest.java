package com.bank.account_manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bank.account_manager.controller.CustomerController;
import com.bank.account_manager.model.Account;
import com.bank.account_manager.model.Customer;
import com.bank.account_manager.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	private CustomerController cc;

	@Mock
	private CustomerService cs;
	
	@Test
	public void testCustomerAdd() {
    	Customer customer = new Customer(1L, "Michael", null);
    	
    	cc.create(customer);
		when(cs.addCustomer(customer)).thenReturn(customer);
		verify(cs).addCustomer(customer);
	}
	
	@Test
	public void testCustomerGet() {
		Set<Account> accountList = new HashSet<Account>();
		accountList.add(new Account("Debit", 0L));
		accountList.add(new Account("Credit", 0L));
		Customer customerFromService = new Customer(1L, "Michael", accountList);
		when(cs.findOneWithAccount(1L)).thenReturn(customerFromService);

		Customer customerFromController = cc.get(1L);

		verify(cs).findOneWithAccount(1l);		

		assertThat(customerFromController.getName()).contains("Michael");
		assertThat(customerFromController.getAccountList()).isNotNull().containsAll(accountList);	
	}
}
