package com.firefly.core.lending.scoring.core.services.result.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.mappers.result.v1.ScoringResultMapper;
import com.firefly.core.lending.scoring.interfaces.dtos.result.v1.ScoringResultDTO;
import com.firefly.core.lending.scoring.models.entities.result.v1.ScoringResult;
import com.firefly.core.lending.scoring.models.repositories.result.v1.ScoringResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ScoringResultServiceImpl implements ScoringResultService {

    @Autowired
    private ScoringResultRepository repository;

    @Autowired
    private ScoringResultMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringResultDTO>> findAll(Long scoringCaseId, Long scoringRequestId, FilterRequest<ScoringResultDTO> filterRequest) {
        filterRequest.getFilters().setScoringRequestId(scoringRequestId);
        return FilterUtils.createFilter(
                ScoringResult.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringResultDTO> create(Long scoringCaseId, Long scoringRequestId, ScoringResultDTO dto) {
        dto.setScoringRequestId(scoringRequestId);
        ScoringResult entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringResultDTO> getById(Long scoringCaseId, Long scoringRequestId, Long scoringResultId) {
        return repository.findById(scoringResultId)
                .filter(result -> result.getScoringRequestId().equals(scoringRequestId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringResultDTO> update(Long scoringCaseId, Long scoringRequestId, Long scoringResultId, ScoringResultDTO dto) {
        return repository.findById(scoringResultId)
                .filter(result -> result.getScoringRequestId().equals(scoringRequestId))
                .flatMap(existing -> {
                    ScoringResult updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setScoringResultId(scoringResultId);
                    updatedEntity.setCreatedAt(existing.getCreatedAt());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long scoringCaseId, Long scoringRequestId, Long scoringResultId) {
        return repository.findById(scoringResultId)
                .filter(result -> result.getScoringRequestId().equals(scoringRequestId))
                .flatMap(repository::delete);
    }
}