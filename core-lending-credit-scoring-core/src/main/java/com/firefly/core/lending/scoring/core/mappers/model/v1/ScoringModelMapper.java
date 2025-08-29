package com.firefly.core.lending.scoring.core.mappers.model.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.model.v1.ScoringModelDTO;
import com.firefly.core.lending.scoring.models.entities.model.v1.ScoringModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoringModelMapper {
    ScoringModelDTO toDTO(ScoringModel scoringModel);
    ScoringModel toEntity(ScoringModelDTO scoringModelDTO);
}