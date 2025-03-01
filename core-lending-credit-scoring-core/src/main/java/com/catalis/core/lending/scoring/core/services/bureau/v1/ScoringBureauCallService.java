package com.catalis.core.lending.scoring.core.services.bureau.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.scoring.interfaces.dtos.bureau.v1.ScoringBureauCallDTO;
import reactor.core.publisher.Mono;

public interface ScoringBureauCallService {

    /**
     * Retrieves a paginated list of ScoringBureauCallDTO objects associated with the specified scoring case ID
     * based on the provided filter criteria.
     *
     * @param scoringCaseId the unique identifier of the scoring case for which the bureau calls are to be retrieved
     * @param filterRequest the filter criteria used for retrieving the scoring bureau calls, encapsulated within
     *                      a FilterRequest object
     * @return a Mono emitting a PaginationResponse containing the filtered list of ScoringBureauCallDTO objects
     */
    Mono<PaginationResponse<ScoringBureauCallDTO>> findAll(Long scoringCaseId,
                                                           FilterRequest<ScoringBureauCallDTO> filterRequest);

    /**
     * Creates a new ScoringBureauCall entry associated with the specified scoring case.
     *
     * @param scoringCaseId the unique identifier of the scoring case to which the bureau call is tied
     * @param dto the ScoringBureauCallDTO object containing the details of the bureau call to be created
     * @return a Mono emitting the created ScoringBureauCallDTO object
     */
    Mono<ScoringBureauCallDTO> create(Long scoringCaseId, ScoringBureauCallDTO dto);

    /**
     * Retrieves a ScoringBureauCallDTO by its unique identifiers for the scoring case and the bureau call.
     *
     * @param scoringCaseId the unique identifier of the scoring case associated with the bureau call
     * @param scoringBureauCallId the unique identifier of the specific scoring bureau call to retrieve
     * @return a Mono emitting the ScoringBureauCallDTO corresponding to the provided identifiers, or an empty Mono if not found
     */
    Mono<ScoringBureauCallDTO> getById(Long scoringCaseId, Long scoringBureauCallId);

    /**
     * Updates an existing scoring bureau call identified by its unique IDs with the provided data.
     *
     * @param scoringCaseId the unique identifier of the scoring case to which the bureau call belongs
     * @param scoringBureauCallId the unique identifier of the scoring bureau call to be updated
     * @param dto the updated details of the scoring bureau call, encapsulated in a ScoringBureauCallDTO object
     * @return a Mono emitting the updated ScoringBureauCallDTO if the operation is successful
     */
    Mono<ScoringBureauCallDTO> update(Long scoringCaseId, Long scoringBureauCallId, ScoringBureauCallDTO dto);

    /**
     * Deletes a specific scoring bureau call associated with a given scoring case.
     *
     * @param scoringCaseId the unique identifier of the scoring case to which the bureau call belongs
     * @param scoringBureauCallId the unique identifier of the scoring bureau call to be deleted
     * @return a Mono that completes with no value if the deletion is successful
     */
    Mono<Void> delete(Long scoringCaseId, Long scoringBureauCallId);
}