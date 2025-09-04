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


package com.firefly.core.lending.scoring.core.services.bureau.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.mappers.bureau.v1.ScoringBureauCallMapper;
import com.firefly.core.lending.scoring.interfaces.dtos.bureau.v1.ScoringBureauCallDTO;
import com.firefly.core.lending.scoring.models.entities.bureau.v1.ScoringBureauCall;
import com.firefly.core.lending.scoring.models.repositories.bureau.v1.ScoringBureauCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ScoringBureauCallServiceImpl implements ScoringBureauCallService {

    @Autowired
    private ScoringBureauCallRepository repository;

    @Autowired
    private ScoringBureauCallMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringBureauCallDTO>> findAll(UUID scoringCaseId, FilterRequest<ScoringBureauCallDTO> filterRequest) {
        filterRequest.getFilters().setScoringCaseId(scoringCaseId);
        return FilterUtils.createFilter(
                ScoringBureauCall.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringBureauCallDTO> create(UUID scoringCaseId, ScoringBureauCallDTO dto) {
        dto.setScoringCaseId(scoringCaseId);
        ScoringBureauCall entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringBureauCallDTO> getById(UUID scoringCaseId, UUID scoringBureauCallId) {
        return repository.findById(scoringBureauCallId)
                .filter(entity -> entity.getScoringCaseId().equals(scoringCaseId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringBureauCallDTO> update(UUID scoringCaseId, UUID scoringBureauCallId, ScoringBureauCallDTO dto) {
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
    public Mono<Void> delete(UUID scoringCaseId, UUID scoringBureauCallId) {
        return repository.findById(scoringBureauCallId)
                .filter(entity -> entity.getScoringCaseId().equals(scoringCaseId))
                .flatMap(repository::delete)
                .then();
    }
}