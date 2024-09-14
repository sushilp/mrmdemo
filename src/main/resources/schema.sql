CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Example of creating the table schema
CREATE TABLE maintenance_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    priority VARCHAR(255) NOT NULL,
    description VARCHAR(500) NOT NULL,
    owner VARCHAR(255) NOT NULL,
    approver VARCHAR(255),
    approval_comments VARCHAR(255),
    rejection_comments VARCHAR(255),
    datetime_created TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL
);
