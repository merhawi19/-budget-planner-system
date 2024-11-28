package com.FMS.fmsbackend.budget.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.FMS.fmsbackend.budget.entity.GLAccounts;
import com.FMS.fmsbackend.budget.repository.GLAccountsRepository;

import jakarta.persistence.EntityNotFoundException;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/FMS/api/v1")
public class GLAccountsController {
	@Autowired
	private GLAccountsRepository glAccountsRepository;

	@GetMapping("/GLAccounts")
	public List<GLAccounts> getAllGLAccounts() {
		System.err.println(".                            eorr");

		System.err.println("size               ."+glAccountsRepository.findAll().size()+".               .");
		return glAccountsRepository.findAll();
	}
	
	@GetMapping("/GLAccounts/root")
	public List<GLAccounts> getrootGLAccounts() {
		System.err.println(".                            eorr");
	
		System.err.println("size        root       ."+glAccountsRepository.findrootGL().size()+".               .");
		return glAccountsRepository.findrootGL();
	}
	
	
	@GetMapping("/GLAccounts/subSubGL/{id}")
	public List<GLAccounts> getAllsubSubGLAccounts(@PathVariable Long id) {
			System.err.println("size               ."+glAccountsRepository.findAll().size()+".               .");
		return glAccountsRepository.findAll();
	}
	
	    @PostMapping("/GLAccounts/save")
    public ResponseEntity<String> saveGLAccounts(@RequestBody GLAccounts glAccounts) {
	    	System.err.println("eorr                     ....");
	    	System.err.println(glAccounts.getGlCode()+" .       .  "+glAccounts.getParentGLAccount().getPkGLAccounts());
    	glAccountsRepository.save(glAccounts);
        return ResponseEntity.ok("GL Accounts saved successfully.");
    }
	    
		
		@GetMapping("GLAccounts/delete/{id}")
		public ResponseEntity<GLAccounts> deletedepartment(@PathVariable long id){
			System.err.println(id);
		
	    	GLAccounts glAccounts = glAccountsRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
	    	glAccounts.setStatus("Delete");

			GLAccounts updateDepartment = glAccountsRepository.save(glAccounts);
			return ResponseEntity.ok(updateDepartment);
		}
		

		@DeleteMapping("GLAccounts/{id}")
	    public void deleteExampleEntity(@PathVariable Long id) {
	        // Check if the entity exists
	        if (glAccountsRepository.existsById(id)) {
	        	GLAccounts glAccounts = glAccountsRepository.findById(id)
	                    .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));

	            // Delete the entity (and its related entities due to cascading, if configured)
	        	glAccountsRepository.delete(glAccounts);
	        } else {
	            // Handle case where entity with given id does not exist
	            throw new EntityNotFoundException("Entity with id " + id + " not found");
	        }
	    }
}
