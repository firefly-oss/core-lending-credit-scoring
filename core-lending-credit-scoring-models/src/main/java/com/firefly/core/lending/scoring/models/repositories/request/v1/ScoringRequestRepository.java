package com.firefly.core.lending.scoring.models.repositories.request.v1;

import com.firefly.core.lending.scoring.models.entities.request.v1.ScoringRequest;
import com.firefly.core.lending.scoring.models.repositories.BaseRepository;

import java.util.UUID;

public interface ScoringRequestRepository extends BaseRepository<ScoringRequest, UUID> {
}
