package com.catalis.core.lending.scoring.web.controllers.model.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.scoring.core.services.model.v1.ScoringModelService;
import com.catalis.core.lending.scoring.interfaces.dtos.model.v1.ScoringModelDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/scoring-models")
@Tag(name = "ScoringModel", description = "Operations for managing scoring models")
@RequiredArgsConstructor
public class ScoringModelController {

    private final ScoringModelService service;

    @GetMapping
    @Operation(summary = "List or search scoring models")
    public Mono<ResponseEntity<PaginationResponse<ScoringModelDTO>>> findAll(
            @ModelAttribute FilterRequest<ScoringModelDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new scoring model")
    public Mono<ResponseEntity<ScoringModelDTO>> create(@RequestBody ScoringModelDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{scoringModelId}")
    @Operation(summary = "Get a scoring model by ID")
    public Mono<ResponseEntity<ScoringModelDTO>> getById(@PathVariable Long scoringModelId) {
        return service.getById(scoringModelId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{scoringModelId}")
    @Operation(summary = "Update a scoring model")
    public Mono<ResponseEntity<ScoringModelDTO>> update(
            @PathVariable Long scoringModelId,
            @RequestBody ScoringModelDTO dto) {
        return service.update(scoringModelId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scoringModelId}")
    @Operation(summary = "Delete a scoring model")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long scoringModelId) {
        return service.delete(scoringModelId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}