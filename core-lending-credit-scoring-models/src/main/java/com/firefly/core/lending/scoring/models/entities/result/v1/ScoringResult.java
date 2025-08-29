package com.firefly.core.lending.scoring.models.entities.result.v1;

import com.firefly.core.lending.scoring.interfaces.enums.model.v1.ScoreOutcomeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("scoring_result")
public class ScoringResult {

    @Id
    @Column("scoring_result_id")
    private Long scoringResultId;

    @Column("scoring_request_id")
    private Long scoringRequestId;   // References ScoringRequest

    @Column("score_value")
    private BigDecimal scoreValue;   // e.g. Probability of Default or raw score

    @Column("score_outcome")
    private ScoreOutcomeEnum scoreOutcome;

    @Column("reason_codes")
    private String reasonCodes;      // JSON or text with partial explanations

    @Column("extra_details")
    private String extraDetails;     // Extended info e.g. partial scoring breakdown

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}