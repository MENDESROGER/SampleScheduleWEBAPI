package com.evaluation.schedule.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.schedule.api.model.AvailabilityImput;
import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.repository.AvailabilityRepository;
import com.evaluation.schedule.domain.service.AvailabilityService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/availability")
public class AvailabilityController {
	
	@Autowired
	private AvailabilityRepository availabilityRepository;
	@Autowired
	private AvailabilityService availabilityService;
		
	@GetMapping
	public List<Availability> find() {
		return availabilityRepository.findAll();
	}
	
	@GetMapping("/findOk")
	public String findOK() {
		return "Ok";
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Availability> find(@PathVariable Long availabilityId) {
		return availabilityRepository.findById(availabilityId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	


	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Availability create(@Valid @RequestBody AvailabilityImput availabilityImput) {		
		return availabilityService.save(availabilityImput);
	}
	
	

	@DeleteMapping("/{availabilityId}")
	public ResponseEntity<Void> delete(@PathVariable Long availabilityId) {
		if (!availabilityRepository.existsById(availabilityId)) {
			return ResponseEntity.notFound().build();
		}
		
		availabilityService.delete(availabilityId);
		
		return ResponseEntity.noContent().build();
	}

}
