package com.catalis.core.lending.scoring.models.entities.request.v1;

import com.catalis.core.lending.scoring.interfaces.enums.request.v1.RequestStatusEnum;
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
@Table("scoring_request")
public class ScoringRequest {

    @Id
    @Column("scoring_request_id")
    private Long scoringRequestId;

    @Column("scoring_case_id")
    private Long scoringCaseId;     // FK to ScoringCase

    @Column("scoring_model_id")
    private Long scoringModelId;    // FK to ScoringModel

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