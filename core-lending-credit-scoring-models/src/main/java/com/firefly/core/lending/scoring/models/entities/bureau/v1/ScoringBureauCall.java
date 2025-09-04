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


package com.firefly.core.lending.scoring.models.entities.bureau.v1;

import com.firefly.core.lending.scoring.interfaces.enums.bureau.v1.BureauNameEnum;
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
@Table("scoring_bureau_call")
public class ScoringBureauCall {

    @Id
    @Column("scoring_bureau_call_id")
    private UUID scoringBureauCallId;

    @Column("scoring_case_id")
    private UUID scoringCaseId;     // References ScoringCase

    @Column("bureau_name")
    private BureauNameEnum bureauName;

    @Column("call_date")
    private LocalDateTime callDate;

    @Column("reference_code")
    private String referenceCode;

    @Column("is_success")
    private Boolean isSuccess;

    @Column("raw_response")
    private String rawResponse;

    @Column("note")
    private String note;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}