package com.catalis.core.lending.scoring.interfaces.dtos.bureau.v1;

import com.catalis.core.lending.scoring.interfaces.enums.bureau.v1.BureauNameEnum;
import com.catalis.core.utils.annotations.FilterableId;
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
public class ScoringBureauCallDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long scoringBureauCallId;

    @FilterableId
    private Long scoringCaseId;      // Ties to ScoringCase

    private BureauNameEnum bureauName;
    private LocalDateTime callDate;
    private String referenceCode;
    private Boolean isSuccess;
    private String rawResponse;      // Possibly JSON
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
