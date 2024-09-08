package com.customerservice.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerservice.model.Customer;
import com.customerservice.service.CustomerService;
import com.customerservice.service.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("customers/feign")
public class CustomerFeignController {

	@Autowired
	private CustomerService customerService;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
		logger.debug("Notification: Request Received for Customer Id: {}", id);
		return ResponseEntity.of(customerService.getCustomerUsingFeignClient(id));
	}
}
