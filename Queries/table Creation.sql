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

INSERT INTO Users (email, name, age, date_of_birth, usertype, cnic, phone_number, password)
VALUES ('agency1@example.com', 'Travel Agency 1', 28, '1995-05-15', 'Travel Agency', '111223344556677', '9876543210', '1234');

INSERT INTO Users (email, name, age, date_of_birth, usertype, cnic, phone_number, password)
VALUES ('agency2@example.com', 'Travel Agency 2', 32, '1990-11-23', 'Travel Agency', '998877665544332', '1234567890', '1234');

INSERT INTO Users (email, name, age, date_of_birth, usertype, cnic, phone_number, password)
VALUES ('agency3@example.com', 'Travel Agency 3', 26, '1997-08-08', 'Travel Agency', '123456789012345', '8765432109', '1234');

-- Insert trips for 'Hunza'
INSERT INTO Trip (user_email, destination_id, trip_date, prices, number_of_days)
VALUES
    ('agency1@example.com', 1, '2023-05-10', 1500.00, 5),
    ('agency2@example.com', 1, '2023-06-15', 1800.00, 7);

-- Insert trips for 'Gilgit'
INSERT INTO Trip (user_email, destination_id, trip_date, prices, number_of_days)
VALUES
    ('agency2@example.com', 2, '2023-07-20', 1200.00, 4),
    ('agency3@example.com', 2, '2023-08-25', 1600.00, 6);

-- Insert trips for 'Naran'
INSERT INTO Trip (user_email, destination_id, trip_date, prices, number_of_days)
VALUES
    ('agency1@example.com', 3, '2023-09-30', 2000.00, 8),
    ('agency3@example.com', 3, '2023-10-15', 2200.00, 9);


CREATE TABLE Destinations (
    destination_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_name VARCHAR(255) NOT NULL
);

INSERT INTO Destinations (destination_name) VALUES
    ('Hunza'),
    ('Gilgit'),
    ('Naran');

-- For Hunza
INSERT INTO Landmarks (destination_id, landmark_name) VALUES
    (1, 'Attabad Lake'),
    (1, 'Karimabad Fort'),
    (1, 'Eagle''s Nest');

-- For Gilgit
INSERT INTO Landmarks (destination_id, landmark_name) VALUES
    (2, 'Gilgit River'),
    (2, 'Shangrila Resort'),
    (2, 'Fairy Meadows');

-- For Naran
INSERT INTO Landmarks (destination_id, landmark_name) VALUES
    (3, 'Saif-ul-Mulook Lake'),
    (3, 'Shogran'),
    (3, 'Babusar Top');


CREATE TABLE Trip (
    trip_id INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(255),
    destination_id INT,
    trip_date DATE NOT NULL,
    prices DECIMAL(10, 2) NOT NULL,
    number_of_days INT,
    FOREIGN KEY (user_email) REFERENCES Users(email),
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
);

CREATE TABLE Review (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(255) NOT NULL,
    reviewed_user_email VARCHAR(255) NOT NULL,
    rating INT NOT NULL,
    comment VARCHAR(255),
    review_date DATE NOT NULL,
    FOREIGN KEY (user_email) REFERENCES Users(email),
    FOREIGN KEY (reviewed_user_email) REFERENCES Users(email)
);

SELECT
    u.name AS user_name,
    d.destination_name AS trip_destination,
    t.prices AS trip_price,
    t.number_of_days AS trip_number_of_days,
    b.booking_date,
    p.payment_date,
    p.amount,
    p.payment_method,
    b.discount_amount,
    (t.prices - p.amount - b.discount_amount) AS remaining_amount
FROM
    Users u
JOIN Booking b ON u.email =  b.user_email
JOIN Trip t ON t.trip_id = b.trip_id
JOIN Destinations d ON t.destination_id = d.destination_id
JOIN Payment p ON b.booking_id = p.booking_id
where t.user_email = "riyan@gg.com";

SELECT
    u.name AS user_name,
    d.destination_name AS trip_destination,
    t.prices AS trip_price,
    t.number_of_days AS trip_number_of_days,
    b.booking_date,
    p.payment_date,
    p.amount,
    p.payment_method,
    b.discount_amount,
    (t.prices - p.amount - b.discount_amount) AS remaining_amount
FROM
    Users u
JOIN Trip t ON u.email = t.user_email
JOIN Destinations d ON t.destination_id = d.destination_id
JOIN Booking b ON t.trip_id = b.trip_id
JOIN Payment p ON b.booking_id = p.booking_id
where b.user_email = "meow@nu.edu.com";



CREATE TABLE Booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    user_email VARCHAR(255),
    discount_amount DECIMAL(10, 2),
    booking_date DATE NOT NULL,
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id),
    FOREIGN KEY (user_email) REFERENCES Users(email)
);

CREATE TABLE Itinerary (
    itinerary_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    
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


select count(*) from restaurants
select * from restaurants
select * from review
select * from users
select * from trip;
select * from ItineraryRestaurants;
select * from loyaltyprogram
select * from Transportation
select * from itinerary;
select * from booking
select * from payment
select * from ItineraryAccommodation;
select * from activities;

select * from ItineraryTransportation

CREATE TABLE Restaurants (
    restaurant_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT,
    restaurant_name VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2),
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
);

