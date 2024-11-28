package com.FMS.fmsbackend.budget.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.FMS.fmsbackend.budget.entity.BudgetAllocation;
import com.FMS.fmsbackend.budget.entity.BudgetPeriod;
import com.FMS.fmsbackend.budget.repository.BudgetAllocationRepository;
import com.FMS.fmsbackend.budget.repository.BudgetPeriodRepository;
import com.FMS.fmsbackend.budget.repository.GLAccountsRepository;
import com.FMS.fmsbackend.budget.repository.ProfitCostCentersRepository;
import com.mysql.cj.x.protobuf.MysqlxCrud.FindOrBuilder;
import com.FMS.fmsbackend.budget.entity.GLAccounts;
import com.FMS.fmsbackend.budget.entity.ProfitCostCenters;



@Service
public class BudgetService {

    private final BudgetAllocationRepository budgetRepository;
    private final ProfitCostCentersRepository costCentersRepository;
    private final GLAccountsRepository glRepository;
    private final BudgetPeriodRepository periodRepository;
    

    public BudgetService(BudgetAllocationRepository budgetRepository, ProfitCostCentersRepository costRepository, BudgetPeriodRepository periodRepository, GLAccountsRepository glRepository) {
        this.budgetRepository = budgetRepository;
		this.costCentersRepository = costRepository;
		this.glRepository = glRepository;
		this.periodRepository = periodRepository;
    }

    public void saveBudget(BudgetAllocation budget) {
        // Check for existing budget records with the same values for two out of the three fields
    	
    	System.err.println("GL .      ."+budget.getGl().getPkGLAccounts()+".            BP..  ."+ budget.getBudgetPeriod().getPeriodId()+ ".           .cp"+budget.getCostCenters().getPkCostCenters());
        List<BudgetAllocation> existingBudgets = budgetRepository.findByGLAndBudgetPeriodOrGLAndCostCenterOrBudgetPeriodAndCostCenter(
                budget.getGl().getPkGLAccounts(), budget.getBudgetPeriod().getPeriodId(), budget.getCostCenters().getPkCostCenters());
        
        System.out.println(existingBudgets.size());
        HashSet<Long> gl= new HashSet<Long>(); 
        HashSet<Long> bp= new HashSet<Long>();
        HashSet<Long> cp= new HashSet<Long>(); 
        if (!existingBudgets.isEmpty()) {
            long thirdFieldValue = 0;
            System.out.println(" thirdFieldValue ");
            
            for (BudgetAllocation existingBudget : existingBudgets) {
            // Determine the third field's value in the new budget record
            if (!Objects.equals(existingBudget.getGl().getPkGLAccounts(), budget.getGl().getPkGLAccounts())) {
            	gl.add( existingBudget.getGl().getPkGLAccounts());
            	System.out.println( "Gl Servce"+existingBudget.getGl().getPkGLAccounts());

            } else if (!Objects.equals(existingBudget.getBudgetPeriod().getPeriodId(), budget.getBudgetPeriod().getPeriodId())) {
            	bp.add( existingBudget.getBudgetPeriod().getPeriodId());
            	System.out.println("BP Service"+existingBudget.getBudgetPeriod().getPeriodId());


            } else if (!Objects.equals(existingBudget.getCostCenters().getPkCostCenters(), budget.getCostCenters().getPkCostCenters())) {
            	cp.add(existingBudget.getCostCenters().getPkCostCenters());
            	
            	System.out.println("CP Service"+existingBudget.getCostCenters().getPkCostCenters());
            }
            
            }
            
            System.err.println(gl.size()+".        ."+bp.size()+ ".           ." +cp.size() );

            // Determine if the third field's value is a parent or child to the existing records
            if (gl.size() != 0||bp.size() != 0||cp.size() != 0) {
                Long parentBudgetId = determineParentBudgetId(gl, bp, cp, budget);
                
                System.out.println(parentBudgetId);
                if(parentBudgetId!=null)
                { 
                	System.err.println(parentBudgetId);
                	BudgetAllocation paretBudgetAllocation=budgetRepository.findById(parentBudgetId).get();
                	budget.setParentBudgetAllocations(paretBudgetAllocation);
                }
                 }
                }

        try {
            // Save the budget
            budgetRepository.save(budget);
        } catch (DataIntegrityViolationException e) {
            // Handle the constraint violation error here
            throw new RuntimeException("Budget allocation already exists.");
        }
    }

    private Long determineParentBudgetId(Set<Long> glSet, Set<Long> bpSet, Set<Long> cpSet, BudgetAllocation budget) {
        // Create a list to store all potential parent budget IDs

        // Add all values from glSet, bpSet, and cpSet to the list
        if(!glSet.isEmpty()) {
    		GLAccounts reqGl=glRepository.findById(budget.getGl().getPkGLAccounts()).get();
    		Long parentId=reqGl.getParentGLAccount().getPkGLAccounts();
System.out.println("requsting .         .          .."+parentId);
        	for(Long id:glSet) {
        		System.err.println("Potentiol parent Id .    ."+id);
        		/*	Optional<GLAccounts> gl= glRepository.findById(id);
        	
        		if(gl.get().getParentGLAccount().getPkGLAccounts().equals(budget.getGl().getPkGLAccounts()))
        		{
        			System.out.println("this is parent Gl");
        		}
        		*/
        		if(parentId.equals(id))
        		{
        			return budgetRepository.findByGLAndBudgetPeriodAndCostCenter(id,budget.getBudgetPeriod().getPeriodId(),budget.getCostCenters().getPkCostCenters()).get(0).getAllocationId();
        		}
        		 
        		
        		
        	}
        }
        
        if(!bpSet.isEmpty()) {
        	BudgetPeriod reqBp=periodRepository.findById(budget.getBudgetPeriod().getPeriodId()).get();

        	for(Long id:bpSet) {
        		
        		Optional<BudgetPeriod> bp= periodRepository.findById(id);
        		
        		if(bp.get().getParent().getPeriodId().equals(budget.getBudgetPeriod().getPeriodId()))
        		{
        			System.out.println("this is parent BP");
        		}
        		
        		if(reqBp.getParent().getPeriodId().equals(bp.get().getPeriodId()))
        		{
        			return budgetRepository.findByGLAndBudgetPeriodAndCostCenter(budget.getGl().getPkGLAccounts(),id,budget.getCostCenters().getPkCostCenters()).get(0).getAllocationId();
        		}
        	}
        }
        
        if(!cpSet.isEmpty()) {
        	ProfitCostCenters reqcp=costCentersRepository.findById(budget.getBudgetPeriod().getPeriodId()).get();

        	for(Long id:bpSet) {
        		
        		Optional<ProfitCostCenters> bp= costCentersRepository.findById(id);
        		
        		if(bp.get().getParent().getPkCostCenters().equals(budget.getBudgetPeriod().getPeriodId()))
        		{
        			System.out.println("this is parent BP");
        		}
        		
        		if(reqcp.getParent().getPkCostCenters().equals(bp.get().getPkCostCenters()))
        		{
        			return budgetRepository.findByGLAndBudgetPeriodAndCostCenter(budget.getGl().getPkGLAccounts(),budget.getBudgetPeriod().getPeriodId(),id).get(0).getAllocationId();
        		}
        	}
        }
           return  null;
    }

    
    
}
