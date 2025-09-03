-- V4 - CONVERT ALL ID FIELDS FROM BIGSERIAL TO UUID

-- Enable UUID extension if not already enabled
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================================================================
-- STEP 1: Add new UUID columns alongside existing BIGSERIAL columns
-- ========================================================================

-- Add UUID columns to scoring_case
ALTER TABLE scoring_case ADD COLUMN scoring_case_id_uuid UUID DEFAULT uuid_generate_v4();
ALTER TABLE scoring_case ADD COLUMN loan_application_id_uuid UUID;
ALTER TABLE scoring_case ADD COLUMN customer_id_uuid UUID;

-- Add UUID columns to scoring_model
ALTER TABLE scoring_model ADD COLUMN scoring_model_id_uuid UUID DEFAULT uuid_generate_v4();

-- Add UUID columns to scoring_request
ALTER TABLE scoring_request ADD COLUMN scoring_request_id_uuid UUID DEFAULT uuid_generate_v4();
ALTER TABLE scoring_request ADD COLUMN scoring_case_id_uuid UUID;
ALTER TABLE scoring_request ADD COLUMN scoring_model_id_uuid UUID;

-- Add UUID columns to scoring_result
ALTER TABLE scoring_result ADD COLUMN scoring_result_id_uuid UUID DEFAULT uuid_generate_v4();
ALTER TABLE scoring_result ADD COLUMN scoring_request_id_uuid UUID;

-- Add UUID columns to scoring_bureau_call
ALTER TABLE scoring_bureau_call ADD COLUMN scoring_bureau_call_id_uuid UUID DEFAULT uuid_generate_v4();
ALTER TABLE scoring_bureau_call ADD COLUMN scoring_case_id_uuid UUID;

-- ========================================================================
-- STEP 2: Populate UUID foreign key columns with corresponding values
-- ========================================================================

-- Update scoring_request foreign keys
UPDATE scoring_request 
SET scoring_case_id_uuid = sc.scoring_case_id_uuid 
FROM scoring_case sc 
WHERE scoring_request.scoring_case_id = sc.scoring_case_id;

UPDATE scoring_request 
SET scoring_model_id_uuid = sm.scoring_model_id_uuid 
FROM scoring_model sm 
WHERE scoring_request.scoring_model_id = sm.scoring_model_id;

-- Update scoring_result foreign keys
UPDATE scoring_result 
SET scoring_request_id_uuid = sr.scoring_request_id_uuid 
FROM scoring_request sr 
WHERE scoring_result.scoring_request_id = sr.scoring_request_id;

-- Update scoring_bureau_call foreign keys
UPDATE scoring_bureau_call 
SET scoring_case_id_uuid = sc.scoring_case_id_uuid 
FROM scoring_case sc 
WHERE scoring_bureau_call.scoring_case_id = sc.scoring_case_id;

-- ========================================================================
-- STEP 3: Drop existing foreign key constraints
-- ========================================================================

ALTER TABLE scoring_bureau_call DROP CONSTRAINT IF EXISTS fk_bureau_call_case;
ALTER TABLE scoring_request DROP CONSTRAINT IF EXISTS fk_request_case;
ALTER TABLE scoring_request DROP CONSTRAINT IF EXISTS fk_request_model;
ALTER TABLE scoring_result DROP CONSTRAINT IF EXISTS fk_result_request;

-- ========================================================================
-- STEP 4: Drop old columns and rename UUID columns
-- ========================================================================

-- scoring_case table
ALTER TABLE scoring_case DROP COLUMN scoring_case_id;
ALTER TABLE scoring_case DROP COLUMN loan_application_id;
ALTER TABLE scoring_case DROP COLUMN customer_id;
ALTER TABLE scoring_case RENAME COLUMN scoring_case_id_uuid TO scoring_case_id;
ALTER TABLE scoring_case RENAME COLUMN loan_application_id_uuid TO loan_application_id;
ALTER TABLE scoring_case RENAME COLUMN customer_id_uuid TO customer_id;

-- scoring_model table
ALTER TABLE scoring_model DROP COLUMN scoring_model_id;
ALTER TABLE scoring_model RENAME COLUMN scoring_model_id_uuid TO scoring_model_id;

-- scoring_request table
ALTER TABLE scoring_request DROP COLUMN scoring_request_id;
ALTER TABLE scoring_request DROP COLUMN scoring_case_id;
ALTER TABLE scoring_request DROP COLUMN scoring_model_id;
ALTER TABLE scoring_request RENAME COLUMN scoring_request_id_uuid TO scoring_request_id;
ALTER TABLE scoring_request RENAME COLUMN scoring_case_id_uuid TO scoring_case_id;
ALTER TABLE scoring_request RENAME COLUMN scoring_model_id_uuid TO scoring_model_id;

-- scoring_result table
ALTER TABLE scoring_result DROP COLUMN scoring_result_id;
ALTER TABLE scoring_result DROP COLUMN scoring_request_id;
ALTER TABLE scoring_result RENAME COLUMN scoring_result_id_uuid TO scoring_result_id;
ALTER TABLE scoring_result RENAME COLUMN scoring_request_id_uuid TO scoring_request_id;

-- scoring_bureau_call table
ALTER TABLE scoring_bureau_call DROP COLUMN scoring_bureau_call_id;
ALTER TABLE scoring_bureau_call DROP COLUMN scoring_case_id;
ALTER TABLE scoring_bureau_call RENAME COLUMN scoring_bureau_call_id_uuid TO scoring_bureau_call_id;
ALTER TABLE scoring_bureau_call RENAME COLUMN scoring_case_id_uuid TO scoring_case_id;

-- ========================================================================
-- STEP 5: Set primary keys and constraints
-- ========================================================================

-- Set primary keys
ALTER TABLE scoring_case ADD PRIMARY KEY (scoring_case_id);
ALTER TABLE scoring_model ADD PRIMARY KEY (scoring_model_id);
ALTER TABLE scoring_request ADD PRIMARY KEY (scoring_request_id);
ALTER TABLE scoring_result ADD PRIMARY KEY (scoring_result_id);
ALTER TABLE scoring_bureau_call ADD PRIMARY KEY (scoring_bureau_call_id);

-- Add NOT NULL constraints for required UUID foreign keys
ALTER TABLE scoring_request ALTER COLUMN scoring_case_id SET NOT NULL;
ALTER TABLE scoring_request ALTER COLUMN scoring_model_id SET NOT NULL;
ALTER TABLE scoring_result ALTER COLUMN scoring_request_id SET NOT NULL;
ALTER TABLE scoring_bureau_call ALTER COLUMN scoring_case_id SET NOT NULL;

-- ========================================================================
-- STEP 6: Recreate foreign key constraints
-- ========================================================================

ALTER TABLE scoring_bureau_call 
ADD CONSTRAINT fk_bureau_call_case 
FOREIGN KEY (scoring_case_id) REFERENCES scoring_case (scoring_case_id);

ALTER TABLE scoring_request 
ADD CONSTRAINT fk_request_case 
FOREIGN KEY (scoring_case_id) REFERENCES scoring_case (scoring_case_id);

ALTER TABLE scoring_request 
ADD CONSTRAINT fk_request_model 
FOREIGN KEY (scoring_model_id) REFERENCES scoring_model (scoring_model_id);

ALTER TABLE scoring_result 
ADD CONSTRAINT fk_result_request 
FOREIGN KEY (scoring_request_id) REFERENCES scoring_request (scoring_request_id);