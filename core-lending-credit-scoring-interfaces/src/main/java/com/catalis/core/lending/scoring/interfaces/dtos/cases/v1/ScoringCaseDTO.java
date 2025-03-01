package com.catalis.core.lending.scoring.interfaces.dtos.cases.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.lending.scoring.interfaces.enums.cases.v1.CaseStatusEnum;
import com.catalis.core.lending.scoring.interfaces.enums.cases.v1.CaseTypeEnum;
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
public class ScoringCaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long scoringCaseId;

    @FilterableId
    private Long loanApplicationId;  // Reference to Loan Origination (optional)

    @FilterableId
    private Long customerId;         // Reference to Customer domain

    private CaseStatusEnum caseStatus;
    private CaseTypeEnum caseType;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}