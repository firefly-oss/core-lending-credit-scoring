package com.catalis.core.lending.scoring.web.controllers.result.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.scoring.core.services.result.v1.ScoringResultService;
import com.catalis.core.lending.scoring.interfaces.dtos.result.v1.ScoringResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/scoring-cases/{scoringCaseId}/requests/{scoringRequestId}/results")
@Tag(name = "ScoringResult", description = "Operations for scoring results within a scoring request")
@RequiredArgsConstructor
public class ScoringResultController {

    private final ScoringResultService service;

    @GetMapping
    @Operation(summary = "List or search scoring results for a request")
    public Mono<ResponseEntity<PaginationResponse<ScoringResultDTO>>> findAll(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId,
            @ModelAttribute FilterRequest<ScoringResultDTO> filterRequest) {

        return service.findAll(scoringCaseId, scoringRequestId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new scoring result")
    public Mono<ResponseEntity<ScoringResultDTO>> create(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId,
            @RequestBody ScoringResultDTO dto) {

        return service.create(scoringCaseId, scoringRequestId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{scoringResultId}")
    @Operation(summary = "Get a scoring result by ID")
    public Mono<ResponseEntity<ScoringResultDTO>> getById(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId,
            @PathVariable Long scoringResultId) {

        return service.getById(scoringCaseId, scoringRequestId, scoringResultId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{scoringResultId}")
    @Operation(summary = "Update a scoring result")
    public Mono<ResponseEntity<ScoringResultDTO>> update(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId,
            @PathVariable Long scoringResultId,
            @RequestBody ScoringResultDTO dto) {

        return service.update(scoringCaseId, scoringRequestId, scoringResultId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scoringResultId}")
    @Operation(summary = "Delete a scoring result")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId,
            @PathVariable Long scoringResultId) {

        return service.delete(scoringCaseId, scoringRequestId, scoringResultId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
