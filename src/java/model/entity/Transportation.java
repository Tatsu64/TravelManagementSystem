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
import java.sql.Time;
import java.util.Date;

public class Transportation {
    private int transportationId;
    private String transportationName;
    private Time departureTime;
    private Time returnTime;
    private String imageUrl;

    public Transportation(int transportationId, String transportationName, Time depatureTime, Time returnTime, String imageUrl) {
        this.transportationId = transportationId;
        this.transportationName = transportationName;
        this.departureTime = depatureTime;
        this.returnTime = returnTime;
        this.imageUrl = imageUrl;
    }

   

    public Transportation() {
    }

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

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time depatureTime) {
        this.departureTime = depatureTime;
    }

    public Time getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Time returnTime) {
        this.returnTime = returnTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    
  
    
}
