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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("scoring_bureau_call")
public class ScoringBureauCall {

    @Id
    @Column("scoring_bureau_call_id")
    private Long scoringBureauCallId;

    @Column("scoring_case_id")
    private Long scoringCaseId;     // References ScoringCase

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