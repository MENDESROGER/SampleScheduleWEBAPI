package com.evaluation.schedule.domain.model;


import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.evaluation.schedule.domain.model.type.TypeStatusAvailability;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;



@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@Table(name="availability")
public class Availability {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Room room;
		
	@Column
	private String observation;
		
	@Enumerated(EnumType.STRING)
	private TypeStatusAvailability status;
	
	@Column(name="availabletimestart")
	private OffsetDateTime availableTimeStart;
	
	@Column(name="availabletimeend")
	private OffsetDateTime availableTimeEnd;
		
					
}
