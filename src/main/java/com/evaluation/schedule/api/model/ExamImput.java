package com.evaluation.schedule.api.model;

import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.evaluation.schedule.domain.model.type.TypeCertification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamImput {
	
	@Valid
	@NotNull
	private  CandidateImput candidate;
	
	@Valid
	@NotNull
	private  AvailabilityIdImput availability;
	
	@NotNull
	private TypeCertification typecertification;
				
	
}
