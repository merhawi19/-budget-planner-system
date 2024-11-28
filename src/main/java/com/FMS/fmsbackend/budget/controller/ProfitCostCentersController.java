package com.FMS.fmsbackend.budget.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.FMS.fmsbackend.budget.entity.ProfitCostCenters;
import com.FMS.fmsbackend.budget.repository.ProfitCostCentersRepository;
import com.FMS.fmsbackend.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("FMS/api/v1")
public class ProfitCostCentersController {

	@Autowired
	private ProfitCostCentersRepository profitCostCentersRepository;

	@GetMapping("/profitCostCenters")
	public List<ProfitCostCenters> getAllProfitCostCenters() {
		return profitCostCentersRepository.findAll();
	}

	@GetMapping("/profitCostCenters/root")
	public List<ProfitCostCenters> getProfitCostCenters() {
		System.err.println(".               .          PCCenter ...........    ."
				+ profitCostCentersRepository.findrootPCCenter().size());
		return profitCostCentersRepository.findrootPCCenter();
	}

	@PostMapping("/profitCostCenters/save")
	public ResponseEntity<String> saveProfitCostCenters(@RequestBody ProfitCostCenters profitCostCenters) {
		profitCostCentersRepository.save(profitCostCenters);
		return ResponseEntity.ok(" profit Cost Centers saved successfully.");
	}

	@PutMapping("profitCostCenters/{id}")
	public ResponseEntity<ProfitCostCenters> updatePCC(@PathVariable long id,
			@RequestBody ProfitCostCenters pccdetails) {
		System.err.println(pccdetails.getTRN_CODE()+".      id     ."+id);
		ProfitCostCenters costCenters = profitCostCentersRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(("\"Department not found with this Id :") + id));
		costCenters.setTRN_CODE(pccdetails.getTRN_CODE());
		costCenters.setTRN_DESC(pccdetails.getTRN_DESC());
		System.err.println(costCenters.getTRN_CODE()+".      id     ."+pccdetails.getTRN_DESC());

		ProfitCostCenters updateDepartment = profitCostCentersRepository.save(costCenters);
		return ResponseEntity.ok(updateDepartment);

	}
}
