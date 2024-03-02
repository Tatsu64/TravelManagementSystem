/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author ADMIN
 */


public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private Location location;  // Foreign Key to Location 
    private String imageUrl;
    private String address;

    public Restaurant(int restaurantId, String restaurantName, Location location, String imageUrl, String address) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.location = location;
        this.imageUrl = imageUrl;
        this.address = address;
    }

    public Restaurant(String restaurantName, Location location, String imageUrl, String address) {
        this.restaurantName = restaurantName;
        this.location = location;
        this.imageUrl = imageUrl;
        this.address = address;
    }

    public Restaurant() {
    }

    
    // Getters and Setters

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}


