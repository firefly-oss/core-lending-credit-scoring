-- V2 - CREATE TABLES FOR THE CREDIT SCORING SUBMODULE

-- ========================================================================
-- TABLE: scoring_case
-- ========================================================================
CREATE TABLE IF NOT EXISTS scoring_case (
                                            scoring_case_id      BIGSERIAL PRIMARY KEY,
                                            loan_application_id  BIGINT,  -- External reference (no FK)
                                            customer_id          BIGINT,  -- External reference (no FK)
                                            case_status          case_status NOT NULL,
                                            case_type            case_type NOT NULL,
                                            remarks              TEXT,
                                            created_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at           TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: scoring_bureau_call
-- ========================================================================
CREATE TABLE IF NOT EXISTS scoring_bureau_call (
                                                   scoring_bureau_call_id  BIGSERIAL PRIMARY KEY,
                                                   scoring_case_id         BIGINT NOT NULL,
                                                   bureau_name             bureau_name NOT NULL,
                                                   call_date               TIMESTAMP NOT NULL DEFAULT NOW(),
    reference_code          VARCHAR(100),
    is_success              BOOLEAN DEFAULT FALSE,
    raw_response            TEXT,
    note                    TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_bureau_call_case
    FOREIGN KEY (scoring_case_id)
    REFERENCES scoring_case (scoring_case_id)
    );

-- ========================================================================
-- TABLE: scoring_model
-- ========================================================================
CREATE TABLE IF NOT EXISTS scoring_model (
                                             scoring_model_id     BIGSERIAL PRIMARY KEY,
                                             model_name           VARCHAR(100) NOT NULL,
    model_version        VARCHAR(50) NOT NULL,
    score_dimension      score_dimension NOT NULL,
    model_approach       model_approach NOT NULL,
    model_status         model_status NOT NULL,
    note                 TEXT,
    created_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at           TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: scoring_request
-- ========================================================================
CREATE TABLE IF NOT EXISTS scoring_request (
                                               scoring_request_id   BIGSERIAL PRIMARY KEY,
                                               scoring_case_id      BIGINT NOT NULL,
                                               scoring_model_id     BIGINT NOT NULL,
                                               request_timestamp    TIMESTAMP NOT NULL DEFAULT NOW(),
    request_status       request_status NOT NULL,
    note                 TEXT,
    created_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_request_case
    FOREIGN KEY (scoring_case_id)
    REFERENCES scoring_case (scoring_case_id),
    CONSTRAINT fk_request_model
    FOREIGN KEY (scoring_model_id)
    REFERENCES scoring_model (scoring_model_id)
    );

-- ========================================================================
-- TABLE: scoring_result
-- ========================================================================
CREATE TABLE IF NOT EXISTS scoring_result (
                                              scoring_result_id    BIGSERIAL PRIMARY KEY,
                                              scoring_request_id   BIGINT NOT NULL,
                                              score_value          DECIMAL(9,4),
    score_outcome        score_outcome NOT NULL,
    reason_codes         TEXT,    -- can store JSON with codes
    extra_details        TEXT,    -- additional fields or extended result data
    created_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_result_request
    FOREIGN KEY (scoring_request_id)
    REFERENCES scoring_request (scoring_request_id)
    );
