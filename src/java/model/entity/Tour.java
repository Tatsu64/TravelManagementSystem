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

public class Tour {
    private int tourId;
    private String tourName;
    private String description;
    private Date startDate;
    private Date endDate;
    private BigDecimal tourPrice;
    private String imageUrl;
    private Employee employee;  // Foreign Key to Employee
    private String startLocation;
    private int maxCapacity;
    private int currentCapacity;

    public Tour(int tourId, String tourName, String description, Date startDate, Date endDate, BigDecimal tourPrice, String imageUrl, Employee employee, String startLocation, int maxCapacity, int currentCapacity) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourPrice = tourPrice;
        this.imageUrl = imageUrl;
        this.employee = employee;
        this.startLocation = startLocation;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public Tour(String tourName, String description, Date startDate, Date endDate, BigDecimal tourPrice, String imageUrl, Employee employee, String startLocation, int maxCapacity, int currentCapacity) {
        this.tourName = tourName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourPrice = tourPrice;
        this.imageUrl = imageUrl;
        this.employee = employee;
        this.startLocation = startLocation;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public Tour() {
    }

    
    // Getters and Setters

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(BigDecimal tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    
}
