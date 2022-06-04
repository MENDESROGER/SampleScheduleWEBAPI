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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evaluation.schedule.api.exception.ResourceNotFoundException;
import com.evaluation.schedule.domain.model.Candidate;
import com.evaluation.schedule.domain.repository.CandidateRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Component
@Path("/candidate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Hello API - say hello to the world", produces = "application/json")
public class CandidateResource {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@GET
	@Produces("application/json")	
	@Path("/find")	
	@ApiOperation(            //Swagger Annotation
            value = "Say hello by entering your name",
            response = Candidate.class)
    @ApiResponses(value = {       //Swagger Annotation
            
    })
	public List<Candidate> getAll(){
		return candidateRepository.findAll();
	}
	
	
	@GET	
	@Path("/findOk")	
	public String findOk(){
		return "Ok";
	}
	
	@POST
	@Consumes("application/json")	
	@Produces("application/json")
	@Path("/create")
	public Candidate create(Candidate candidate) {
		return candidateRepository.save(candidate);			
	}
	
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 @Path("/update/{id}")
	 public ResponseEntity<Candidate> update(@PathParam(value = "id") Long candidateId,
	   @Valid @RequestBody Candidate candidateDetails) throws ResourceNotFoundException {
	  Candidate candidate = candidateRepository.findById(candidateId)
	    .orElseThrow(() -> new ResourceNotFoundException("Candidate not found :: " + candidateId));

	  candidate.setName(candidateDetails.getName());
	  candidate.setAddress(candidate.getAddress());
	  
	  final Candidate updatedCandidate = candidateRepository.save(candidate);
	  return ResponseEntity.ok(updatedCandidate);
	 }
	 
	 
	 @DELETE
	 @Path("/delete/{id}")
	 public Map<String, Boolean> deleteUser(@PathParam(value = "id") Long candidateId) throws ResourceNotFoundException {
		 Candidate candidate = candidateRepository.findById(candidateId)
	    .orElseThrow(() -> new ResourceNotFoundException("Candidate not found :: " + candidateId));

	  candidateRepository.delete(candidate);
	  Map<String, Boolean> response = new HashMap<>();
	  response.put("deleted", Boolean.TRUE);
	  return response;
	 }

}
