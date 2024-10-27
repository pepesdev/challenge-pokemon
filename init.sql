DROP TABLE IF EXISTS request_log;
CREATE TABLE IF NOT EXISTS request_log(
    id integer NOT NULL, 
    ip_origin VARCHAR,
    date_request DATE,
    method TEXT,
    elapsed_time VARCHAR,
    request TEXT,
    response TEXT
);

CREATE SEQUENCE IF NOT EXISTS public.request_log_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.request_log_seq
    OWNER TO postgres;