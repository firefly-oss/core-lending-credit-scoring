package com.catalis.core.lending.scoring.core.mappers.request.v1;

import com.catalis.core.lending.scoring.interfaces.dtos.request.v1.ScoringRequestDTO;
import com.catalis.core.lending.scoring.models.entities.request.v1.ScoringRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoringRequestMapper {
    ScoringRequestDTO toDTO(ScoringRequest scoringRequest);
    ScoringRequest toEntity(ScoringRequestDTO scoringRequestDTO);
}
