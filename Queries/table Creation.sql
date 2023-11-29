select * from users

CREATE TABLE Users (
    email VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    date_of_birth DATE,
    usertype VARCHAR(50),
    cnic VARCHAR(15),
    phone_number VARCHAR(15),
    password VARCHAR(255),
    CONSTRAINT chk_email CHECK (email LIKE '%_@__%.__%') -- Check for a basic email format
);

CREATE TABLE Destinations (
    destination_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_name VARCHAR(255) NOT NULL
);

CREATE TABLE Trip (
    trip_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT,
    trip_date DATE NOT NULL,
    prices DECIMAL(10, 2) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    number_of_days INT,
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id),
    FOREIGN KEY (user_email) REFERENCES Users(email)
);

CREATE TABLE Review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(255) NOT NULL,
    reviewed_user_email VARCHAR(255) NOT NULL,
    rating INT NOT NULL,
    comment TEXT,
    review_date DATE NOT NULL,
    FOREIGN KEY (user_email) REFERENCES Users(email),
    FOREIGN KEY (reviewed_user_email) REFERENCES Users(email)
);




CREATE TABLE Booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    user_email VARCHAR(255),
    booking_date DATE NOT NULL,
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id),
    FOREIGN KEY (user_email) REFERENCES Users(email)
);

CREATE TABLE Itinerary (
    itinerary_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    activity_name VARCHAR(255) NOT NULL,
    activity_date DATE NOT NULL,
    activity_description TEXT,
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id)
);

CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT,
    payment_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
);

CREATE TABLE TravelRecommendation (
    recommendation_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT,
    recommendation_type VARCHAR(50) NOT NULL,
    recommendation_text TEXT,
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
);

CREATE TABLE Restaurants (
    restaurant_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT,
    restaurant_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
);

CREATE TABLE Landmarks (
    landmark_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT,
    landmark_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
);

CREATE TABLE LoyaltyProgram (
    loyalty_id INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(255) NOT NULL,
    points INT DEFAULT 0,
    tier VARCHAR(50), -- You can define different loyalty tiers if needed
    enrollment_date DATE NOT NULL,
    last_activity_date DATE,
    FOREIGN KEY (user_email) REFERENCES Users(email)
);

CREATE TABLE Transportation (
    transportation_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    mode_of_transport VARCHAR(50) NOT NULL, -- e.g., 'Air', 'Coaster', 'Car'
    departure_date DATE NOT NULL,
    arrival_date DATE NOT NULL,
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id)
);

CREATE TABLE Activities (
    activity_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    activity_name VARCHAR(255) NOT NULL,
    activity_date DATE NOT NULL,
    activity_description TEXT,
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id)
);

CREATE TABLE Accommodation (
    accommodation_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    location VARCHAR(255) NOT NULL,
    motel_name VARCHAR(255) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id)
);


CREATE TABLE Budget (
    budget_id INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(255) NOT NULL,
    trip_id INT,
    accommodation_cost DECIMAL(10, 2),
    transportation_cost DECIMAL(10, 2),
    activities_cost DECIMAL(10, 2),
    restaurant_cost DECIMAL(10, 2),
    other_expenses DECIMAL(10, 2),
    total_budget DECIMAL(10, 2),
    FOREIGN KEY (user_email) REFERENCES Users(email),
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id)
);

CREATE TABLE GroupTable (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(255) NOT NULL,
    creator_user_email VARCHAR(255),
    FOREIGN KEY (creator_user_email) REFERENCES Users(email)
);

CREATE TABLE GroupMembers (
    group_id INT,
    member_user_email VARCHAR(255),
    PRIMARY KEY (group_id, member_user_email),
    FOREIGN KEY (group_id) REFERENCES GroupTable(group_id),
    FOREIGN KEY (member_user_email) REFERENCES Users(email)
);

CREATE TABLE Messages (
    message_id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT,
    sender_user_email varchar(255),
    message_text TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (group_id) REFERENCES GroupTable(group_id),
    FOREIGN KEY (sender_user_email) REFERENCES Users(email)
);