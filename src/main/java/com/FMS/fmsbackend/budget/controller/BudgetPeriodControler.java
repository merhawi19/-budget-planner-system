package com.FMS.fmsbackend.budget.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FMS.fmsbackend.budget.entity.BudgetPeriod;
import com.FMS.fmsbackend.budget.entity.BudgetPeriod;
import com.FMS.fmsbackend.budget.repository.BudgetPeriodRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("FMS/api/v1")
public class BudgetPeriodControler {

	@Autowired
	private BudgetPeriodRepository budgetPeriodRepository;

	@GetMapping("/budgetPeriod")
	public List<BudgetPeriod> getAllBudgetPeriod() {
		return budgetPeriodRepository.findAll();
	}
	
	@GetMapping("/budgetPeriod/root")
	public List<BudgetPeriod> getBudgetPeriod() {
		System.err.println("BP.              .           -"+budgetPeriodRepository.findrootBP().size());
		return budgetPeriodRepository.findrootBP();
	}

	@PostMapping("/budgetPeriod/save")
	public ResponseEntity<String> saveBudgetPeriod(@RequestBody BudgetPeriod budgetPeriod) {
		budgetPeriodRepository.save(budgetPeriod);
		return ResponseEntity.ok("budget Period saved successfully.");
	}
	
	
	
	
	@GetMapping("/budgetPeriod/subSubGL/{id}")
	public List<BudgetPeriod> getAllsubSubBudgetPeriod(@PathVariable Long id) {
		 List<BudgetPeriod> subBudgetPeriod=budgetPeriodRepository.findsubBP(id);
				return subBudgetPeriod;
	}
}
