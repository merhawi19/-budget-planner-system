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



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "BudgetPlan")
@JsonIgnoreProperties({ "subBudgetPlan" })
public class BudgetCategory {
	@Column(name = "pkGLAccounts")
	@Id
	@JsonIgnore
	@SequenceGenerator(name = "categoryId_sequence_generator", initialValue = 100000, allocationSize = 1)
	@GeneratedValue(generator = "categoryId_sequence_generator", strategy = GenerationType.SEQUENCE)
	private Long categoryId;
	
	@Column(length = 100)
	private String categoryName;
	
	@Column(length = 500)
	String category_DESC;
	
	@ManyToOne
	@JoinColumn(name = "Parent")
	private BudgetCategory Parent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Parent")
	private List<BudgetCategory> subCategory = new ArrayList<>();

	public BudgetCategory(Long categoryId, String categoryName, String category_DESC, BudgetCategory parent,
			List<BudgetCategory> subCategory) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.category_DESC = category_DESC;
		Parent = parent;
		this.subCategory = subCategory;
	}

	public BudgetCategory() {
		super();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategory_DESC() {
		return category_DESC;
	}

	public void setCategory_DESC(String category_DESC) {
		this.category_DESC = category_DESC;
	}

	public BudgetCategory getParent() {
		return Parent;
	}

	public void setParent(BudgetCategory parent) {
		Parent = parent;
	}

	public List<BudgetCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<BudgetCategory> subCategory) {
		this.subCategory = subCategory;
	}
	
	
	
	

}
