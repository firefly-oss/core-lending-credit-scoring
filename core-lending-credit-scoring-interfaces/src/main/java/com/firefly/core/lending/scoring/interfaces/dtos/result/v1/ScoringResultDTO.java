/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.scoring.interfaces.dtos.result.v1;

import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ScoreOutcomeEnum;
import org.fireflyframework.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoringResultDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID scoringResultId;

    @FilterableId
    @NotNull(message = "Scoring request ID is required")
    private UUID scoringRequestId;   // Ties to ScoringRequest

    @NotNull(message = "Score value is required")
    @DecimalMin(value = "0.0", message = "Score value must be greater than or equal to 0")
    @DecimalMax(value = "1.0", message = "Score value must be less than or equal to 1")
    private BigDecimal scoreValue;   // Numeric score, e.g. Probability of Default

    @NotNull(message = "Score outcome is required")
    private ScoreOutcomeEnum scoreOutcome;

    @Size(max = 2000, message = "Reason codes cannot exceed 2000 characters")
    private String reasonCodes;      // Possibly JSON or comma-separated

    @Size(max = 5000, message = "Extra details cannot exceed 5000 characters")
    private String extraDetails;     // Extended fields, partial data

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}