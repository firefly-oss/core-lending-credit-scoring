package com.catalis.core.lending.scoring.models.entities.model.v1;

import com.catalis.core.lending.scoring.interfaces.enums.model.v1.ModelApproachEnum;
import com.catalis.core.lending.scoring.interfaces.enums.model.v1.ModelStatusEnum;
import com.catalis.core.lending.scoring.interfaces.enums.model.v1.ScoreDimensionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("scoring_model")
public class ScoringModel {

    @Id
    @Column("scoring_model_id")
    private Long scoringModelId;

    @Column("model_name")
    private String modelName;

    @Column("model_version")
    private String modelVersion;

    @Column("score_dimension")
    private ScoreDimensionEnum scoreDimension;

    @Column("model_approach")
    private ModelApproachEnum modelApproach;

    @Column("model_status")
    private ModelStatusEnum modelStatus;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
