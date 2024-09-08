
package com.customerservice.service;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customerservice.config.CustomerFeignClient;
import com.customerservice.exception.CustomerNotFoundException;
import com.customerservice.model.CreditCard;
import com.customerservice.model.Customer;
import com.customerservice.repository.CreditCardCacheRepository;
import com.customerservice.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private RestTemplate restTemplate;

//	@Value("${creditcard.service.url}")
	private String creditcardServiceUrl;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CreditCardCacheRepository creditcardCacheRepository;

	@Autowired
	private CustomerFeignClient customerFeignClient;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@HystrixCommand(fallbackMethod = "getCustomerDetailFromCache", ignoreExceptions = { CustomerNotFoundException.class })
	@Retryable(
	        value = { CustomerNotFoundException.class },
	        maxAttempts = 4, 
	        backoff = @Backoff(delay = 2000)
	    )
	    @Override
	    public Optional<Customer> getCustomer(String customerId) {
	        logger.info("Get Customer detail for customer id: {}", customerId);
	        Optional<Customer> customer = Optional.ofNullable(customerRepository.getCustomer(customerId));
	        customer.orElseThrow(() -> new CustomerNotFoundException("Customer Id not Found: " + customerId));
	        ResponseEntity<CreditCard> creditcardEntity = restTemplate.getForEntity(getCreditCardUrl(customerId), CreditCard.class);
	        if (creditcardEntity.getStatusCode() == HttpStatus.OK) {
	            CreditCard creditCard = creditcardEntity.getBody();
	            customer.get().setCurrency(creditCard.getCurrency());
	            customer.get().setCreditCard(creditCard.getBalance());
	        }
	        return customer;
	    }

	    @Recover
	    public Optional<Customer> recover(Exception e, String customerId) {
	        logger.error("Recovering from retry failure: {}", e.getMessage());
	        return Optional.empty();
	    }


	@Override
	public Optional<Customer> getCustomerUsingFeignClient(String customerId) {
		logger.info("Feign: Get Customer detail for customer id: {}", customerId);
		Optional<Customer> customer = Optional.ofNullable(customerRepository.getCustomer(customerId));
		customer.orElseThrow(() -> new CustomerNotFoundException("Customer Id not Found: " + customerId));
		CreditCard creditcard = customerFeignClient.getCreditCard(customerId);
		if (creditcard != null) {
			customer.get().setCurrency(creditcard.getCurrency());
			customer.get().setCreditCard(creditcard.getBalance());
		}
		return customer;
	}

	Optional<Customer> getCustomerDetailFromCache(String customerId) {
		logger.info("Circuit Open, Getting Customer detail from Cache for customer id '{}'", customerId);
		Optional<Customer> customer = Optional.ofNullable(customerRepository.getCustomer(customerId));
		customer.orElseThrow(() -> new CustomerNotFoundException("Customer Id not Found: " + customerId));
		CreditCard creditcard = getCreditCard(customerId);
		customer.get().setCurrency(creditcard.getCurrency());
		customer.get().setCreditCard(creditcard.getBalance());
		return customer;
	}

	private CreditCard getCreditCard(String id) {
		return creditcardCacheRepository.getCreditCard(id);
	}

	private String getCreditCardUrl(String creditcardId) {
		// return "http://" + creditcardServiceUrl + "creditcards/" + creditcardId;
		return "http://creditcard-service/creditcard/" + creditcardId;
	}

	

	

}
