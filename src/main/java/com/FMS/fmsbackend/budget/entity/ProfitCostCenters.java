package com.FMS.fmsbackend.budget.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties({ "subPCC" })

@Entity
@Table(name = "ProfitCostCenters")
public class ProfitCostCenters {

	@Id
	@SequenceGenerator(name = "CostCenters_sequence_generator", initialValue = 100000, allocationSize = 1)
	@GeneratedValue(generator = "CostCenters_sequence_generator", strategy = GenerationType.SEQUENCE)
	@Column(name = "pkCostCenters")
	private Long pkCostCenters;

	@Column(length = 50)
	private String TRN_CODE;

	@Column(length = 500)
	private String TRN_DESC;

	@Column(length = 25)
	private String Status;

	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "PCParent")
	@JsonIgnoreProperties("subPCC")
	private ProfitCostCenters parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProfitCostCenters> subPCC = new ArrayList<>();

	public ProfitCostCenters() {
		super();
	}

	public ProfitCostCenters(Long pkCostCenters, String tRN_CODE, String tRN_DESC, ProfitCostCenters parent,
			String Status, List<ProfitCostCenters> subPCC) {
		super();
		this.pkCostCenters = pkCostCenters;
		TRN_CODE = tRN_CODE;
		TRN_DESC = tRN_DESC;
		this.parent = parent;
		this.subPCC = subPCC;
		this.Status = Status;

	}

	public Long getPkCostCenters() {
		return pkCostCenters;
	}

	public void setPkCostCenters(Long pkCostCenters) {
		this.pkCostCenters = pkCostCenters;
	}

	public String getTRN_CODE() {
		return TRN_CODE;
	}

	public void setTRN_CODE(String tRN_CODE) {
		TRN_CODE = tRN_CODE;
	}

	public String getTRN_DESC() {
		return TRN_DESC;
	}

	public void setTRN_DESC(String tRN_DESC) {
		TRN_DESC = tRN_DESC;
	}

	public ProfitCostCenters getParent() {
		return parent;
	}

	public void setParent(ProfitCostCenters parent) {
		this.parent = parent;
	}

	public List<ProfitCostCenters> getSubPCC() {
		return subPCC;
	}

	public void setSubPCC(List<ProfitCostCenters> subPCC) {
		this.subPCC = subPCC;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
