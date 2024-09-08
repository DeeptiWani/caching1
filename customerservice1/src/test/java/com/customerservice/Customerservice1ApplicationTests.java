package com.customerservice;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.customerservice.controller.CustomerController;
import com.customerservice.model.Customer;
import com.customerservice.service.CustomerService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Customerservice1ApplicationTests {
	@Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomerTest() {
        String customerId = "1";
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("Deepti Wani");
        customer.setEmail("dw@gmail.com");

        when(customerService.getCustomer(customerId)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> response = customerController.getCustomer(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());

        verify(customerService).getCustomer(customerId);
    }

    @Test
    public void getCustomerNotFoundTest() {
        String customerId = "1";
        when(customerService.getCustomer(customerId)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = customerController.getCustomer(customerId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(customerService).getCustomer(customerId);
    }

	
}