package com.catalis.core.lending.scoring.core.mappers.cases.v1;

import com.catalis.core.lending.scoring.interfaces.dtos.cases.v1.ScoringCaseDTO;
import com.catalis.core.lending.scoring.models.entities.cases.v1.ScoringCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoringCaseMapper {
    ScoringCaseDTO toDTO(ScoringCase scoringCase);
    ScoringCase toEntity(ScoringCaseDTO scoringCaseDTO);
}
