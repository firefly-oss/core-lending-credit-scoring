package com.firefly.core.lending.scoring.core.mappers.model.v1;

import com.firefly.core.lending.scoring.interfaces.dtos.model.v1.ScoringModelDTO;
import com.firefly.core.lending.scoring.models.entities.model.v1.ScoringModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T15:38:50+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class ScoringModelMapperImpl implements ScoringModelMapper {

    @Override
    public ScoringModelDTO toDTO(ScoringModel scoringModel) {
        if ( scoringModel == null ) {
            return null;
        }

        ScoringModelDTO.ScoringModelDTOBuilder scoringModelDTO = ScoringModelDTO.builder();

        scoringModelDTO.scoringModelId( scoringModel.getScoringModelId() );
        scoringModelDTO.modelName( scoringModel.getModelName() );
        scoringModelDTO.modelVersion( scoringModel.getModelVersion() );
        scoringModelDTO.scoreDimension( scoringModel.getScoreDimension() );
        scoringModelDTO.modelApproach( scoringModel.getModelApproach() );
        scoringModelDTO.modelStatus( scoringModel.getModelStatus() );
        scoringModelDTO.note( scoringModel.getNote() );
        scoringModelDTO.createdAt( scoringModel.getCreatedAt() );
        scoringModelDTO.updatedAt( scoringModel.getUpdatedAt() );

        return scoringModelDTO.build();
    }

    @Override
    public ScoringModel toEntity(ScoringModelDTO scoringModelDTO) {
        if ( scoringModelDTO == null ) {
            return null;
        }

        ScoringModel.ScoringModelBuilder scoringModel = ScoringModel.builder();

        scoringModel.scoringModelId( scoringModelDTO.getScoringModelId() );
        scoringModel.modelName( scoringModelDTO.getModelName() );
        scoringModel.modelVersion( scoringModelDTO.getModelVersion() );
        scoringModel.scoreDimension( scoringModelDTO.getScoreDimension() );
        scoringModel.modelApproach( scoringModelDTO.getModelApproach() );
        scoringModel.modelStatus( scoringModelDTO.getModelStatus() );
        scoringModel.note( scoringModelDTO.getNote() );
        scoringModel.createdAt( scoringModelDTO.getCreatedAt() );
        scoringModel.updatedAt( scoringModelDTO.getUpdatedAt() );

        return scoringModel.build();
    }
}
