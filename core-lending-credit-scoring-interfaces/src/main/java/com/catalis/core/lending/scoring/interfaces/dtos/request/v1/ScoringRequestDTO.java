package com.catalis.core.lending.scoring.interfaces.dtos.request.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.scoring.interfaces.enums.request.v1.RequestStatusEnum;
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