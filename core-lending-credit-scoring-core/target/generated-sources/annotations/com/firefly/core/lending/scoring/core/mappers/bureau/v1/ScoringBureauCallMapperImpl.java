package com.firefly.core.lending.scoring.core.mappers.bureau.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.bureau.v1.ScoringBureauCallDTO;
import com.firefly.core.lending.scoring.models.entities.bureau.v1.ScoringBureauCall;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T16:44:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ScoringBureauCallMapperImpl implements ScoringBureauCallMapper {

    @Override
    public ScoringBureauCallDTO toDTO(ScoringBureauCall scoringBureauCall) {
        if ( scoringBureauCall == null ) {
            return null;
        }

        ScoringBureauCallDTO.ScoringBureauCallDTOBuilder scoringBureauCallDTO = ScoringBureauCallDTO.builder();

        scoringBureauCallDTO.scoringBureauCallId( scoringBureauCall.getScoringBureauCallId() );
        scoringBureauCallDTO.scoringCaseId( scoringBureauCall.getScoringCaseId() );
        scoringBureauCallDTO.bureauName( scoringBureauCall.getBureauName() );
        scoringBureauCallDTO.callDate( scoringBureauCall.getCallDate() );
        scoringBureauCallDTO.referenceCode( scoringBureauCall.getReferenceCode() );
        scoringBureauCallDTO.isSuccess( scoringBureauCall.getIsSuccess() );
        scoringBureauCallDTO.rawResponse( scoringBureauCall.getRawResponse() );
        scoringBureauCallDTO.note( scoringBureauCall.getNote() );
        scoringBureauCallDTO.createdAt( scoringBureauCall.getCreatedAt() );
        scoringBureauCallDTO.updatedAt( scoringBureauCall.getUpdatedAt() );

        return scoringBureauCallDTO.build();
    }

    @Override
    public ScoringBureauCall toEntity(ScoringBureauCallDTO scoringBureauCallDTO) {
        if ( scoringBureauCallDTO == null ) {
            return null;
        }

        ScoringBureauCall.ScoringBureauCallBuilder scoringBureauCall = ScoringBureauCall.builder();

        scoringBureauCall.scoringBureauCallId( scoringBureauCallDTO.getScoringBureauCallId() );
        scoringBureauCall.scoringCaseId( scoringBureauCallDTO.getScoringCaseId() );
        scoringBureauCall.bureauName( scoringBureauCallDTO.getBureauName() );
        scoringBureauCall.callDate( scoringBureauCallDTO.getCallDate() );
        scoringBureauCall.referenceCode( scoringBureauCallDTO.getReferenceCode() );
        scoringBureauCall.isSuccess( scoringBureauCallDTO.getIsSuccess() );
        scoringBureauCall.rawResponse( scoringBureauCallDTO.getRawResponse() );
        scoringBureauCall.note( scoringBureauCallDTO.getNote() );
        scoringBureauCall.createdAt( scoringBureauCallDTO.getCreatedAt() );
        scoringBureauCall.updatedAt( scoringBureauCallDTO.getUpdatedAt() );

        return scoringBureauCall.build();
    }
}
