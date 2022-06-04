package com.evaluation.schedule.controller;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.evaluation.schedule.api.controller.AvailabilityController;
import com.evaluation.schedule.api.model.AvailabilityImput;
import com.evaluation.schedule.api.model.RoomImput;
import com.evaluation.schedule.domain.model.Availability;
import com.evaluation.schedule.domain.model.Room;
import com.evaluation.schedule.domain.model.type.TypeStatusAvailability;
import com.evaluation.schedule.domain.repository.AvailabilityRepository;
import com.evaluation.schedule.domain.repository.inmemory.AvailabilityRepositoryExtendsInMemory;
import com.evaluation.schedule.domain.repository.inmemory.AvailabilityRepositoryInMemory;
import com.evaluation.schedule.domain.service.AvailabilityService;


@SpringBootTest
@AutoConfigureMockMvc
public class AvailabilityResourceTest {

	@Autowired
	private AvailabilityController availabilityController;
		
	@MockBean
	private AvailabilityRepository availabilityRepository;
	
	@MockBean
	@Autowired
	private AvailabilityRepositoryExtendsInMemory availabilityRepositoryExtendsInMemory;
	
	@MockBean
	private AvailabilityService availabilityService;
		
	
	@Autowired
	MockMvc mockMvc;
	
	protected OffsetDateTime parse(String offsetDateTimeAsString) {
		 return OffsetDateTime.parse(offsetDateTimeAsString);
     }
	
	@Test void candidateTestGetAll() throws Exception {
		mockMvc.perform(get("/availability/findOk")).andExpect(status().isOk());
	}
	
			
			
	@Test
	void createAvailabilityDuplicate() throws Exception {
		
		Availability availability = new Availability();
		availabilityRepositoryExtendsInMemory = new AvailabilityRepositoryExtendsInMemory();
		
		Room room = new Room();
		
		room.setId(Long.parseLong("1"));
				
		availability.setAvailableTimeStart(parse("2022-06-02T00:38:00.5448817Z"));
		availability.setAvailableTimeEnd(parse("2022-06-03T01:38:00.5448817Z"));
		availability.setRoom(room);
		
		availabilityRepositoryExtendsInMemory.save(availability);
		
		room = new Room();
		room.setId(Long.parseLong("2"));
		
		availability = new Availability();		
		availability.setAvailableTimeStart(parse("2022-06-02T00:38:00.5448817Z"));
		availability.setAvailableTimeEnd(parse("2022-06-03T01:38:00.5448817Z"));
		availability.setRoom(room);
									
		assertEquals(availabilityRepositoryExtendsInMemory.save(availability).getId(),availability.getId());
							
	}
	
		
 }



