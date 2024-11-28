package com.FMS.fmsbackend.budget.entity;

import java.time.LocalDate;
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


import com.FMS.fmsbackend.budget.Serializer.BudgetPeriodListSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "BudgetPeriod")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "periodId")
public class BudgetPeriod {
	@Id
	@SequenceGenerator(name = "BudgetPeriodId_sequence_generator", initialValue = 100000, allocationSize = 1)
	@GeneratedValue(generator = "BudgetPeriodId_sequence_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "PeriodId")
	private Long periodId;

	@Column(length = 200)
	private String PeriodName;

	private LocalDate startDate;

	private LocalDate endDate;

	@Column(length = 25)
	private String Status;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PBParent")
	 @JsonIgnoreProperties("subBudgetPeriods") //private BudgetPeriod Parent;
	private BudgetPeriod parent;


	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BudgetPeriod> subBudgetPeriods = new ArrayList<>();

	public BudgetPeriod() {
		super();
	}

	public BudgetPeriod(Long periodId, String periodName, LocalDate startDate, LocalDate endDate, String status,
			BudgetPeriod parent, List<BudgetPeriod> subBudgetPeriods) {
		super();
		this.periodId = periodId;
		PeriodName = periodName;
		this.startDate = startDate;
		this.endDate = endDate;
		Status = status;
		this.parent = parent;
		this.subBudgetPeriods = subBudgetPeriods;
	}

	public Long getPeriodId() {
		return this.periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public String getPeriodName() {
		return PeriodName;
	}

	public void setPeriodName(String periodName) {
		PeriodName = periodName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public BudgetPeriod getParent() {
		return this.parent;
	}

	public void setParent(BudgetPeriod parent) {
		this.parent = parent;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<BudgetPeriod> getSubBudgetPeriods() {
		return subBudgetPeriods;
	}

	public void setSubBudgetPeriods(List<BudgetPeriod> subBudgetPeriods) {
		this.subBudgetPeriods = subBudgetPeriods;
	}

}
