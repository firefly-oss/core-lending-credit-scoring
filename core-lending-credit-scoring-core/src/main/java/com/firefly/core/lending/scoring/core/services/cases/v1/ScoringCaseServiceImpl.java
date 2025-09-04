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

import java.util.UUID;

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
    public Mono<ScoringCaseDTO> getById(UUID scoringCaseId) {
        return repository.findById(scoringCaseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringCaseDTO> update(UUID scoringCaseId, ScoringCaseDTO dto) {
        return repository.findById(scoringCaseId)
                .flatMap(existingScoringCase -> {
                    ScoringCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setScoringCaseId(existingScoringCase.getScoringCaseId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID scoringCaseId) {
        return repository.deleteById(scoringCaseId);
    }
}
