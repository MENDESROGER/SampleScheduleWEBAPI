package com.evaluation.schedule.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.schedule.domain.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
		

}
