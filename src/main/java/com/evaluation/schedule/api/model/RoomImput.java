package com.evaluation.schedule.api.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomImput {
	
	@NotNull
	private Long id;

}
