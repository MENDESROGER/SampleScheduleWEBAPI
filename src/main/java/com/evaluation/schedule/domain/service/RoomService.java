package com.evaluation.schedule.domain.service;

import org.springframework.stereotype.Service;

import com.evaluation.schedule.domain.model.Room;
import com.evaluation.schedule.domain.repository.RoomRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoomService {
	
	private RoomRepository roomRepository;
	
	public Room searchRoom(Long availabilityId) {
		return roomRepository.findById(availabilityId).orElse(null);
				
	}

}
