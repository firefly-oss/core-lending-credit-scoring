package com.firefly.core.lending.scoring.core.services.cases.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.interfaces.dtos.cases.v1.ScoringCaseDTO;
import reactor.core.publisher.Mono;

public interface ScoringCaseService {

    /**
     * Retrieves a paginated list of ScoringCaseDTO objects based on the provided filter criteria.
     *
     * @param filterRequest the filtering criteria used for retrieving the scoring cases,
     *                      encapsulated within a FilterRequest object.
     * @return a Mono emitting a PaginationResponse containing the filtered list of ScoringCaseDTO objects.
     */
    Mono<PaginationResponse<ScoringCaseDTO>> findAll(FilterRequest<ScoringCaseDTO> filterRequest);

    /**
     * Creates a new scoring case with the provided details.
     *
     * @param dto the ScoringCaseDTO object containing the information of the scoring case to be created
     * @return a Mono emitting the created ScoringCaseDTO object
     */
    Mono<ScoringCaseDTO> create(ScoringCaseDTO dto);

    /**
     * Retrieves a scoring case by its unique identifier.
     *
     * @param scoringCaseId the unique identifier of the scoring case to be retrieved
     * @return a Mono emitting the ScoringCaseDTO corresponding to the provided identifier, or an empty Mono if not found
     */
    Mono<ScoringCaseDTO> getById(Long scoringCaseId);

    /**
     * Updates an existing scoring case with the provided information.
     *
     * @param scoringCaseId the unique identifier of the scoring case to update
     * @param dto the details of the scoring case to be updated, encapsulated in a ScoringCaseDTO object
     * @return a Mono emitting the updated ScoringCaseDTO if the operation is successful
     */
    Mono<ScoringCaseDTO> update(Long scoringCaseId, ScoringCaseDTO dto);

    /**
     * Deletes a scoring case by its unique identifier.
     *
     * @param scoringCaseId the unique identifier of the scoring case to be deleted
     * @return a Mono that completes with no value if the operation is successful
     */
    Mono<Void> delete(Long scoringCaseId);
}