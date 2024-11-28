package com.FMS.fmsbackend.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.FMS.fmsbackend.budget.entity.GLAccounts;




public interface GLAccountsRepository extends JpaRepositoryImplementation<GLAccounts, Long> {
	
	   @Query(value = "SELECT * FROM db_fms.glaccounts where glparent=?1 ", nativeQuery = true)
	   	List<GLAccounts> findBySubGLId(Long id);
	   
	   @Query(value = "SELECT * FROM db_fms.glaccounts where glparent IS NULL", nativeQuery = true)
	   	List<GLAccounts> findrootGL();
	
	

}
