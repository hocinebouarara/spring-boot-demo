-- CREATE SEQUENCE customer_id_sequence;
CREATE TABLE customer(
--     i use this statment if i use the bigint as a type of the ID but when i use BIGSERIAL i do not need this.
--     id BIGINT DEFAULT nextval('customer_id_sequence') PRIMARY KEY
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    age INT NOT NULL
)