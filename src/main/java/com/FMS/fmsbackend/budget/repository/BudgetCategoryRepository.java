package com.FMS.fmsbackend.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.FMS.fmsbackend.budget.entity.BudgetCategory;

public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory,Long>{

	
	   @Query(value = "SELECT * FROM db_fms.profit_cost_centers where pk_cost_centers=1", nativeQuery = true)
	   	List<BudgetCategory> findrootCategorey();
}
