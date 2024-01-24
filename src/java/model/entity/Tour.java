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

public class Tour {
    private int tourId;
    private String tourName;
    private String description;
    private Date startDate;
    private Date endDate;
    private double tourPrice;
    private String imageUrl;
    private Employee employee;
    private String startLocation;

    public Tour(int tourId, String tourName, String description, Date startDate, Date endDate, double tourPrice, String imageUrl, Employee employee, String startLocation) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourPrice = tourPrice;
        this.imageUrl = imageUrl;
        this.employee = employee;
        this.startLocation = startLocation;
    }

    public Tour(String tourName, String description, Date startDate, Date endDate, double tourPrice, String imageUrl, Employee employee, String startLocation) {
        this.tourName = tourName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourPrice = tourPrice;
        this.imageUrl = imageUrl;
        this.employee = employee;
        this.startLocation = startLocation;
    }

    
    // getters and setters

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

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
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
    
}

