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


package com.firefly.core.lending.scoring.core.services.result.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.interfaces.dtos.result.v1.ScoringResultDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ScoringResultService {

    /**
     * Retrieves a paginated list of ScoringResultDTO objects associated with the specified scoring case
     * and scoring request based on the provided filter criteria.
     *
     * @param scoringCaseId the unique identifier of the scoring case for which the scoring results are to be retrieved
     * @param scoringRequestId the unique identifier of the scoring request for which the scoring results are to be retrieved
     * @param filterRequest the filtering criteria used for retrieving the scoring results, encapsulated within a FilterRequest object
     * @return a Mono emitting a PaginationResponse containing the filtered list of ScoringResultDTO objects
     */
    Mono<PaginationResponse<ScoringResultDTO>> findAll(UUID scoringCaseId, UUID scoringRequestId,
                                                       FilterRequest<ScoringResultDTO> filterRequest);

    /**
     * Creates a new scoring result associated with the provided scoring case ID and scoring request ID.
     *
     * @param scoringCaseId the unique identifier of the scoring case to which the scoring result belongs
     * @param scoringRequestId the unique identifier of the scoring request to which the scoring result is tied
     * @param dto the ScoringResultDTO object containing the details of the scoring result to be created
     * @return a Mono emitting the created ScoringResultDTO object
     */
    Mono<ScoringResultDTO> create(UUID scoringCaseId, UUID scoringRequestId, ScoringResultDTO dto);

    /**
     * Retrieves a specific ScoringResultDTO based on the provided scoring case ID, scoring request ID,
     * and scoring result ID.
     *
     * @param scoringCaseId the unique identifier of the scoring case to which the scoring result belongs
     * @param scoringRequestId the unique identifier of the scoring request associated with the scoring result
     * @param scoringResultId the unique identifier of the scoring result to be retrieved
     * @return a Mono emitting the ScoringResultDTO corresponding to the provided identifiers, or an empty Mono if not found
     */
    Mono<ScoringResultDTO> getById(UUID scoringCaseId, UUID scoringRequestId, UUID scoringResultId);

    /**
     * Updates an existing scoring result identified by the given scoring case ID, scoring request ID,
     * and scoring result ID with the provided details.
     *
     * @param scoringCaseId the unique identifier of the scoring case associated with the scoring result
     * @param scoringRequestId the unique identifier of the scoring request associated with the scoring result
     * @param scoringResultId the unique identifier of the scoring result to be updated
     * @param dto the updated details of the scoring result, encapsulated in a ScoringResultDTO object
     * @return a Mono emitting the updated ScoringResultDTO if the update operation succeeds
     */
    Mono<ScoringResultDTO> update(UUID scoringCaseId, UUID scoringRequestId, UUID scoringResultId,
                                  ScoringResultDTO dto);

    /**
     * Deletes a specific scoring result identified by its unique identifiers for
     * the scoring case, scoring request, and scoring result.
     *
     * @param scoringCaseId the unique identifier of the scoring case associated with the scoring result
     * @param scoringRequestId the unique identifier of the scoring request associated with the scoring result
     * @param scoringResultId the unique identifier of the scoring result to be deleted
     * @return a Mono that completes with no value upon successful deletion
     */
    Mono<Void> delete(UUID scoringCaseId, UUID scoringRequestId, UUID scoringResultId);
}