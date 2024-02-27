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

public class TourDates {
    private int tourDateId;
    private int tourId;
    private Date startDate;
    private Date endDate;
    private int currentCapacity;

    public TourDates(int tourDateId, int tourId, Date startDate, Date endDate, int currentCapacity) {
        this.tourDateId = tourDateId;
        this.tourId = tourId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentCapacity = currentCapacity;
    }

    public TourDates(int tourId, Date startDate, Date endDate, int currentCapacity) {
        this.tourId = tourId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentCapacity = currentCapacity;
    }



    public TourDates() {
    }
    

    // Getters and setters
    public int getTourDateId() {
        return tourDateId;
    }

    public void setTourDateId(int tourDateId) {
        this.tourDateId = tourDateId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
}

