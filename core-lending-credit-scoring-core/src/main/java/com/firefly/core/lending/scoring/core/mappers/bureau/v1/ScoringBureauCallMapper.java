package com.firefly.core.lending.scoring.core.mappers.bureau.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.bureau.v1.ScoringBureauCallDTO;
import com.firefly.core.lending.scoring.models.entities.bureau.v1.ScoringBureauCall;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoringBureauCallMapper {
    ScoringBureauCallDTO toDTO(ScoringBureauCall scoringBureauCall);
    ScoringBureauCall toEntity(ScoringBureauCallDTO scoringBureauCallDTO);
}
