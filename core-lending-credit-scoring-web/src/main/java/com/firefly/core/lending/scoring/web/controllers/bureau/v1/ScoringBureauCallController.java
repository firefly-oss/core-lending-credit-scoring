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


package com.firefly.core.lending.scoring.web.controllers.bureau.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.services.bureau.v1.ScoringBureauCallService;
import com.firefly.core.lending.scoring.interfaces.dtos.bureau.v1.ScoringBureauCallDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/scoring-cases/{scoringCaseId}/bureau-calls")
@Tag(name = "ScoringBureauCall", description = "Operations for bureau calls under a scoring case")
@RequiredArgsConstructor
public class ScoringBureauCallController {

    private final ScoringBureauCallService service;

    @GetMapping
    @Operation(summary = "List or search bureau calls for a scoring case")
    public Mono<ResponseEntity<PaginationResponse<ScoringBureauCallDTO>>> findAll(
            @PathVariable UUID scoringCaseId,
            @ModelAttribute FilterRequest<ScoringBureauCallDTO> filterRequest) {

        return service.findAll(scoringCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new bureau call record")
    public Mono<ResponseEntity<ScoringBureauCallDTO>> create(
            @PathVariable UUID scoringCaseId,
            @Valid @RequestBody ScoringBureauCallDTO dto) {

        return service.create(scoringCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{scoringBureauCallId}")
    @Operation(summary = "Get a bureau call record by ID")
    public Mono<ResponseEntity<ScoringBureauCallDTO>> getById(
            @PathVariable UUID scoringCaseId,
            @PathVariable UUID scoringBureauCallId) {

        return service.getById(scoringCaseId, scoringBureauCallId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{scoringBureauCallId}")
    @Operation(summary = "Update a bureau call record")
    public Mono<ResponseEntity<ScoringBureauCallDTO>> update(
            @PathVariable UUID scoringCaseId,
            @PathVariable UUID scoringBureauCallId,
            @Valid @RequestBody ScoringBureauCallDTO dto) {

        return service.update(scoringCaseId, scoringBureauCallId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scoringBureauCallId}")
    @Operation(summary = "Delete a bureau call record")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID scoringCaseId,
            @PathVariable UUID scoringBureauCallId) {

        return service.delete(scoringCaseId, scoringBureauCallId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}