
package com.customerservice.repository;

import com.customerservice.model.CreditCard;


public interface CreditCardCacheRepository {

	public CreditCard getCreditCard(String id);
}
