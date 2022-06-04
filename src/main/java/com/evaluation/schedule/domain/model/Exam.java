package com.evaluation.schedule.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.evaluation.schedule.domain.model.type.TypeCertification;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="exam")
@Setter
@Getter
public class Exam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
			
	@ManyToOne
	@JoinColumn(name="availability_id", referencedColumnName = "id")
	private Availability availability;
	
	@ManyToOne
	@JoinColumn(name="candidate_id", referencedColumnName = "id")
	private Candidate candidate;
		
	@Column(name="datescheduling")
	private LocalDateTime dateScheduling=LocalDateTime.now();
			
	@Enumerated(EnumType.STRING)
	@Column(name="typecertification")
	private TypeCertification typecertification;
	
	@Column
	private Integer codesubscription;
		
}
