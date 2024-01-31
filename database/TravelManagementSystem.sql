-- Tạo cơ sở dữ liệu
CREATE DATABASE TravelManagementSystem;
GO

-- Sử dụng cơ sở dữ liệu mới tạo
USE TravelManagementSystem;
GO

-- Tạo bảng Employees
CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
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
    tour_id INT PRIMARY KEY,
    tour_name NVARCHAR(255),
    description NVARCHAR(MAX),
    start_date DATE,
    end_date DATE,
    tour_price DECIMAL(18, 2),
    image_url NVARCHAR(MAX),
    employee_id INT FOREIGN KEY REFERENCES Employees(employee_id),
    start_location NVARCHAR(255),
    max_capacity INT,
    current_capacity INT
);
GO

-- Tạo bảng Locations
CREATE TABLE Locations (
    location_id INT PRIMARY KEY,
    location_name NVARCHAR(255),
    tour_id INT FOREIGN KEY REFERENCES Tours(tour_id)
);
GO

-- Tạo bảng Hotels
CREATE TABLE Hotels (
    hotel_id INT PRIMARY KEY,
    hotel_name NVARCHAR(255),
    location_id INT FOREIGN KEY REFERENCES Locations(location_id),
    price DECIMAL(18, 2),
    image_url NVARCHAR(MAX),
    address NVARCHAR(MAX)
);
GO

-- Tạo bảng Transportations
CREATE TABLE Transportations (
    transportation_id INT PRIMARY KEY,
    transportation_name NVARCHAR(255),
    departure_date DATE,
    return_date DATE,
    price DECIMAL(18, 2),
    image_url NVARCHAR(MAX)
);
GO

-- Tạo bảng Restaurants
CREATE TABLE Restaurants (
    restaurant_id INT PRIMARY KEY,
    location_id INT FOREIGN KEY REFERENCES Locations(location_id),
    reservation_date DATE,
    price DECIMAL(18, 2),
    image_url NVARCHAR(MAX),
    address NVARCHAR(MAX)
);
GO

-- Tạo bảng Bookings
CREATE TABLE Bookings (
    booking_id INT PRIMARY KEY,
    tour_id INT FOREIGN KEY REFERENCES Tours(tour_id),
    user_id INT FOREIGN KEY REFERENCES Users(user_id),
    booking_date DATE,
    number_of_people INT,
    total_price DECIMAL(18, 2)
);
GO

-- Tạo bảng Bills (Loại bỏ cột booking_id và thêm cột mới)
CREATE TABLE Bills (
    bill_id INT PRIMARY KEY,
    booking_id INT UNIQUE FOREIGN KEY REFERENCES Bookings(booking_id),
    payment_date DATE,
    payment_method NVARCHAR(50)
);
GO

-- Tạo bảng Reviews
CREATE TABLE Reviews (
    review_id INT PRIMARY KEY,
    booking_id INT FOREIGN KEY REFERENCES Bookings(booking_id),
    content NVARCHAR(MAX),
    rating INT
);
GO

-- Tạo bảng ActivitySchedules
CREATE TABLE ActivitySchedules (
    schedule_id INT PRIMARY KEY,
    tour_id INT FOREIGN KEY REFERENCES Tours(tour_id),
    day_number INT,
    activity_name NVARCHAR(255),
    activity_date DATE,
    start_time TIME,
    end_time TIME,
    location NVARCHAR(255),
    description NVARCHAR(MAX),
    image_url NVARCHAR(MAX),
    CONSTRAINT FK_ActivitySchedules_Tours FOREIGN KEY (tour_id) REFERENCES Tours(tour_id)
);
GO

-- Tạo bảng HotelBookings (Mối quan hệ nhiều-nhiều giữa Hotels và Bookings)
CREATE TABLE HotelBookings (
    hotel_id INT,
    booking_id INT,
    PRIMARY KEY (hotel_id, booking_id),
    FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id),
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);
GO

-- Tạo bảng RestaurantBookings (Mối quan hệ nhiều-nhiều giữa Restaurants và Bookings)
CREATE TABLE RestaurantBookings (
    restaurant_id INT,
    booking_id INT,
    PRIMARY KEY (restaurant_id, booking_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id),
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);
GO

-- Tạo bảng TourTransportations (Mối quan hệ nhiều-nhiều giữa Tours và Transportations)
CREATE TABLE TourTransportations (
    tour_id INT,
    transportation_id INT,
    PRIMARY KEY (tour_id, transportation_id),
    FOREIGN KEY (tour_id) REFERENCES Tours(tour_id),
    FOREIGN KEY (transportation_id) REFERENCES Transportations(transportation_id)
);
GO
