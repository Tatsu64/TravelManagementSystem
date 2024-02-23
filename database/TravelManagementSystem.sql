-- Tạo cơ sở dữ liệu
CREATE DATABASE TravelManagementSystem;
GO

-- Sử dụng cơ sở dữ liệu mới tạo
USE TravelManagementSystem;
GO

-- Tạo bảng Employees
CREATE TABLE Employees (
    employee_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    full_name NVARCHAR(255),
    email NVARCHAR(255),
    position NVARCHAR(50)
);
GO

-- Tạo bảng Users
CREATE TABLE Users (
    user_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    name NVARCHAR(255),
    password NVARCHAR(255),
    email NVARCHAR(255),
    address NVARCHAR(MAX),
    phone NVARCHAR(15),
    role NVARCHAR(50)
);
GO

-- Tạo bảng Tours
CREATE TABLE Tours (
    tour_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    tour_name NVARCHAR(255),
    description NVARCHAR(MAX),
    start_date DATE,
    end_date DATE,
    tour_price DECIMAL(18, 2),
    image_url NVARCHAR(MAX),
    employee_id INT,
    start_location NVARCHAR(255),
    max_capacity INT,
    current_capacity INT,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);
GO

-- Tạo bảng Locations
CREATE TABLE Locations (
    location_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    location_name NVARCHAR(255)
);
GO

-- Tạo bảng Hotels
CREATE TABLE Hotels (
    hotel_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    hotel_name NVARCHAR(255),
    location_id INT,
    price DECIMAL(18, 2),
    image_url NVARCHAR(MAX),
    address NVARCHAR(MAX),
    FOREIGN KEY (location_id) REFERENCES Locations(location_id)
);
GO

-- Tạo bảng Transportations
CREATE TABLE Transportations (
    transportation_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    transportation_name NVARCHAR(255),
    departure_date DATE,
    return_date DATE,
    price DECIMAL(18, 2),
    image_url NVARCHAR(MAX)
);
GO

-- Tạo bảng Restaurants
CREATE TABLE Restaurants (
    restaurant_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	restaurant_name NVARCHAR(255),
    location_id INT,
    reservation_date DATE,
    price DECIMAL(18, 2),
    image_url NVARCHAR(MAX),
    address NVARCHAR(MAX),
    FOREIGN KEY (location_id) REFERENCES Locations(location_id)
);
GO

-- Tạo bảng Bookings
CREATE TABLE Bookings (
    booking_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    tour_id INT,
    user_id INT,
    booking_date DATE,
    number_of_people INT,
    total_price DECIMAL(18, 2),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
GO

-- Tạo bảng Bills
CREATE TABLE Bills (
    bill_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    booking_id INT UNIQUE,
    payment_date DATE,
    payment_method NVARCHAR(50),
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);
GO

-- Tạo bảng Reviews
CREATE TABLE Reviews (
    review_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    booking_id INT,
    content NVARCHAR(MAX),
    rating INT,
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);
GO

-- Tạo bảng ActivitySchedules
CREATE TABLE ActivitySchedules (
    schedule_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    tour_id INT,
    day_number INT,
    activity_name NVARCHAR(255),
    activity_date DATE,
    start_time TIME,
    end_time TIME,
    location NVARCHAR(255),
    description NVARCHAR(MAX),
    image_url NVARCHAR(MAX),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id)
);
GO
-- Tạo bảng TourLocation
CREATE TABLE TourLocation (
    tour_id INT,
    location_id INT,
    PRIMARY KEY (tour_id, location_id),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id),
    FOREIGN KEY (location_id) REFERENCES Locations(location_id)
);
GO

-- Tạo bảng HotelTour
CREATE TABLE HotelTour (
    hotel_id INT,
    tour_id INT,
    PRIMARY KEY (hotel_id, tour_id),
    FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id)
);
GO

-- Tạo bảng RestaurantTour
CREATE TABLE RestaurantTour (
    restaurant_id INT,
    tour_id INT,
    PRIMARY KEY (restaurant_id, tour_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id)
);
GO
-- Tạo bảng TourTransportation
CREATE TABLE TourTransportation (
    tour_id INT,
    transportation_id INT,
    PRIMARY KEY (tour_id, transportation_id),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id),
    FOREIGN KEY (transportation_id) REFERENCES Transportations(transportation_id)
);
GO

ALTER TABLE Tours
DROP COLUMN end_date;

ALTER TABLE Tours
DROP COLUMN start_date;

CREATE TABLE TourDates (
    tour_date_id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    tour_id INT,
    start_date DATE,
    end_date DATE,
    current_capacity INT, 
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id)
);

ALTER TABLE Tours
DROP COLUMN current_capacity;

ALTER TABLE Transportations
DROP COLUMN departure_date;

ALTER TABLE Transportations
DROP COLUMN return_date;

ALTER TABLE Transportations
ADD departure_time TIME;

ALTER TABLE Transportations
ADD return_time TIME;

ALTER TABLE Restaurants
DROP COLUMN reservation_date;

ALTER TABLE ActivitySchedules
DROP COLUMN activity_date;

ALTER TABLE Transportations
DROP COLUMN price;

ALTER TABLE Hotels
DROP COLUMN price;

ALTER TABLE Restaurants
DROP COLUMN price;
