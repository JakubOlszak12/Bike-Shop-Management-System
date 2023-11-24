DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(400) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    price DECIMAL(20,2) NOT NULL,
    created timestamp
);
