package com.firefly.core.lending.scoring.interfaces.dtos.cases.v1;

import com.firefly.core.lending.scoring.interfaces.enums.cases.v1.CaseStatusEnum;
import com.firefly.core.lending.scoring.interfaces.enums.cases.v1.CaseTypeEnum;
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
public class ScoringCaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID scoringCaseId;

    @FilterableId
    // loanApplicationId is optional - no validation required
    private UUID loanApplicationId;  // Reference to Loan Origination (optional)

    @FilterableId
    @NotNull(message = "Customer ID is required")
    private UUID customerId;         // Reference to Customer domain

    @NotNull(message = "Case status is required")
    private CaseStatusEnum caseStatus;

    @NotNull(message = "Case type is required")
    private CaseTypeEnum caseType;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}