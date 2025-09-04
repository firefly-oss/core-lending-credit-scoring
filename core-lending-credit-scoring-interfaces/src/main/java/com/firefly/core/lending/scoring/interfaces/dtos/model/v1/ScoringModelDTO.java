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


package com.firefly.core.lending.scoring.interfaces.dtos.model.v1;

import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ModelApproachEnum;
import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ModelStatusEnum;
import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ScoreDimensionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class ScoringModelDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID scoringModelId;

    @NotBlank(message = "Model name is required")
    @Size(min = 2, max = 100, message = "Model name must be between 2 and 100 characters")
    private String modelName;

    @NotBlank(message = "Model version is required")
    @Pattern(regexp = "^\\d+\\.\\d+(\\.\\d+)?$", message = "Model version must follow semantic versioning (e.g., 1.0, 1.0.1)")
    private String modelVersion;

    @NotNull(message = "Score dimension is required")
    private ScoreDimensionEnum scoreDimension;

    @NotNull(message = "Model approach is required")
    private ModelApproachEnum modelApproach;

    @NotNull(message = "Model status is required")
    private ModelStatusEnum modelStatus;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
