package com.FMS.fmsbackend.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.FMS.fmsbackend.budget.entity.BudgetPeriod;




public interface BudgetPeriodRepository extends JpaRepositoryImplementation<BudgetPeriod,Long>{

	   @Query(value = "SELECT * FROM db_fms.budget_period where Period_id=1", nativeQuery = true)
	   	List<BudgetPeriod> findrootBP();
	   
	   @Query(value = "SELECT * FROM db_fms.budget_period where Period_id=?1", nativeQuery = true)
	   	List<BudgetPeriod> findsubBP(Long id);
}
