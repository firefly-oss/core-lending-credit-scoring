package com.firefly.core.lending.scoring.interfaces.dtos.model.v1;

import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ModelApproachEnum;
import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ModelStatusEnum;
import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ScoreDimensionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoringModelDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long scoringModelId;

    private String modelName;
    private String modelVersion;
    private ScoreDimensionEnum scoreDimension;
    private ModelApproachEnum modelApproach;
    private ModelStatusEnum modelStatus;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
