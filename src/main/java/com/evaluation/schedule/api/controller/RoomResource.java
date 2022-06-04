package com.evaluation.schedule.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.evaluation.schedule.api.exception.ResourceNotFoundException;
import com.evaluation.schedule.domain.model.Room;
import com.evaluation.schedule.domain.repository.RoomRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
@Path("/room")
public class RoomResource {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@GET
	@Produces("application/json")	
	@Path("/find")	
	public List<Room> getAll(){
		return roomRepository.findAll();
	}
		
	@POST
	@Consumes("application/json")	
	@Produces("application/json")
	@Path("/create")
	public Room create(Room room) {
		return roomRepository.save(room);			
	}
	
	 @PUT
	 @Consumes("application/json")
	 @Produces("application/json")
	 @Path("/update/{id}")
	 public ResponseEntity<Room> update(@PathParam(value = "id") Long roomId,
	   @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
	  Room room = roomRepository.findById(roomId)
	    .orElseThrow(() -> new ResourceNotFoundException("Room not found :: " + roomId));

	  room.setName(roomDetails.getName());
	  room.setAddress(roomDetails.getAddress());
	  
	  final Room updatedRoom = roomRepository.save(room);
	  return ResponseEntity.ok(updatedRoom);
	 }
	 
	 
	 @DELETE
	 @Produces("application/json")
	 @Path("/delete/{id}")
	 public Map<String, Boolean> delete(@PathParam(value = "id") Long roomId) throws ResourceNotFoundException {
		 Room room = roomRepository.findById(roomId)
	    .orElseThrow(() -> new ResourceNotFoundException("Room not found :: " + roomId));

	  roomRepository.delete(room);
	  Map<String, Boolean> response = new HashMap<>();
	  response.put("deleted", Boolean.TRUE);
	  return response;
	 }

}
