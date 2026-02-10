/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.scoring.web.controllers.cases.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.services.cases.v1.ScoringCaseService;
import com.firefly.core.lending.scoring.interfaces.dtos.cases.v1.ScoringCaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/scoring-cases")
@Tag(name = "ScoringCase", description = "Operations for managing scoring cases")
@RequiredArgsConstructor
public class ScoringCaseController {

    private final ScoringCaseService service;

    @GetMapping
    @Operation(summary = "List or search scoring cases")
    public Mono<ResponseEntity<PaginationResponse<ScoringCaseDTO>>> findAll(
            @ModelAttribute FilterRequest<ScoringCaseDTO> filterRequest) {

        return service.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new scoring case")
    public Mono<ResponseEntity<ScoringCaseDTO>> create(@Valid @RequestBody ScoringCaseDTO dto) {
        return service.create(dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{scoringCaseId}")
    @Operation(summary = "Get a scoring case by ID")
    public Mono<ResponseEntity<ScoringCaseDTO>> getById(@PathVariable UUID scoringCaseId) {
        return service.getById(scoringCaseId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{scoringCaseId}")
    @Operation(summary = "Update a scoring case")
    public Mono<ResponseEntity<ScoringCaseDTO>> update(
            @PathVariable UUID scoringCaseId,
            @Valid @RequestBody ScoringCaseDTO dto) {
        return service.update(scoringCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scoringCaseId}")
    @Operation(summary = "Delete a scoring case")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID scoringCaseId) {
        return service.delete(scoringCaseId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
