package com.demo.rewardprogram.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.rewardprogram.model.CustomerDTO;
import com.demo.rewardprogram.model.TransactionRecordDTO;
import com.demo.rewardprogram.repo.CustomerRepository;
import com.demo.rewardprogram.repo.TransactionRecordRepository;
import com.demo.rewardprogram.service.CustomerService;
import com.demo.rewardprogram.service.DataTransformer;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private DataTransformer dataTransformer;

	private TransactionService testData = new TransactionService();

	@Autowired
	private TransactionRecordRepository tranRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.rewardprogram.service.CustomerService#findById(java.lang.Long)
	 */
	@Override
	public CustomerDTO findById(Long custId) {
		Optional<com.demo.rewardprogram.entity.Customer> foundCust = custRepo.findById(custId);
		if (foundCust.isPresent()) {
			return dataTransformer.toCustomerModel(foundCust.get());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.rewardprogram.service.CustomerService#findThreeMonthTrans()
	 */
	@Override
	public List<TransactionRecordDTO> findThreeMonthTrans() {
		List<TransactionRecordDTO> threeMonthTrans = new ArrayList<>();
		
		List<com.demo.rewardprogram.entity.TransactionRecord> threeMonthTranRecords = tranRepo
				.findAllInLastThreeMonth();

		threeMonthTranRecords.forEach(source -> {
			TransactionRecordDTO target = new TransactionRecordDTO();
			BeanUtils.copyProperties(source, target);
			target.setCustomer(dataTransformer.toCustomerModel(source.getCustomer()));
			threeMonthTrans.add(target);
		});

		return threeMonthTrans;
	}
	
	@PostConstruct
	private void postConstruct() {
		if (custRepo.findAll().isEmpty()) {
			com.demo.rewardprogram.entity.Customer mary = custRepo.save(testData.createCustomer("Mary"));
			com.demo.rewardprogram.entity.Customer tom = custRepo.save(testData.createCustomer("Tom"));

			tranRepo.save(testData.createTransactionRecord(mary, "20", 1, 2));
			tranRepo.save(testData.createTransactionRecord(mary, "120", 1, 2));
			tranRepo.save(testData.createTransactionRecord(mary, "100", 1, 5));
			tranRepo.save(testData.createTransactionRecord(mary, "20", 2, 0));
			tranRepo.save(testData.createTransactionRecord(mary, "60", 2, 4));
			tranRepo.save(testData.createTransactionRecord(mary, "120", 2, 4));
			tranRepo.save(testData.createTransactionRecord(mary, "200", 0, 0));
			
			tranRepo.save(testData.createTransactionRecord(tom, "120", 1, 3));
			tranRepo.save(testData.createTransactionRecord(tom, "20", 2, 1));
			tranRepo.save(testData.createTransactionRecord(tom, "100", 1, 15));
			tranRepo.save(testData.createTransactionRecord(tom, "56", 2, 14));
			tranRepo.save(testData.createTransactionRecord(tom, "110", 1, 15));
			tranRepo.save(testData.createTransactionRecord(tom, "66", 1, 14));
			tranRepo.save(testData.createTransactionRecord(tom, "200", 0, 0));
		}
	}

}
