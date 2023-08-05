package com.demo.rewardprogram.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.demo.rewardprogram.model.TransactionRecordDTO;
import com.demo.rewardprogram.service.impl.TransactionService;

class TransactionRecordTest {

	private TransactionService testData = new TransactionService();

	@Test
	void test_calculateRewardPoint_lessThan_51() {
		TransactionRecordDTO trans = testData.createTransactionRecordDTO(null, "50", 2, 2);

		assertEquals(0, trans.getRewardPoint().compareTo(new BigDecimal("0")));
	}

	@Test
	void test_calculateRewardPoint_moreThan_100() {
		TransactionRecordDTO trans = testData.createTransactionRecordDTO(null, "120", 2, 2);

		assertEquals(0, trans.getRewardPoint().compareTo(new BigDecimal("90")));
	}

	@Test
	void test_calculateRewardPoint_moreThan_50() {
		TransactionRecordDTO trans = testData.createTransactionRecordDTO(null, "70", 2, 2);

		assertEquals(0, trans.getRewardPoint().compareTo(new BigDecimal("20")));
	}

}
