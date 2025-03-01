-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL ENUM TYPES

-------------------------
-- case_status
-------------------------
CREATE CAST (varchar AS case_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- case_type
-------------------------
CREATE CAST (varchar AS case_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- bureau_name
-------------------------
CREATE CAST (varchar AS bureau_name)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- score_dimension
-------------------------
CREATE CAST (varchar AS score_dimension)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- model_approach
-------------------------
CREATE CAST (varchar AS model_approach)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- model_status
-------------------------
CREATE CAST (varchar AS model_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- request_status
-------------------------
CREATE CAST (varchar AS request_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- score_outcome
-------------------------
CREATE CAST (varchar AS score_outcome)
    WITH INOUT
    AS IMPLICIT;
