package com.evaluation.schedule.api.assembler;

import org.springframework.stereotype.Component;

import com.evaluation.schedule.api.model.AvailabilityImput;
import com.evaluation.schedule.domain.model.Availability;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AvailabilityAssember {

	private ModelMapper modelMapper;
	
	/*public EntregaModel toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}*/
	
	/*public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
		return entregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}*/
	
	public Availability toEntity(AvailabilityImput availabilityInput) {
		return modelMapper.map(availabilityInput, Availability.class);
	}
	
}