package com.catalis.core.lending.scoring.interfaces.dtos.result.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.scoring.interfaces.enums.model.v1.ScoreOutcomeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoringResultDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long scoringResultId;

    @FilterableId
    private Long scoringRequestId;   // Ties to ScoringRequest

    private BigDecimal scoreValue;   // Numeric score, e.g. Probability of Default
    private ScoreOutcomeEnum scoreOutcome;
    private String reasonCodes;      // Possibly JSON or comma-separated
    private String extraDetails;     // Extended fields, partial data
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}