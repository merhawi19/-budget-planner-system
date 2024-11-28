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


import com.FMS.fmsbackend.budget.Serializer.GLAccountsSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Entity
@Table(name = "GLAccounts")
public class GLAccounts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLAccounts_sequence_generator")
    @SequenceGenerator(name = "GLAccounts_sequence_generator", sequenceName = "gl_accounts_sequence", initialValue = 100000, allocationSize = 1)
    @Column(name = "pkGLAccounts")
    private Long pkGLAccounts;

    @Column(name = "GL_Code")
    private Long glCode;
    
    @Column(name = "GL_DESC", length = 500)
    private String glDescription;
    
    @Column(name = "CCY", length = 50)
    private String ccy;
    
	@Column(length = 25)
	private String Status; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GLParent")
    @JsonIgnoreProperties("subGLAccounts")
    private GLAccounts parentGLAccount;

    @OneToMany(mappedBy = "parentGLAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GLAccounts> subGLAccounts = new ArrayList<>();
    
    	public GLAccounts() {
		super();
	}

	public GLAccounts(Long pkGLAccounts, Long glCode, String glDescription, String currency, GLAccounts parentGLAccount,String Status,
			List<GLAccounts> subGLAccounts) {
		super();
		this.pkGLAccounts = pkGLAccounts;
		this.glCode = glCode;
		this.glDescription = glDescription;
		this.ccy = currency;
		this.parentGLAccount = parentGLAccount;
		this.subGLAccounts = subGLAccounts;
		this.Status= Status;

	}

	public Long getPkGLAccounts() {
		return pkGLAccounts;
	}

	public void setPkGLAccounts(Long pkGLAccounts) {
		this.pkGLAccounts = pkGLAccounts;
	}

	public Long getGlCode() {
		return glCode;
	}

	public void setGlCode(Long glCode) {
		this.glCode = glCode;
	}

	public String getGlDescription() {
		return glDescription;
	}

	public void setGlDescription(String glDescription) {
		this.glDescription = glDescription;
	}

	
	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public GLAccounts getParentGLAccount() {
		return parentGLAccount;
	}

	public void setParentGLAccount(GLAccounts parentGLAccount) {
		this.parentGLAccount = parentGLAccount;
	}

	public List<GLAccounts> getSubGLAccounts() {
		return subGLAccounts;
	}

	public void setSubGLAccounts(List<GLAccounts> subGLAccounts) {
		this.subGLAccounts = subGLAccounts;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
    
   
}
