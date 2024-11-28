package com.FMS.fmsbackend.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.FMS.fmsbackend.budget.entity.BudgetAllocation;

public interface BudgetAllocationRepository extends JpaRepository<BudgetAllocation, Long>{
	
	 @Query(value = "SELECT * FROM db_fms.budget_allocation where  glaccounts=?1 and  budget_period=?2  or glaccounts=?1 and cost_centers=?3  or budget_period=?2 and cost_centers=?3 ;", nativeQuery = true)
	   	List<BudgetAllocation> findByGLAndBudgetPeriodOrGLAndCostCenterOrBudgetPeriodAndCostCenter(Long gl,Long bp,Long cp);

	 @Query(value = "SELECT * FROM db_fms.budget_allocation where budget_period=?2 and cost_centers=?3 and glaccounts=?1", nativeQuery = true)
	   	List<BudgetAllocation> findByGLAndBudgetPeriodAndCostCenter(Long gl,Long bp,Long cp);

}
