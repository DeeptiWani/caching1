
package com.customerservice.service;

import java.util.Optional;

import com.customerservice.model.Customer;


public interface CustomerService {

	/**
	 * Returns the customer details
	 * 
	 * @param customerId
	 *            unique id of the customer
	 * @return Customer Detail
	 */
	public Optional<Customer> getCustomer(String customerId);
	
	public Optional<Customer> getCustomerUsingFeignClient(String customerId);

}
