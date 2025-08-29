package com.firefly.core.lending.scoring.interfaces.dtos.request.v1;

import com.firefly.core.lending.scoring.interfaces.enums.request.v1.RequestStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
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
public class ScoringRequestDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long scoringRequestId;

    @FilterableId
    private Long scoringCaseId;    // Ties to ScoringCase

    @FilterableId
    private Long scoringModelId;   // Ties to ScoringModel

    private LocalDateTime requestTimestamp;
    private RequestStatusEnum requestStatus;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}