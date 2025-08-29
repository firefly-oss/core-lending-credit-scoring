package com.firefly.core.lending.scoring.core.mappers.cases.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.cases.v1.ScoringCaseDTO;
import com.firefly.core.lending.scoring.models.entities.cases.v1.ScoringCase;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ScoringCaseMapperImpl implements ScoringCaseMapper {

    @Override
    public ScoringCaseDTO toDTO(ScoringCase scoringCase) {
        if ( scoringCase == null ) {
            return null;
        }

        ScoringCaseDTO.ScoringCaseDTOBuilder scoringCaseDTO = ScoringCaseDTO.builder();

        scoringCaseDTO.scoringCaseId( scoringCase.getScoringCaseId() );
        scoringCaseDTO.loanApplicationId( scoringCase.getLoanApplicationId() );
        scoringCaseDTO.customerId( scoringCase.getCustomerId() );
        scoringCaseDTO.caseStatus( scoringCase.getCaseStatus() );
        scoringCaseDTO.caseType( scoringCase.getCaseType() );
        scoringCaseDTO.remarks( scoringCase.getRemarks() );
        scoringCaseDTO.createdAt( scoringCase.getCreatedAt() );
        scoringCaseDTO.updatedAt( scoringCase.getUpdatedAt() );

        return scoringCaseDTO.build();
    }

    @Override
    public ScoringCase toEntity(ScoringCaseDTO scoringCaseDTO) {
        if ( scoringCaseDTO == null ) {
            return null;
        }

        ScoringCase.ScoringCaseBuilder scoringCase = ScoringCase.builder();

        scoringCase.scoringCaseId( scoringCaseDTO.getScoringCaseId() );
        scoringCase.loanApplicationId( scoringCaseDTO.getLoanApplicationId() );
        scoringCase.customerId( scoringCaseDTO.getCustomerId() );
        scoringCase.caseStatus( scoringCaseDTO.getCaseStatus() );
        scoringCase.caseType( scoringCaseDTO.getCaseType() );
        scoringCase.remarks( scoringCaseDTO.getRemarks() );
        scoringCase.createdAt( scoringCaseDTO.getCreatedAt() );
        scoringCase.updatedAt( scoringCaseDTO.getUpdatedAt() );

        return scoringCase.build();
    }
}
