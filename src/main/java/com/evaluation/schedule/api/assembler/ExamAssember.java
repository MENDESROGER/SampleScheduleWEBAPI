package com.evaluation.schedule.api.assembler;

import org.springframework.stereotype.Component;

import com.evaluation.schedule.api.model.AvailabilityImput;
import com.evaluation.schedule.api.model.ExamImput;
import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Exam;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ExamAssember {

	private ModelMapper modelMapper;
	
	public Exam toEntity(ExamImput examInput) {
		return modelMapper.map(examInput, Exam.class);
	}
	
}