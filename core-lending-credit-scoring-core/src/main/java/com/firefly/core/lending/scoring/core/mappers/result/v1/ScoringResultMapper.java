package com.firefly.core.lending.scoring.core.mappers.result.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.result.v1.ScoringResultDTO;
import com.firefly.core.lending.scoring.models.entities.result.v1.ScoringResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoringResultMapper {
    ScoringResultDTO toDTO(ScoringResult scoringResult);
    ScoringResult toEntity(ScoringResultDTO scoringResultDTO);
}
