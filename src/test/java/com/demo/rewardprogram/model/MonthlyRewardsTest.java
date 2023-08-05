package com.demo.rewardprogram.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.demo.rewardprogram.model.CustomerDTO;
import com.demo.rewardprogram.model.MonthlyRewards;
import com.demo.rewardprogram.model.TransactionRecordDTO;
import com.demo.rewardprogram.service.impl.TransactionService;

public class MonthlyRewardsTest {
	private TransactionService dataBuilder = new TransactionService();

	private CustomerDTO mary = dataBuilder.createCustomerDTO("Mary");

	private MonthlyRewards testClass = new MonthlyRewards(mary);

	@Test
	void test_getMonthlyTotalRewardPoint() {

		List<TransactionRecordDTO> trans = new ArrayList<>();
		trans.add(dataBuilder.createTransactionRecordDTO(mary, "90", 2, 1));
		trans.add(dataBuilder.createTransactionRecordDTO(mary, "50", 2, 1));
		testClass.getMonthlyTrans().put(Month.JUNE, trans);

		assertEquals(0, testClass.getMonthlyTotalRewardPoint().get(Month.JUNE).compareTo(new BigDecimal("40")));

	}

}
