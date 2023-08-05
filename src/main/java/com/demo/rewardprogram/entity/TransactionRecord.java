package com.demo.rewardprogram.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransactionRecord {

	private BigDecimal amount;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Id
	@GeneratedValue
	private Long id;

	private LocalDate purchaseDate;

	public TransactionRecord() {
		super();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
