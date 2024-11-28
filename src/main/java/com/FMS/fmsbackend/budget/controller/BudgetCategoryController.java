package com.FMS.fmsbackend.budget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FMS.fmsbackend.budget.entity.BudgetCategory;
import com.FMS.fmsbackend.budget.repository.BudgetCategoryRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("FMS/api/v1")
public class BudgetCategoryController {
	
	@Autowired
	private BudgetCategoryRepository budgetCategoryRepository;

	@GetMapping("/budgetCategory")
	public List<BudgetCategory> getAllBudgetCategory() {
		return budgetCategoryRepository.findAll();
	}
	
	
	@GetMapping("/budgetCategory/root")
	public List<BudgetCategory> getBudgetCategory() {
		return budgetCategoryRepository.findAll();
	}
	  
	    @PostMapping("/budgetCategory/save")
    public ResponseEntity<String> saveBudgetCategory(@RequestBody BudgetCategory budgetCategory) {
	    	budgetCategoryRepository.save(budgetCategory);
        return ResponseEntity.ok("Budget Category saved successfully.");
    }

}
