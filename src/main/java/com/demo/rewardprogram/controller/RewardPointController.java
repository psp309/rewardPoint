package com.demo.rewardprogram.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rewardprogram.entity.TransactionRecord;
import com.demo.rewardprogram.model.CustomerDTO;
import com.demo.rewardprogram.model.MonthlyRewards;
import com.demo.rewardprogram.model.TransactionRecordDTO;
import com.demo.rewardprogram.service.CustomerService;
import com.demo.rewardprogram.service.DataTransformer;
import com.demo.rewardprogram.service.impl.TransactionService;

@RestController
@RequestMapping("/api/v1")
public class RewardPointController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DataTransformer dataTransformer;
	
	@Autowired
	private TransactionService transactionService;

	@GetMapping("rewards/customer/{customerId}")
	public MonthlyRewards rewardsByCustomer(@PathVariable(value = "customerId") Long customerId) {
		MonthlyRewards monthlyReward = null;
		CustomerDTO cust = customerService.findById(customerId);

		List<TransactionRecordDTO> threeMonthTrans = customerService.findThreeMonthTrans();
		Map<CustomerDTO, List<TransactionRecordDTO>> custTrans = dataTransformer.splitByCustomerTransactions(threeMonthTrans);
		if (custTrans.containsKey(cust)) {
			monthlyReward = dataTransformer.calculateMonthlyReport(cust, custTrans.get(cust));
		}

		return monthlyReward;

	}

	@GetMapping("/rewards")
	public List<MonthlyRewards> monthlyRewardsReport() {

		List<MonthlyRewards> ret = new ArrayList<>();

		List<TransactionRecordDTO> threeMonthTrans = customerService.findThreeMonthTrans();

		Map<CustomerDTO, List<TransactionRecordDTO>> custTrans = dataTransformer.splitByCustomerTransactions(threeMonthTrans);

		custTrans.forEach((k, v) -> {
			MonthlyRewards monthlyReward = dataTransformer.calculateMonthlyReport(k, v);

			ret.add(monthlyReward);

		});

		return ret;
	}
	
	@PostMapping("rewards/customer/{customerId}/add")
	public String addRewardsForCustomer(@PathVariable(value = "customerId") Long customerId,@RequestParam(name = "amount") String amount) {
		
		CustomerDTO cust = customerService.findById(customerId);
		
		TransactionRecord transactionRecordDTO= transactionService.createTransactionRecord(dataTransformer.fromCustomerModel(cust), amount);

		return "Transaction added successfully for customer";

	}

}