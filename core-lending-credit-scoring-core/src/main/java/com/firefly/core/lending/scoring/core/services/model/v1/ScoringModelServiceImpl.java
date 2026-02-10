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


package com.firefly.core.lending.scoring.core.services.model.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.filters.FilterUtils;
import org.fireflyframework.core.queries.PaginationResponse;
import com.firefly.core.lending.scoring.core.mappers.model.v1.ScoringModelMapper;
import com.firefly.core.lending.scoring.interfaces.dtos.model.v1.ScoringModelDTO;
import com.firefly.core.lending.scoring.models.entities.model.v1.ScoringModel;
import com.firefly.core.lending.scoring.models.repositories.model.v1.ScoringModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class ScoringModelServiceImpl implements ScoringModelService {

    @Autowired
    private ScoringModelRepository repository;

    @Autowired
    private ScoringModelMapper mapper;

    @Override
    public Mono<PaginationResponse<ScoringModelDTO>> findAll(FilterRequest<ScoringModelDTO> filterRequest) {
        return FilterUtils.createFilter(
                ScoringModel.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ScoringModelDTO> create(ScoringModelDTO dto) {
        return Mono.just(dto)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ScoringModelDTO> getById(UUID scoringModelId) {
        return repository.findById(scoringModelId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<ScoringModelDTO> update(UUID scoringModelId, ScoringModelDTO dto) {
        return repository.findById(scoringModelId)
                .flatMap(existingEntity -> {
                    ScoringModel updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setScoringModelId(existingEntity.getScoringModelId());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID scoringModelId) {
        return repository.findById(scoringModelId)
                .flatMap(repository::delete);
    }
}
