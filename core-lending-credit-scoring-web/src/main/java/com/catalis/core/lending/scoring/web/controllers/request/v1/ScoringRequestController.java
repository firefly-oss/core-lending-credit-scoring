package com.catalis.core.lending.scoring.web.controllers.request.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.scoring.core.services.request.v1.ScoringRequestService;
import com.catalis.core.lending.scoring.interfaces.dtos.request.v1.ScoringRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/scoring-cases/{scoringCaseId}/requests")
@Tag(name = "ScoringRequest", description = "Operations for scoring requests under a scoring case")
@RequiredArgsConstructor
public class ScoringRequestController {

    private final ScoringRequestService service;

    @GetMapping
    @Operation(summary = "List or search scoring requests for a scoring case")
    public Mono<ResponseEntity<PaginationResponse<ScoringRequestDTO>>> findAll(
            @PathVariable Long scoringCaseId,
            @ModelAttribute FilterRequest<ScoringRequestDTO> filterRequest) {

        return service.findAll(scoringCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new scoring request")
    public Mono<ResponseEntity<ScoringRequestDTO>> create(
            @PathVariable Long scoringCaseId,
            @RequestBody ScoringRequestDTO dto) {

        return service.create(scoringCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{scoringRequestId}")
    @Operation(summary = "Get a scoring request by ID")
    public Mono<ResponseEntity<ScoringRequestDTO>> getById(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId) {

        return service.getById(scoringCaseId, scoringRequestId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{scoringRequestId}")
    @Operation(summary = "Update a scoring request")
    public Mono<ResponseEntity<ScoringRequestDTO>> update(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId,
            @RequestBody ScoringRequestDTO dto) {

        return service.update(scoringCaseId, scoringRequestId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scoringRequestId}")
    @Operation(summary = "Delete a scoring request")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable Long scoringCaseId,
            @PathVariable Long scoringRequestId) {

        return service.delete(scoringCaseId, scoringRequestId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
