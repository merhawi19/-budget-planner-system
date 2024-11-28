package com.FMS.fmsbackend.budget.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BudgetAllocation")
@JsonIgnoreProperties({ "subBudgetAllocation" })
public class BudgetAllocation {
	@Column(name = "pkAllocationId")
	@Id
	@SequenceGenerator(name = "allocationId_sequence_generator", initialValue = 100000, allocationSize = 1)
	@GeneratedValue(generator = "allocationId_sequence_generator", strategy = GenerationType.SEQUENCE)
	private Long allocationId;

	private BigDecimal amountAllocated;

	@ManyToOne
	@JoinColumn(name = "CostCenters")
	private ProfitCostCenters costCenters;

	@ManyToOne
	@JoinColumn(name = "GLAccounts")
	private GLAccounts gl;

	@ManyToOne
	@JoinColumn(name = "BudgetPeriod")
	private BudgetPeriod budgetPeriod;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BAParent")
	@JsonIgnoreProperties("subBudgetAllocations") // private BudgetPeriod Parent;
	private BudgetAllocation parentBudgetAllocations;
	
	@OneToMany(mappedBy = "parentBudgetAllocations", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BudgetAllocation> subBudgetAllocations = new ArrayList<>();

	public BudgetAllocation() {
		super();
	}

	

	public BudgetAllocation(Long allocationId, BigDecimal amountAllocated, ProfitCostCenters costCenters, GLAccounts gl,
			BudgetPeriod budgetPeriod, BudgetAllocation parentBudgetAllocations,
			List<BudgetAllocation> subBudgetAllocations) {
		super();
		this.allocationId = allocationId;
		this.amountAllocated = amountAllocated;
		this.costCenters = costCenters;
		this.gl = gl;
		this.budgetPeriod = budgetPeriod;
		this.parentBudgetAllocations = parentBudgetAllocations;
		this.subBudgetAllocations = subBudgetAllocations;
	}



	public Long getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Long allocationId) {
		this.allocationId = allocationId;
	}

	public BigDecimal getAmountAllocated() {
		return amountAllocated;
	}

	public void setAmountAllocated(BigDecimal amountAllocated) {
		this.amountAllocated = amountAllocated;
	}

	public ProfitCostCenters getCostCenters() {
		return this.costCenters;
	}

	public void setCostCenters(ProfitCostCenters costCenters) {
		this.costCenters = costCenters;
	}

	public BudgetAllocation getParentBudgetAllocations() {
		return parentBudgetAllocations;
	}
	public void setParentBudgetAllocations(BudgetAllocation parentBudgetAllocations) {
		this.parentBudgetAllocations = parentBudgetAllocations;
	}
	public GLAccounts getGl() {
		return gl;
	}

	public void setGl(GLAccounts gl) {
		this.gl = gl;
	}

	public BudgetPeriod getBudgetPeriod() {
		return this.budgetPeriod;
	}

	public void setBudgetPeriod(BudgetPeriod budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}

	public List<BudgetAllocation> getSubBudgetAllocations() {
		return subBudgetAllocations;
	}

	public void setSubBudgetAllocations(List<BudgetAllocation> subBudgetAllocations) {
		this.subBudgetAllocations = subBudgetAllocations;
	}

}
