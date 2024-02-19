/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author ADMIN
 */
import java.math.BigDecimal;
import java.util.Date;


public class Booking {
    private int bookingId;
    private Tour tour;  // Foreign Key to Tour
    private User user;  // Foreign Key to User
    private Date bookingDate;
    private int numberOfPeople;
    private BigDecimal totalPrice;

    public Booking(int bookingId, Tour tour, User user, Date bookingDate, int numberOfPeople, BigDecimal totalPrice) {
        this.bookingId = bookingId;
        this.tour = tour;
        this.user = user;
        this.bookingDate = bookingDate;
        this.numberOfPeople = numberOfPeople;
        this.totalPrice = totalPrice;
    }

    public Booking() {
    }
    

    // Getters and Setters

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}


