package com.evaluation.schedule.domain.utility;

import java.util.Date;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperationDate {
	
	
	public Integer DateGetYear() {
		 Date date = new Date();

	     ZoneId timeZone = ZoneId.systemDefault();
	     LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
	     return getLocalDate.getYear();
		
	}

}
