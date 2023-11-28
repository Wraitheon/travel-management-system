CREATE TABLE Users (
    email VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    date_of_birth DATE,
    usertype VARCHAR(50),
    cnic VARCHAR(15),
    phone_number VARCHAR(15),
    CONSTRAINT chk_email CHECK (email LIKE '%_@__%.__%') -- Check for a basic email format
);