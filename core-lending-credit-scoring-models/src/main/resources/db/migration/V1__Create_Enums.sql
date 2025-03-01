-- V1 - CREATE ENUMS FOR CREDIT SCORING SUBMODULE

-- scoring_case -> case_status
CREATE TYPE case_status AS ENUM (
    'OPEN',
    'COMPLETED',
    'FAILED',
    'CANCELLED'
);

-- scoring_case -> case_type
CREATE TYPE case_type AS ENUM (
    'APPLICATION',
    'RE_SCORING',
    'PORTFOLIO_MONITOR',
    'PRE_APPROVAL'
);

-- scoring_bureau_call -> bureau_name
CREATE TYPE bureau_name AS ENUM (
    'AXESOR',
    'EQUIFAX',
    'EXPERIAN',
    'ASNEF',
    'OTHER'
);

-- scoring_model -> score_dimension
CREATE TYPE score_dimension AS ENUM (
    'FINANCIAL',
    'NON_FINANCIAL',
    'COMBINED',
    'BEHAVIORAL'
);

-- scoring_model -> model_approach
CREATE TYPE model_approach AS ENUM (
    'AI',
    'RULES_BASED',
    'HYBRID',
    'STATISTICAL'
);

-- scoring_model -> model_status
CREATE TYPE model_status AS ENUM (
    'ACTIVE',
    'DEPRECATED',
    'TESTING',
    'DISABLED'
);

-- scoring_request -> request_status
CREATE TYPE request_status AS ENUM (
    'COMPLETED',
    'FAILED',
    'PENDING',
    'IN_PROGRESS',
    'TIMEOUT'
);

-- scoring_result -> score_outcome
CREATE TYPE score_outcome AS ENUM (
    'APPROVE',
    'REJECT',
    'MANUAL_REVIEW',
    'REFER_TO_SUPERVISOR'
);
