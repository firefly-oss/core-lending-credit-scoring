package com.firefly.core.lending.scoring.interfaces.dtos.request.v1;

import com.firefly.core.lending.scoring.interfaces.enums.request.v1.RequestStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoringRequestDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID scoringRequestId;

    @FilterableId
    @NotNull(message = "Scoring case ID is required")
    private UUID scoringCaseId;    // Ties to ScoringCase

    @FilterableId
    @NotNull(message = "Scoring model ID is required")
    private UUID scoringModelId;   // Ties to ScoringModel

    @NotNull(message = "Request timestamp is required")
    private LocalDateTime requestTimestamp;

    @NotNull(message = "Request status is required")
    private RequestStatusEnum requestStatus;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}