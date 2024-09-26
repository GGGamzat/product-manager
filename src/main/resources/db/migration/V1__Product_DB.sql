CREATE SEQUENCE product_seq start with 1 increment by 50;

CREATE TABLE Product(
    id      int8  PRIMARY KEY,
    name   VARCHAR(150)    NOT NULL,
    price    DOUBLE PRECISION,
    quantity    INTEGER
)