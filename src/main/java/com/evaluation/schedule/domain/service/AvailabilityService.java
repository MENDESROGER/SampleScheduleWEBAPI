package com.evaluation.schedule.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evaluation.schedule.api.assembler.AvailabilityAssember;
import com.evaluation.schedule.api.model.AvailabilityImput;
import com.evaluation.schedule.domain.exception.ServiceException;
import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Candidate;
import com.evaluation.schedule.domain.model.Room;
import com.evaluation.schedule.domain.repository.AvailabilityRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AvailabilityService {

	private AvailabilityRepository availabilityRepository;

	private AvailabilityAssember availabilityAssember;

	private RoomService roomService;

	public Availability findAvailability(Long availabilityId) {
		return availabilityRepository.findById(availabilityId).orElse(null);

	}

	public Availability find(Long availabilityId) {
		return availabilityRepository.findById(availabilityId)
				.orElseThrow(() -> new ServiceException("Availability not found"));
	}

	@Transactional
	public Availability save(AvailabilityImput availabilityImput) {
		Availability availability = availabilityAssember.toEntity(availabilityImput);

		boolean availabilityFound = availabilityRepository
				.findByAvailabilityRoom(availabilityImput.getRoom().getId(), availabilityImput.getAvailableTimeStart())
				.stream().anyMatch(availabilityExist -> !availabilityExist.equals(availability));

		if (availabilityFound) {
			throw new ServiceException("There is an appointment for this room at the same time");
		} else {

			Room room = roomService.searchRoom(availabilityImput.getRoom().getId());

			availability.setRoom(room);

			return availabilityRepository.save(availability);
		}
	}

	@Transactional
	public void delete(Long availabilityId) {
		availabilityRepository.deleteById(availabilityId);
	}

}
