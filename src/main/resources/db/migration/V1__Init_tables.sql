CREATE TABLE users (
    id            BIGSERIAL    PRIMARY KEY,
    username      VARCHAR(16)  NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password_hash VARCHAR(60)  NOT NULL,
    first_name    VARCHAR(50)  NOT NULL,
    last_name     VARCHAR(50)  NOT NULL,
    role          VARCHAR(10)  NOT NULL
);

CREATE TABLE blacklisted_refresh_token_ids (
    id BIGINT PRIMARY KEY
);
