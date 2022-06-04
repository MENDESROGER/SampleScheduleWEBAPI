package com.evaluation.schedule.api.model.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CandidateDTO {
		
	private Long id;
	
	
	private String name;
	
	
	private String address;
	

	private OffsetDateTime dateCreateRegister;
	
}
