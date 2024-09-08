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

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Tracer tracer;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
		logger.debug("Notification: Request Received for Customer Id: {}", id);
		// TraceId can be added in error respose for easy debugging
		logger.info("Tracer Id: '{}'", tracer.currentSpan().context().traceIdString());
		return ResponseEntity.of(customerService.getCustomer(id));
	}

}
