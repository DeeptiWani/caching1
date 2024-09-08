
package com.customerservice.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.customerservice.model.Customer;


@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private static ConcurrentHashMap<String, Customer> customers;

	static {
		customers = new ConcurrentHashMap<>();
		customers.putAll(initializeCustomer());
	}


	@Override
	public Customer getCustomer(String id) {
		if (customers.containsKey(id)) {
			return customers.get(id);
		} else {
			return null;
		}
	}

	private static ConcurrentHashMap<String, Customer> initializeCustomer() {
		ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<>();

		Customer deepti = new Customer();
		deepti.setCustomerId("1");
		deepti.setName("Deepti Wani");
		deepti.setEmail("dw@gmail.com");
		
		
		Customer krish = new Customer();
		krish.setCustomerId("2");
		krish.setName("Krish Sharma");
		krish.setEmail("ks@gmail.com");
		
		
		Customer payal = new Customer();
		payal.setCustomerId("3");
		payal.setName("Payal Deshmukh");
		payal.setEmail("pd@gmail.com");
	
		
		Customer riya = new Customer();
		riya.setCustomerId("4");
		riya.setName("Riya Gosavi");
		riya.setEmail("rg@gmail.com");
		
		
		Customer sahil = new Customer();
		sahil.setCustomerId("5");
		sahil.setName("Sahil Manak");
		sahil.setEmail("sm@gmail.com");
		
		
		Customer piyush = new Customer();
		piyush.setCustomerId("6");
		piyush.setName("Piyush Mehera");
		piyush.setEmail("pm@gmail.com");
	
		
		customers.put("1", deepti);
		customers.put("2", krish);
		customers.put("3", payal);
		customers.put("4", riya);
		customers.put("5", sahil);
		customers.put("6", piyush);

		return customers;

	}

	

}
