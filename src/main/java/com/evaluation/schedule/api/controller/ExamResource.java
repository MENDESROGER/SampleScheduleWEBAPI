package com.evaluation.schedule.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.evaluation.schedule.api.exception.ResourceNotFoundException;
import com.evaluation.schedule.api.model.ExamImput;
import com.evaluation.schedule.domain.model.Exam;
import com.evaluation.schedule.domain.repository.ExamRepository;
import com.evaluation.schedule.domain.service.AvailabilityService;
import com.evaluation.schedule.domain.service.ExamService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
@Path("/exam")
public class ExamResource {

	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	private ExamService examService;
	
	@GET
	@Produces("application/json")	
	@Path("/find")	
	public List<Exam> getAll(){
		return examRepository.findAll();
	}
		
	@POST
	@Consumes("application/json")	
	@Produces("application/json")
	@Path("/create")
	public Exam create(ExamImput examImput) {
		return examService.save(examImput);			
	}
	
	/* @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 @Path("/update/{id}")
	 public ResponseEntity<Exam> update(@PathParam(value = "id") Long examId,
	   @Valid @RequestBody Exam examDetails) throws ResourceNotFoundException {
	  Exam exam = examRepository.findById(examId)
	    .orElseThrow(() -> new ResourceNotFoundException("Exam not found :: " + examId));

	  exam.setName(examDetails.getName());
	  exam.setAddress(examDetails.getAddress());
	  
	  final Exam updatedExam = examRepository.save(exam);
	  return ResponseEntity.ok(updatedExam);
	 }*/
	 
	 
	 @DELETE
	 @Produces("application/json")
	 @Path("/delete/{id}")
	 public Map<String, Boolean> delete(@PathParam(value = "id") Long examId) throws ResourceNotFoundException {
		 Exam exam = examRepository.findById(examId)
	    .orElseThrow(() -> new ResourceNotFoundException("Exam not found :: " + examId));

	  examRepository.delete(exam);
	  Map<String, Boolean> response = new HashMap<>();
	  response.put("deleted", Boolean.TRUE);
	  return response;
	 }
}


