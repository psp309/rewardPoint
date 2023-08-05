package com.demo.rewardprogram.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.rewardprogram.entity.Customer;
import com.demo.rewardprogram.entity.TransactionRecord;
import com.demo.rewardprogram.model.CustomerDTO;
import com.demo.rewardprogram.model.TransactionRecordDTO;
import com.demo.rewardprogram.repo.TransactionRecordRepository;
import com.demo.rewardprogram.service.DataTransformer;

@Service
public class TransactionService {

	@Autowired
	public DataTransformer dataTransformer;
	
	@Autowired
	private TransactionRecordRepository tranRepo;
	
	public Customer createCustomer(String custName) {
		Customer mary = new Customer();
		mary.setName(custName);
		return mary;
	}

	public TransactionRecord createTransactionRecord(Customer cust, String amount, int minusMonth, int minusDay) {
		TransactionRecord maryTran1 = new TransactionRecord();
		maryTran1.setCustomer(cust);

		maryTran1.setAmount(new BigDecimal(amount));

		LocalDate monthAgo = LocalDate.now().minusMonths(minusMonth).minusDays(minusDay);

		maryTran1.setPurchaseDate(monthAgo);
		return maryTran1;
	}
	
	public CustomerDTO createCustomerDTO(String custName) {
		CustomerDTO mary = new CustomerDTO();
		mary.setName(custName);
		mary.setId(1l);
		return mary;
	}

	public TransactionRecordDTO createTransactionRecordDTO(CustomerDTO cust, String amount, int minusMonth, int minusDay) {
		TransactionRecordDTO maryTran1 = new TransactionRecordDTO();
		maryTran1.setCustomer(cust);
		maryTran1.setId(1l);

		maryTran1.setAmount(new BigDecimal(amount));

		LocalDate monthAgo = LocalDate.now().minusMonths(minusMonth).minusDays(minusDay);

		maryTran1.setPurchaseDate(monthAgo);
		return maryTran1;
	}
	
	public TransactionRecord createTransactionRecord(Customer cust, String amount) {
		TransactionRecord maryTran1 = new TransactionRecord();
		maryTran1.setCustomer(cust);

		maryTran1.setAmount(new BigDecimal(amount));

		LocalDate monthAgo = LocalDate.now();

		maryTran1.setPurchaseDate(monthAgo);
		tranRepo.save(maryTran1);
		return maryTran1;
	}
	
	
	
}
