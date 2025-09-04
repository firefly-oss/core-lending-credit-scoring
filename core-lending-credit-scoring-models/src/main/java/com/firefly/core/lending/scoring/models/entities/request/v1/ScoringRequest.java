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


package com.firefly.core.lending.scoring.models.entities.request.v1;

import com.firefly.core.lending.scoring.interfaces.enums.request.v1.RequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("scoring_request")
public class ScoringRequest {

    @Id
    @Column("scoring_request_id")
    private UUID scoringRequestId;

    @Column("scoring_case_id")
    private UUID scoringCaseId;     // FK to ScoringCase

    @Column("scoring_model_id")
    private UUID scoringModelId;    // FK to ScoringModel

    @Column("request_timestamp")
    private LocalDateTime requestTimestamp;

    @Column("request_status")
    private RequestStatusEnum requestStatus;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}