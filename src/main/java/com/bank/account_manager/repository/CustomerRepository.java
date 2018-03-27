package com.bank.account_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.account_manager.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{


	@Query("SELECT cust from Customer cust where cust.id=:id")
    public Customer findByIdWithAccount(@Param("id") Long id);
}
