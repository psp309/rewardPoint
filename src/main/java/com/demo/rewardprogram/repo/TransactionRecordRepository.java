package com.demo.rewardprogram.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.rewardprogram.entity.TransactionRecord;

@Repository
@Transactional
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {

	@Query("SELECT trans FROM TransactionRecord trans  WHERE  trans.purchaseDate >= DATEADD(MONTH, -2, CURRENT_DATE) ")
	List<TransactionRecord> findAllInLastThreeMonth();

}
