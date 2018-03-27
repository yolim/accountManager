package com.bank.account_manager;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.bank.account_manager.model.Account;
import com.bank.account_manager.model.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebIntegrationTest
public class CustomerControllerWebIntegrationTest {
	
	private final String customerJsonString = "{\"id\":1,\"name\":\"Michael\",\"accountList\":[{\"id\":2,\"type\":\"Debit\",\"amount\":0},{\"id\":1,\"type\":\"Credit\",\"amount\":0}]}";
	
	@Test
	public void testAdd() throws IOException {
		Set<Account> accountList = new HashSet<Account>();
		accountList.add(new Account("Debit", 0L));
		accountList.add(new Account("Credit", 0L));
		Customer customer = new Customer(1L, "Michael", accountList);
		
		RestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/customers", customer , String.class);

	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode responseJson = objectMapper.readTree(response.getBody());

	    assertThat( responseJson.toString()).isEqualTo(customerJsonString);	
	}
	
	@Test
	public void testGet() throws IOException {		
		RestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/customers/1", String.class);


	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode responseJson = objectMapper.readTree(response.getBody());

	    assertThat( responseJson.toString()).isEqualTo(customerJsonString);	
	}
	
}
