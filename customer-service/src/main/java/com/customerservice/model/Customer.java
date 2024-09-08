package com.customerservice.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerId;
    private String name;
    private String email;
    private double creditcard;
    private Currency currency;

    
    
    
    
    public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}





	public String getCustomerId() {
		return customerId;
	}





	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public double getCreditCard() {
		return creditcard;
	}





	public void setCreditCard(double creditcard) {
		this.creditcard = creditcard;
	}





	public Currency getCurrency() {
		return currency;
	}





	public void setCurrency(Currency currency) {
		this.currency = currency;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", creditcard="
                + creditcard + ", currency=" + currency + "]";
    }
}
