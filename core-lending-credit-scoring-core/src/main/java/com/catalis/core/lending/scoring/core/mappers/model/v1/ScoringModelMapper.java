package com.catalis.core.lending.scoring.core.mappers.model.v1;

import com.catalis.core.lending.scoring.interfaces.dtos.model.v1.ScoringModelDTO;
import com.catalis.core.lending.scoring.models.entities.model.v1.ScoringModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoringModelMapper {
    ScoringModelDTO toDTO(ScoringModel scoringModel);
    ScoringModel toEntity(ScoringModelDTO scoringModelDTO);
}