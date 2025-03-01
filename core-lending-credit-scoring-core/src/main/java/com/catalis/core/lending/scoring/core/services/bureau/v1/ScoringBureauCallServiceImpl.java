package com.catalis.core.lending.scoring.core.services.bureau.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.lending.scoring.core.mappers.bureau.v1.ScoringBureauCallMapper;
import com.catalis.core.lending.scoring.interfaces.dtos.bureau.v1.ScoringBureauCallDTO;
import com.catalis.core.lending.scoring.models.entities.bureau.v1.ScoringBureauCall;
import com.catalis.core.lending.scoring.models.repositories.bureau.v1.ScoringBureauCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ScoringBureauCallServiceImpl implements ScoringBureauCallService {

    @Autowired
    private ScoringBureauCallRepository repository;

    @Autowired
    private ScoringBureauCallMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringBureauCallDTO>> findAll(Long scoringCaseId, FilterRequest<ScoringBureauCallDTO> filterRequest) {
        filterRequest.getFilters().setScoringCaseId(scoringCaseId);
        return FilterUtils.createFilter(
                ScoringBureauCall.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringBureauCallDTO> create(Long scoringCaseId, ScoringBureauCallDTO dto) {
        dto.setScoringCaseId(scoringCaseId);
        ScoringBureauCall entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringBureauCallDTO> getById(Long scoringCaseId, Long scoringBureauCallId) {
        return repository.findById(scoringBureauCallId)
                .filter(entity -> entity.getScoringCaseId().equals(scoringCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringBureauCallDTO> update(Long scoringCaseId, Long scoringBureauCallId, ScoringBureauCallDTO dto) {
        return repository.findById(scoringBureauCallId)
                .filter(entity -> entity.getScoringCaseId().equals(scoringCaseId))
                .flatMap(entity -> {
                    dto.setScoringBureauCallId(scoringBureauCallId);
                    dto.setScoringCaseId(scoringCaseId);
                    ScoringBureauCall updatedEntity = mapper.toEntity(dto);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long scoringCaseId, Long scoringBureauCallId) {
        return repository.findById(scoringBureauCallId)
                .filter(entity -> entity.getScoringCaseId().equals(scoringCaseId))
                .flatMap(repository::delete)
                .then();
    }
}