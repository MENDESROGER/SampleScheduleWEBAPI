package com.evaluation.schedule.domain.repository.inmemory;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.evaluation.schedule.domain.exception.ServiceException;
import com.evaluation.schedule.domain.model.Availability;

import lombok.AllArgsConstructor;

public class AvailabilityRepositoryExtendsInMemory extends AvailabilityRepositoryInMemory {
	
		
	public AvailabilityRepositoryExtendsInMemory() {
		super();
	}

	private Optional<Availability> allAvailability = Optional.empty();
	
	@Override
	public <S extends Availability> S save(S entity) {
					
		//Availability availability = availabilityAssember.toEntity(availabilityImput);

		boolean availabilityFound = findByAvailabilityRoom(entity.getRoom().getId(), entity.getAvailableTimeStart())
				.stream().anyMatch(availabilityExist -> !availabilityExist.getRoom().equals(entity.getRoom().getId()));

		if (availabilityFound) {		
			throw new ServiceException("There is an appointment for this room at the same time");
		}
						
		allAvailability = Optional.of(entity);						
		return entity;
	}
	
	@Override
	public Optional<Availability> findByAvailabilityRoom(Long roomId, OffsetDateTime availableTimeStart) {
					  
		
		System.out.println("igual"+allAvailability.isEmpty());
		
		return allAvailability
		.filter(availabilityExist -> availabilityExist.getRoom().getId().equals(roomId) 
				&& availabilityExist.getAvailableTimeEnd().isAfter(availableTimeStart));
	    	    	   	    	  				
	}
	

}
