/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author ADMIN
 */
public class HotelBooking {
    private Hotel hotel;  // Composite Foreign Key to Hotel
    private Booking booking;  // Composite Foreign Key to Booking

    public HotelBooking(Hotel hotel, Booking booking) {
        this.hotel = hotel;
        this.booking = booking;
    }

    public HotelBooking() {
    }

    
    // Getters and Setters

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
}

