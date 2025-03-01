package com.catalis.core.lending.scoring.core.services.request.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.scoring.core.mappers.request.v1.ScoringRequestMapper;
import com.catalis.core.lending.scoring.interfaces.dtos.request.v1.ScoringRequestDTO;
import com.catalis.core.lending.scoring.models.entities.request.v1.ScoringRequest;
import com.catalis.core.lending.scoring.models.repositories.request.v1.ScoringRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ScoringRequestServiceImpl implements ScoringRequestService {

    @Autowired
    private ScoringRequestRepository repository;

    @Autowired
    private ScoringRequestMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringRequestDTO>> findAll(Long scoringCaseId, FilterRequest<ScoringRequestDTO> filterRequest) {
        filterRequest.getFilters().setScoringCaseId(scoringCaseId);
        return FilterUtils.createFilter(
                ScoringRequest.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringRequestDTO> create(Long scoringCaseId, ScoringRequestDTO dto) {
        dto.setScoringCaseId(scoringCaseId);
        ScoringRequest entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringRequestDTO> getById(Long scoringCaseId, Long scoringRequestId) {
        return repository.findById(scoringRequestId)
                .filter(entity -> scoringCaseId.equals(entity.getScoringCaseId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringRequestDTO> update(Long scoringCaseId, Long scoringRequestId, ScoringRequestDTO dto) {
        return repository.findById(scoringRequestId)
                .filter(entity -> scoringCaseId.equals(entity.getScoringCaseId()))
                .map(existingEntity -> {
                    ScoringRequest updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setScoringRequestId(existingEntity.getScoringRequestId());
                    return updatedEntity;
                })
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long scoringCaseId, Long scoringRequestId) {
        return repository.findById(scoringRequestId)
                .filter(entity -> scoringCaseId.equals(entity.getScoringCaseId()))
                .flatMap(repository::delete)
                .then();
    }
}
