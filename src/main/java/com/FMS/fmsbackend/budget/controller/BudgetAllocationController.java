package com.FMS.fmsbackend.budget.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FMS.fmsbackend.budget.Service.BudgetService;
import com.FMS.fmsbackend.budget.entity.BudgetAllocation;
import com.FMS.fmsbackend.budget.repository.BudgetAllocationRepository;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("FMS/api/v1")
public class BudgetAllocationController {


	@Autowired
	private BudgetAllocationRepository budgetAllocationRepository;
	@Autowired
	private BudgetService budgetService;

	@GetMapping("/budgetAllocation")
	public List<BudgetAllocation> getAllBudgetAllocation() {
		return budgetAllocationRepository.findAll();
	}
	    @PostMapping("/budgetAllocation/save")
    public ResponseEntity<String> saveBudgetAllocation(@RequestBody BudgetAllocation budgetAllocation) {
	    	
	    	System.out.println(".......       BudgetAllocation                         ..");
	    	
	    	System.out.println(budgetAllocation.getAmountAllocated());
	    	System.out.println(budgetAllocation.getBudgetPeriod().getPeriodId());
	    	System.out.println(budgetAllocation.getCostCenters().getPkCostCenters());
	    	System.out.println("Gl .........        ."+budgetAllocation.getGl().getPkGLAccounts());



	    	budgetService.saveBudget(budgetAllocation);
	    	//budgetAllocationRepository.save(budgetAllocation);
	    	System.out.println(".......       BudgetAllocation                         ..");
        return ResponseEntity.ok("budget Allocation saved successfully.");
    }
}
