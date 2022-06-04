package com.evaluation.schedule.api.model;
import java.time.OffsetDateTime;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.evaluation.schedule.domain.model.type.TypeStatusAvailability;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityImput {
	@Valid
	@NotNull
	private  RoomImput room;
				
	@NotNull
	private String observation;
		
	@NotNull
	private TypeStatusAvailability status;
	
	@NotNull
	private OffsetDateTime availableTimeStart;
	
	@NotNull
	private OffsetDateTime availableTimeEnd;
	

}
