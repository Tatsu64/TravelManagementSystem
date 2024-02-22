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
    public String location;
    public int day;
    public int person;
    public double price;
    public String image;
    public String description;

    public HomeTour(int TourId, String location, int day, int person, double price, String image, String description) {
        this.TourId = TourId;
        this.location = location;
        this.day = day;
        this.person = person;
        this.price = price;
        this.image = image;
        this.description = description;
    }
    
    public HomeTour(){
        
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
    
    
    
    
}