INSERT INTO Restaurants (destination_id, restaurant_name, cost)
VALUES
    (3, 'Punjab Tikka', 50.00),
    (3, 'Fairy Tikka', 40.50),
    (3, 'Moon restaurant', 75.25);

CREATE TABLE ItineraryRestaurants (
    restaurant_id INT,
    itinerary_id INT,
    scheduledTime DateTime,
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id),
    FOREIGN KEY (itinerary_id) REFERENCES Itinerary(itinerary_id)
);



CREATE TABLE Accommodation (
    accommodation_id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT,
    location VARCHAR(255) NOT NULL,
    motel_name VARCHAR(255) NOT NULL,

    cost DECIMAL(10, 2),

    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
);

CREATE TABLE ItineraryAccommodation (

    itinerary_id INT,
    accommodation_id INT,
    check_in_date DATETime NOT NULL,
    
    FOREIGN KEY (accommodation_id) REFERENCES Accommodation(accommodation_id),

    FOREIGN KEY (itinerary_id) REFERENCES Itinerary(itinerary_id)
    
)





CREATE TABLE Activities (
    activity_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    activity_name VARCHAR(255) NOT NULL,
    activity_date DATETIME NOT NULL,
    activity_description TEXT,
    cost DECIMAL(10, 2),

    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id)
);

select * from trip



CREATE TABLE Transportation (
    transportation_id INT PRIMARY KEY AUTO_INCREMENT,
    mode_of_transport VARCHAR(50) NOT NULL -- e.g., 'Air', 'Coaster', 'Car'
    
);

INSERT INTO Transportation (mode_of_transport) VALUES ('Air');
INSERT INTO Transportation (mode_of_transport) VALUES ('Coaster');
INSERT INTO Transportation (mode_of_transport) VALUES ('Bus');
INSERT INTO Transportation (mode_of_transport) VALUES ('Car');
INSERT INTO Transportation (mode_of_transport) VALUES ('Jeep');

INSERT INTO TransportationCost (transportation_id, destination_id, cost) VALUES
    (1, 1, 12000.00), -- Air to Hunza
    (2, 1, 800.00),   -- Coaster to Hunza
    (3, 1, 600.00),   -- Bus to Hunza
    (4, 1, 5000.00),  -- Car to Hunza
    (5, 1, 2500.00),  -- Jeep to Hunza

    (1, 2, 10000.00), -- Air to Gilgit
    (2, 2, 800.00),   -- Coaster to Gilgit
    (3, 2, 600.00),   -- Bus to Gilgit
    (4, 2, 4000.00),  -- Car to Gilgit
    (5, 2, 2000.00),  -- Jeep to Gilgit

    (1, 3, 8000.00),  -- Air to Naran
    (2, 3, 600.00),   -- Coaster to Naran
    (3, 3, 400.00),   -- Bus to Naran
    (4, 3, 3000.00),  -- Car to Naran
    (5, 3, 1500.00); 

CREATE TABLE ItineraryTransportation (

    transportation_id INT NOT NULL,
    itinerary_id INT NOT NULL,
    departure_date DATETIME NOT NULL,
   
    FOREIGN KEY (transportation_id) REFERENCES Transportation(transportation_id),

    FOREIGN KEY (itinerary_id) REFERENCES Itinerary(itinerary_id)
    
)

Create TABLE TransportationCost (
    transportation_id INT,
    destination_id INT, 
    cost DECIMAL(10, 2),
    FOREIGN KEY (transportation_id) REFERENCES Transportation(transportation_id),
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
)






CREATE TABLE Landmarks (
    destination_id INT,
    landmark_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (destination_id) REFERENCES Destinations(destination_id)
);
INSERT INTO loyaltyProgram(user_email, points)values("meow@nu.edu.com", 31)

drop table LoyaltyProgram;
CREATE TABLE LoyaltyProgram (
    user_email VARCHAR(255) NOT NULL,
    points INT DEFAULT 0,
    FOREIGN KEY (user_email) REFERENCES Users(email)
);
--  add category of luxury for items;






CREATE TABLE Budget (
    budget_id INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(255) NOT NULL,
    accommodation_cost DECIMAL(10, 2),
    transportation_cost DECIMAL(10, 2),
    activities_cost DECIMAL(10, 2),
    restaurant_cost DECIMAL(10, 2),
    FOREIGN KEY (user_email) REFERENCES Users(email),
    FOREIGN KEY (trip_id) REFERENCES Trip(trip_id)
);

CREATE TABLE GuideBooking (
	booking_id INT PRIMARY KEY AUTO_INCREMENT,
    guideEmail varchar(255),
    travellerEmail varchar(255),
    date date,
    days int,
	 FOREIGN KEY (guideEmail) REFERENCES Users(email),
	  FOREIGN KEY (travellerEmail) REFERENCES Users(email)

)


CREATE TABLE Messages (
    message_id INT PRIMARY KEY AUTO_INCREMENT,
    trip_id INT,
    sender_user_email varchar(255),
    message_text TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (trip_id) REFERENCES trip(trip_id),
    FOREIGN KEY (sender_user_email) REFERENCES Users(email)
);