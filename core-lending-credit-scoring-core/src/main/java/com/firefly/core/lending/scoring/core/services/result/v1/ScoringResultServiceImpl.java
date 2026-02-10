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


package com.firefly.core.lending.scoring.core.services.result.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.filters.FilterUtils;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.mappers.result.v1.ScoringResultMapper;
import com.firefly.core.lending.scoring.interfaces.dtos.result.v1.ScoringResultDTO;
import com.firefly.core.lending.scoring.models.entities.result.v1.ScoringResult;
import com.firefly.core.lending.scoring.models.repositories.result.v1.ScoringResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ScoringResultServiceImpl implements ScoringResultService {

    @Autowired
    private ScoringResultRepository repository;

    @Autowired
    private ScoringResultMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringResultDTO>> findAll(UUID scoringCaseId, UUID scoringRequestId, FilterRequest<ScoringResultDTO> filterRequest) {
        filterRequest.getFilters().setScoringRequestId(scoringRequestId);
        return FilterUtils.createFilter(
                ScoringResult.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringResultDTO> create(UUID scoringCaseId, UUID scoringRequestId, ScoringResultDTO dto) {
        dto.setScoringRequestId(scoringRequestId);
        ScoringResult entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringResultDTO> getById(UUID scoringCaseId, UUID scoringRequestId, UUID scoringResultId) {
        return repository.findById(scoringResultId)
                .filter(result -> result.getScoringRequestId().equals(scoringRequestId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringResultDTO> update(UUID scoringCaseId, UUID scoringRequestId, UUID scoringResultId, ScoringResultDTO dto) {
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
    public Mono<Void> delete(UUID scoringCaseId, UUID scoringRequestId, UUID scoringResultId) {
        return repository.findById(scoringResultId)
                .filter(result -> result.getScoringRequestId().equals(scoringRequestId))
                .flatMap(repository::delete);
    }
}