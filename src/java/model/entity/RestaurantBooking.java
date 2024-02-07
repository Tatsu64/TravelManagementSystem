/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author ADMIN
 */
public class RestaurantBooking {
    private Restaurant restaurant;  // Composite Foreign Key to Restaurant
    private Booking booking;  // Composite Foreign Key to Booking

    public RestaurantBooking(Restaurant restaurant, Booking booking) {
        this.restaurant = restaurant;
        this.booking = booking;
    }

    public RestaurantBooking() {
    }

    
    // Getters and Setters

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
}

