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


package com.firefly.core.lending.scoring.core.services.model.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.interfaces.dtos.model.v1.ScoringModelDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ScoringModelService {

    /**
     * Retrieves a paginated list of ScoringModelDTO objects based on the provided filter criteria.
     *
     * @param filterRequest the filtering criteria used for retrieving the scoring models,
     *                      encapsulated within a FilterRequest object.
     * @return a Mono that emits a PaginationResponse containing the filtered list of ScoringModelDTO objects.
     */
    Mono<PaginationResponse<ScoringModelDTO>> findAll(FilterRequest<ScoringModelDTO> filterRequest);

    /**
     * Creates a new scoring model based on the provided information.
     *
     * @param dto the ScoringModelDTO object containing the details of the scoring model to be created
     * @return a Mono emitting the created ScoringModelDTO object
     */
    Mono<ScoringModelDTO> create(ScoringModelDTO dto);

    /**
     * Retrieves a scoring model by its unique identifier.
     *
     * @param scoringModelId the unique identifier of the scoring model to be retrieved
     * @return a Mono emitting the ScoringModelDTO corresponding to the provided identifier, or an empty Mono if not found
     */
    Mono<ScoringModelDTO> getById(UUID scoringModelId);

    /**
     * Updates an existing scoring model based on the provided ID and details.
     *
     * @param scoringModelId the unique identifier of the scoring model to be updated
     * @param dto the details of the scoring model to update, encapsulated in a ScoringModelDTO object
     * @return a Mono emitting the updated ScoringModelDTO if the operation is successful
     */
    Mono<ScoringModelDTO> update(UUID scoringModelId, ScoringModelDTO dto);

    /**
     * Deletes a scoring model by its ID.
     *
     * @param scoringModelId the ID of the scoring model to be deleted
     * @return a Mono that completes with no value if the operation is successful
     */
    Mono<Void> delete(UUID scoringModelId);
}
