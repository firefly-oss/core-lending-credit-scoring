package com.catalis.core.lending.scoring.models.entities.cases.v1;

import com.catalis.core.lending.scoring.interfaces.enums.cases.v1.CaseStatusEnum;
import com.catalis.core.lending.scoring.interfaces.enums.cases.v1.CaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("scoring_case")
public class ScoringCase {

    @Id
    @Column("scoring_case_id")
    private Long scoringCaseId;

    @Column("loan_application_id")
    private Long loanApplicationId; // External reference (no direct FK)

    @Column("customer_id")
    private Long customerId;        // External reference (no direct FK)

    @Column("case_status")
    private CaseStatusEnum caseStatus;

    @Column("case_type")
    private CaseTypeEnum caseType;

    @Column("remarks")
    private String remarks;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
