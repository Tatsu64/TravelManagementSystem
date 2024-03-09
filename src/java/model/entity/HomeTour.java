/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author toden
 */
public class HomeTour {
    public int TourId;
    public String tourName;
    public String location;
    private String secondlocation;
    private String dateStart;
    private String dateEnd;
    private int current;

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
    public int day;
    public int person;
    public double price;
    public String image;
    public String description;

    public String getSecondlocation() {
        return secondlocation;
    }

    public void setSecondlocation(String secondlocation) {
        this.secondlocation = secondlocation;
    }
    public HomeTour(int TourId, String location, int day, int person, double price, String image, String description) {
        this.TourId = TourId;
        this.location = location;
        this.day = day;
        this.person = person;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public HomeTour(int TourId, String location, String secondlocation, int day, int person, double price, String image, String description) {
        this.TourId = TourId;
        this.location = location;
        this.secondlocation = secondlocation;
        this.day = day;
        this.person = person;
        this.price = price;
        this.image = image;
        this.description = description;
    }
     public HomeTour(int TourId, String location, String secondlocation, int day, int person, int current, double price, String image, String description) {
        this.TourId = TourId;
        this.location = location;
        this.secondlocation = secondlocation;
        this.day = day;
        this.person = person;
        this.current = current;
        this.price = price;
        this.image = image;
        this.description = description;
    }
    
    public HomeTour(){
        
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getTourId() {
        return TourId;
    }

    public void setTourId(int TourId) {
        this.TourId = TourId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    
    
    
    
}
