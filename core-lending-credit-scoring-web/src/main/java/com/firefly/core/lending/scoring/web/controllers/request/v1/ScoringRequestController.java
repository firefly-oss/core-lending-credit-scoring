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


package com.firefly.core.lending.scoring.web.controllers.request.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.services.request.v1.ScoringRequestService;
import com.firefly.core.lending.scoring.interfaces.dtos.request.v1.ScoringRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/scoring-cases/{scoringCaseId}/requests")
@Tag(name = "ScoringRequest", description = "Operations for scoring requests under a scoring case")
@RequiredArgsConstructor
public class ScoringRequestController {

    private final ScoringRequestService service;

    @GetMapping
    @Operation(summary = "List or search scoring requests for a scoring case")
    public Mono<ResponseEntity<PaginationResponse<ScoringRequestDTO>>> findAll(
            @PathVariable UUID scoringCaseId,
            @ModelAttribute FilterRequest<ScoringRequestDTO> filterRequest) {

        return service.findAll(scoringCaseId, filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(summary = "Create a new scoring request")
    public Mono<ResponseEntity<ScoringRequestDTO>> create(
            @PathVariable UUID scoringCaseId,
            @Valid @RequestBody ScoringRequestDTO dto) {

        return service.create(scoringCaseId, dto)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{scoringRequestId}")
    @Operation(summary = "Get a scoring request by ID")
    public Mono<ResponseEntity<ScoringRequestDTO>> getById(
            @PathVariable UUID scoringCaseId,
            @PathVariable UUID scoringRequestId) {

        return service.getById(scoringCaseId, scoringRequestId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{scoringRequestId}")
    @Operation(summary = "Update a scoring request")
    public Mono<ResponseEntity<ScoringRequestDTO>> update(
            @PathVariable UUID scoringCaseId,
            @PathVariable UUID scoringRequestId,
            @Valid @RequestBody ScoringRequestDTO dto) {

        return service.update(scoringCaseId, scoringRequestId, dto)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{scoringRequestId}")
    @Operation(summary = "Delete a scoring request")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable UUID scoringCaseId,
            @PathVariable UUID scoringRequestId) {

        return service.delete(scoringCaseId, scoringRequestId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
