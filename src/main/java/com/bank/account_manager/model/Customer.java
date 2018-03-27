package com.bank.account_manager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Customer")
public class Customer {

	
	@Id
	@GeneratedValue
	@Column(name = "cust_id")
	private Long id;
	
	@NotNull
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Account> accountList = new HashSet<Account>();

	public Customer() {
	}


	public Customer(Long id, String name, Set<Account> accountList) {
		this.id = id;
		this.name = name;
		this.accountList = accountList;
	}
	
	public Customer( String name, Set<Account> accountList) {
		this.name = name;
		this.accountList = accountList;
	}


	public Set<Account> getAccountList() {
		return accountList;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAccountList(Set<Account> accountList) {
		this.accountList = accountList;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", accountList=" + accountList + "]";
	}
}