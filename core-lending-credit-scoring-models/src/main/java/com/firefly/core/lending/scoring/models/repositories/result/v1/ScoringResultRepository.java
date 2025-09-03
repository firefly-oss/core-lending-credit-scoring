package com.firefly.core.lending.scoring.models.repositories.result.v1;

import com.firefly.core.lending.scoring.models.entities.result.v1.ScoringResult;
import com.firefly.core.lending.scoring.models.repositories.BaseRepository;

import java.util.UUID;

public interface ScoringResultRepository extends BaseRepository<ScoringResult, UUID> {
}
