package com.firefly.core.lending.scoring.core.mappers.request.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.request.v1.ScoringRequestDTO;
import com.firefly.core.lending.scoring.models.entities.request.v1.ScoringRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T20:21:10+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ScoringRequestMapperImpl implements ScoringRequestMapper {

    @Override
    public ScoringRequestDTO toDTO(ScoringRequest scoringRequest) {
        if ( scoringRequest == null ) {
            return null;
        }

        ScoringRequestDTO.ScoringRequestDTOBuilder scoringRequestDTO = ScoringRequestDTO.builder();

        scoringRequestDTO.scoringRequestId( scoringRequest.getScoringRequestId() );
        scoringRequestDTO.scoringCaseId( scoringRequest.getScoringCaseId() );
        scoringRequestDTO.scoringModelId( scoringRequest.getScoringModelId() );
        scoringRequestDTO.requestTimestamp( scoringRequest.getRequestTimestamp() );
        scoringRequestDTO.requestStatus( scoringRequest.getRequestStatus() );
        scoringRequestDTO.note( scoringRequest.getNote() );
        scoringRequestDTO.createdAt( scoringRequest.getCreatedAt() );
        scoringRequestDTO.updatedAt( scoringRequest.getUpdatedAt() );

        return scoringRequestDTO.build();
    }

    @Override
    public ScoringRequest toEntity(ScoringRequestDTO scoringRequestDTO) {
        if ( scoringRequestDTO == null ) {
            return null;
        }

        ScoringRequest.ScoringRequestBuilder scoringRequest = ScoringRequest.builder();

        scoringRequest.scoringRequestId( scoringRequestDTO.getScoringRequestId() );
        scoringRequest.scoringCaseId( scoringRequestDTO.getScoringCaseId() );
        scoringRequest.scoringModelId( scoringRequestDTO.getScoringModelId() );
        scoringRequest.requestTimestamp( scoringRequestDTO.getRequestTimestamp() );
        scoringRequest.requestStatus( scoringRequestDTO.getRequestStatus() );
        scoringRequest.note( scoringRequestDTO.getNote() );
        scoringRequest.createdAt( scoringRequestDTO.getCreatedAt() );
        scoringRequest.updatedAt( scoringRequestDTO.getUpdatedAt() );

        return scoringRequest.build();
    }
}
