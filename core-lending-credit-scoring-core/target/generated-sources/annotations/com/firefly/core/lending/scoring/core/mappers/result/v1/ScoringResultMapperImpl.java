package com.firefly.core.lending.scoring.core.mappers.result.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.result.v1.ScoringResultDTO;
import com.firefly.core.lending.scoring.models.entities.result.v1.ScoringResult;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:10+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ScoringResultMapperImpl implements ScoringResultMapper {

    @Override
    public ScoringResultDTO toDTO(ScoringResult scoringResult) {
        if ( scoringResult == null ) {
            return null;
        }

        ScoringResultDTO.ScoringResultDTOBuilder scoringResultDTO = ScoringResultDTO.builder();

        scoringResultDTO.scoringResultId( scoringResult.getScoringResultId() );
        scoringResultDTO.scoringRequestId( scoringResult.getScoringRequestId() );
        scoringResultDTO.scoreValue( scoringResult.getScoreValue() );
        scoringResultDTO.scoreOutcome( scoringResult.getScoreOutcome() );
        scoringResultDTO.reasonCodes( scoringResult.getReasonCodes() );
        scoringResultDTO.extraDetails( scoringResult.getExtraDetails() );
        scoringResultDTO.createdAt( scoringResult.getCreatedAt() );
        scoringResultDTO.updatedAt( scoringResult.getUpdatedAt() );

        return scoringResultDTO.build();
    }

    @Override
    public ScoringResult toEntity(ScoringResultDTO scoringResultDTO) {
        if ( scoringResultDTO == null ) {
            return null;
        }

        ScoringResult.ScoringResultBuilder scoringResult = ScoringResult.builder();

        scoringResult.scoringResultId( scoringResultDTO.getScoringResultId() );
        scoringResult.scoringRequestId( scoringResultDTO.getScoringRequestId() );
        scoringResult.scoreValue( scoringResultDTO.getScoreValue() );
        scoringResult.scoreOutcome( scoringResultDTO.getScoreOutcome() );
        scoringResult.reasonCodes( scoringResultDTO.getReasonCodes() );
        scoringResult.extraDetails( scoringResultDTO.getExtraDetails() );
        scoringResult.createdAt( scoringResultDTO.getCreatedAt() );
        scoringResult.updatedAt( scoringResultDTO.getUpdatedAt() );

        return scoringResult.build();
    }
}
