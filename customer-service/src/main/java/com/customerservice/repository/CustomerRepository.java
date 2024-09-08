
package com.customerservice.repository;

import com.customerservice.model.Customer;


public interface CustomerRepository {

	/**
	 * Returns the customer detail from the DB or from remote service call.
	 * 
	 * @param id
	 *            id of the customer
	 * @return Customer Detail
	 */
	public Customer getCustomer(String id);
}
