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


package com.firefly.core.lending.scoring.interfaces.dtos.bureau.v1;

import com.firefly.core.lending.scoring.interfaces.enums.bureau.v1.BureauNameEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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
public class ScoringBureauCallDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID scoringBureauCallId;

    @FilterableId
    @NotNull(message = "Scoring case ID is required")
    private UUID scoringCaseId;      // Ties to ScoringCase

    @NotNull(message = "Bureau name is required")
    private BureauNameEnum bureauName;

    @NotNull(message = "Call date is required")
    private LocalDateTime callDate;

    @NotBlank(message = "Reference code is required")
    @Size(min = 1, max = 100, message = "Reference code must be between 1 and 100 characters")
    private String referenceCode;

    @NotNull(message = "Success status is required")
    private Boolean isSuccess;

    @Size(max = 10000, message = "Raw response cannot exceed 10000 characters")
    private String rawResponse;      // Possibly JSON

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
