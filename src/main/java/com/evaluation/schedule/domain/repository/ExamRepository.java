package com.evaluation.schedule.domain.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>  {
	
	@Query("SELECT e FROM Exam e WHERE e.candidate.id = ?1 and e.availability.id = ?2")
	Optional<Exam> findByExamRealized(Long candidateId, Long availabilityId);

}
