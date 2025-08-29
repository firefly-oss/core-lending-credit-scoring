package com.firefly.core.lending.scoring.core.services.cases.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.mappers.cases.v1.ScoringCaseMapper;
import com.firefly.core.lending.scoring.interfaces.dtos.cases.v1.ScoringCaseDTO;
import com.firefly.core.lending.scoring.models.entities.cases.v1.ScoringCase;
import com.firefly.core.lending.scoring.models.repositories.cases.v1.ScoringCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ScoringCaseServiceImpl implements ScoringCaseService {

    @Autowired
    private ScoringCaseRepository repository;

    @Autowired
    private ScoringCaseMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringCaseDTO>> findAll(FilterRequest<ScoringCaseDTO> filterRequest) {
        return FilterUtils.createFilter(
                ScoringCase.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringCaseDTO> create(ScoringCaseDTO dto) {
        ScoringCase scoringCase = mapper.toEntity(dto);
        return repository.save(scoringCase)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringCaseDTO> getById(Long scoringCaseId) {
        return repository.findById(scoringCaseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringCaseDTO> update(Long scoringCaseId, ScoringCaseDTO dto) {
        return repository.findById(scoringCaseId)
                .flatMap(existingScoringCase -> {
                    ScoringCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setScoringCaseId(existingScoringCase.getScoringCaseId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long scoringCaseId) {
        return repository.deleteById(scoringCaseId);
    }
}
