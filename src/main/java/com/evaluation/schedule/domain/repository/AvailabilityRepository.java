package com.evaluation.schedule.domain.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Room;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long>{
	
	@Query("SELECT a FROM Availability a WHERE a.room.id = ?1 and a.availableTimeEnd > ?2")
	Optional<Availability> findByAvailabilityRoom(Long roomId, OffsetDateTime availableTimeStart);


}
