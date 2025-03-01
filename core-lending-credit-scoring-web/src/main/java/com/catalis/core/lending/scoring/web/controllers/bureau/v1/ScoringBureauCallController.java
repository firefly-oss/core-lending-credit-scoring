package com.catalis.core.lending.scoring.web.controllers.bureau.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.scoring.core.services.bureau.v1.ScoringBureauCallService;
import com.catalis.core.lending.scoring.interfaces.dtos.bureau.v1.ScoringBureauCallDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/scoring-cases/{scoringCaseId}/bureau-calls")
@Tag(name = "ScoringBureauCall", description = "Operations for bureau calls under a scoring case")
@RequiredArgsConstructor
public class ScoringBureauCallController {

    private final ScoringBureauCallService service;

    @GetMapping
    @Operation(summary = "List or search bureau calls for a scoring case")
    public Mono<ResponseEntity<PaginationResponse<ScoringBureauCallDTO>>> findAll(
            @PathVariable Long scoringCaseId,
            @ModelAttribute FilterRequest<ScoringBureauCallDTO> filterRequest) {

        return service.findAll(scoringCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new bureau call record")
    public Mono<ResponseEntity<ScoringBureauCallDTO>> create(
            @PathVariable Long scoringCaseId,
            @RequestBody ScoringBureauCallDTO dto) {

        return service.create(scoringCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{scoringBureauCallId}")
    @Operation(summary = "Get a bureau call record by ID")
    public Mono<ResponseEntity<ScoringBureauCallDTO>> getById(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringBureauCallId) {

        return service.getById(scoringCaseId, scoringBureauCallId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{scoringBureauCallId}")
    @Operation(summary = "Update a bureau call record")
    public Mono<ResponseEntity<ScoringBureauCallDTO>> update(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringBureauCallId,
            @RequestBody ScoringBureauCallDTO dto) {

        return service.update(scoringCaseId, scoringBureauCallId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scoringBureauCallId}")
    @Operation(summary = "Delete a bureau call record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringBureauCallId) {

        return service.delete(scoringCaseId, scoringBureauCallId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}