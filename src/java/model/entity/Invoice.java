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

public class Invoice {
    private int invoiceId;
    private Booking booking;
    private Date paymentDate;
    private String paymentMethod;

    public Invoice(int invoiceId, Booking booking, Date paymentDate, String paymentMethod) {
        this.invoiceId = invoiceId;
        this.booking = booking;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public Invoice() {
    }

    
    // getters and setters

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

