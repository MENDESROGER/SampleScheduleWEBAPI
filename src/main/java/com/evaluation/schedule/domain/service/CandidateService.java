package com.evaluation.schedule.domain.service;

import org.springframework.stereotype.Service;


import com.evaluation.schedule.domain.model.Candidate;

import com.evaluation.schedule.domain.repository.CandidateRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CandidateService {
	
	private CandidateRepository candidateRepository;
	
	public Candidate findCandidate(Long candidateId) {
		return candidateRepository.findById(candidateId).orElse(null);
				
	}

}
