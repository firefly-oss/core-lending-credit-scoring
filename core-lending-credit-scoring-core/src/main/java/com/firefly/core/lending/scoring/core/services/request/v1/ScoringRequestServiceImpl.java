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


package com.firefly.core.lending.scoring.core.services.request.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.filters.FilterUtils;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.mappers.request.v1.ScoringRequestMapper;
import com.firefly.core.lending.scoring.interfaces.dtos.request.v1.ScoringRequestDTO;
import com.firefly.core.lending.scoring.models.entities.request.v1.ScoringRequest;
import com.firefly.core.lending.scoring.models.repositories.request.v1.ScoringRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ScoringRequestServiceImpl implements ScoringRequestService {

    @Autowired
    private ScoringRequestRepository repository;

    @Autowired
    private ScoringRequestMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringRequestDTO>> findAll(UUID scoringCaseId, FilterRequest<ScoringRequestDTO> filterRequest) {
        filterRequest.getFilters().setScoringCaseId(scoringCaseId);
        return FilterUtils.createFilter(
                ScoringRequest.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringRequestDTO> create(UUID scoringCaseId, ScoringRequestDTO dto) {
        dto.setScoringCaseId(scoringCaseId);
        ScoringRequest entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringRequestDTO> getById(UUID scoringCaseId, UUID scoringRequestId) {
        return repository.findById(scoringRequestId)
                .filter(entity -> scoringCaseId.equals(entity.getScoringCaseId()))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringRequestDTO> update(UUID scoringCaseId, UUID scoringRequestId, ScoringRequestDTO dto) {
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
    public Mono<Void> delete(UUID scoringCaseId, UUID scoringRequestId) {
        return repository.findById(scoringRequestId)
                .filter(entity -> scoringCaseId.equals(entity.getScoringCaseId()))
                .flatMap(repository::delete)
                .then();
    }
}
