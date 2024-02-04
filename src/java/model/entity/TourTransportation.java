/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author ADMIN
 */
public class TourTransportation {
    private Tour tour;  // Composite Foreign Key to Tour
    private Transportation transportation;  // Composite Foreign Key to Transportation

    public TourTransportation(Tour tour, Transportation transportation) {
        this.tour = tour;
        this.transportation = transportation;
    }

    public TourTransportation() {
    }

    
    // Getters and Setters

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }
    
}
