
package com.customerservice.model;

import lombok.Data;


@Data
public class CreditCard {

	private String id;
	private double balance;
	private Currency currency;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", balance=" + balance + ", currency=" + currency + "]";
	}

	
}

