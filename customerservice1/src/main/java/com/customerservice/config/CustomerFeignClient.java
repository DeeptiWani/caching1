
package com.customerservice.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customerservice.model.CreditCard;


@FeignClient(name = "creditcard-service")

// Load balance will fetch the list of Server running for price-service and will
// get the hostname and URL of prices-service
@RibbonClient(name = "creditcard-service")
public interface CustomerFeignClient {

	@RequestMapping("/creditcards/{id}")
	public CreditCard getCreditCard(@PathVariable(value = "id") String id);

	

}
