package com.firefly.core.lending.scoring.core.services.request.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.interfaces.dtos.request.v1.ScoringRequestDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ScoringRequestService {

    /**
     * Retrieves a paginated list of ScoringRequestDTO objects associated with the specified scoring case ID
     * based on the provided filter criteria.
     *
     * @param scoringCaseId the unique identifier of the scoring case for which the scoring requests are to be retrieved
     * @param filterRequest the filtering criteria used for retrieving the scoring requests, encapsulated within a FilterRequest object
     * @return a Mono emitting a PaginationResponse containing the filtered list of ScoringRequestDTO objects
     */
    Mono<PaginationResponse<ScoringRequestDTO>> findAll(UUID scoringCaseId,
                                                        FilterRequest<ScoringRequestDTO> filterRequest);

    /**
     * Creates a new scoring request associated with the specified scoring case.
     *
     * @param scoringCaseId the unique identifier of the scoring case to which the scoring request belongs
     * @param dto the ScoringRequestDTO object containing the details of the scoring request to be created
     * @return a Mono emitting the created ScoringRequestDTO object
     */
    Mono<ScoringRequestDTO> create(UUID scoringCaseId, ScoringRequestDTO dto);

    /**
     * Retrieves a ScoringRequestDTO based on the provided scoring case ID and scoring request ID.
     *
     * @param scoringCaseId the unique identifier of the scoring case associated with the scoring request
     * @param scoringRequestId the unique identifier of the specific scoring request to retrieve
     * @return a Mono emitting the ScoringRequestDTO corresponding to the provided identifiers, or an empty Mono if not found
     */
    Mono<ScoringRequestDTO> getById(UUID scoringCaseId, UUID scoringRequestId);

    /**
     * Updates an existing scoring request identified by the scoring case ID and scoring request ID
     * with the provided details.
     *
     * @param scoringCaseId the unique identifier of the scoring case to which the scoring request belongs
     * @param scoringRequestId the unique identifier of the scoring request to be updated
     * @param dto the updated details of the scoring request, encapsulated in a ScoringRequestDTO object
     * @return a Mono emitting the updated ScoringRequestDTO if the update operation succeeds
     */
    Mono<ScoringRequestDTO> update(UUID scoringCaseId, UUID scoringRequestId, ScoringRequestDTO dto);

    /**
     * Deletes a specific scoring request identified by its unique identifiers for the scoring case and scoring request.
     *
     * @param scoringCaseId the unique identifier of the scoring case associated with the scoring request
     * @param scoringRequestId the unique identifier of the scoring request to be deleted
     * @return a Mono that completes with no value if the deletion is successful
     */
    Mono<Void> delete(UUID scoringCaseId, UUID scoringRequestId);
}
