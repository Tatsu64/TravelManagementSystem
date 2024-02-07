/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author ADMIN
 */
import java.util.Date;

public class Bill {
    private int billId;
    private Booking booking;  // Foreign Key to Booking
    private Date paymentDate;
    private String paymentMethod;

    public Bill(int billId, Booking booking, Date paymentDate, String paymentMethod) {
        this.billId = billId;
        this.booking = booking;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public Bill() {
    }

    
    // Getters and Setters

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
}

