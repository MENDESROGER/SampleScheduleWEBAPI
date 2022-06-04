package com.evaluation.schedule.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluation.schedule.api.assembler.AvailabilityAssember;
import com.evaluation.schedule.api.assembler.ExamAssember;
import com.evaluation.schedule.api.model.AvailabilityImput;
import com.evaluation.schedule.api.model.ExamImput;
import com.evaluation.schedule.domain.exception.ServiceException;
import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Candidate;
import com.evaluation.schedule.domain.model.Exam;
import com.evaluation.schedule.domain.model.Room;
import com.evaluation.schedule.domain.repository.AvailabilityRepository;
import com.evaluation.schedule.domain.repository.CandidateRepository;
import com.evaluation.schedule.domain.repository.ExamRepository;
import com.evaluation.schedule.domain.utility.OperationDate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ExamService {
	
	private ExamRepository examRepository;
	
	private CandidateRepository candidateRepository;
	
	private AvailabilityRepository availabilityRepository;
	
	private CandidateService candidateService;
	
	private AvailabilityService availabilityService;

	private ExamAssember examAssember;
	
	private OperationDate operationDate;
		
	
	@Transactional
	public Exam save(ExamImput examImput) {
		Exam exam = examAssember.toEntity(examImput);
		
		
		boolean examRealized = examRepository
				.findByExamRealized(examImput.getCandidate().getId(), examImput.getAvailability().getId()).isEmpty();

		if (!examRealized) {
			throw new ServiceException("Candidate cannot be scheduled for same availability");
		} 
		
		
		Candidate candidate = candidateService.findCandidate(examImput.getCandidate().getId());
		
									
		if (candidate==null) {
			throw new ServiceException("Candidate is not found");
		}							
		
		exam.setCandidate(candidate);					
		
				
		Availability availability = availabilityService.findAvailability(examImput.getAvailability().getId());
		
		if (availability==null) {
			throw new ServiceException("Availability is not found");
		}
		
		exam.setAvailability(availability);
		
		exam=examRepository.save(exam);
			
		exam.setCodesubscription(generateRegistrationCode(exam.getId()));
						
		return examRepository.save(exam);
		
	}
	
	private Integer generateRegistrationCode(Long idExam) {	
				
		return Integer.parseInt(""+operationDate.DateGetYear()+""+idExam);				
	}
		
}
