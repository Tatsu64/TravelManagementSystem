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

import java.math.BigDecimal;
import java.util.Date;

public class Transportation {
    private int transportationId;
    private String transportationName;
    private Date departureDate;
    private Date returnDate;
    private BigDecimal price;
    private String imageUrl;

    public Transportation(int transportationId, String transportationName, Date departureDate, Date returnDate, BigDecimal price, String imageUrl) {
        this.transportationId = transportationId;
        this.transportationName = transportationName;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Transportation(String transportationName, Date departureDate, Date returnDate, BigDecimal price, String imageUrl) {
        this.transportationName = transportationName;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Transportation() {
    }

    
    // Getters and Setters

    public int getTransportationId() {
        return transportationId;
    }

    public void setTransportationId(int transportationId) {
        this.transportationId = transportationId;
    }

    public String getTransportationName() {
        return transportationName;
    }

    public void setTransportationName(String transportationName) {
        this.transportationName = transportationName;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
