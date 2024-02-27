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
import java.sql.Time;

public class ActivitySchedule {
    private int scheduleId;
    private Tour tour;  // Foreign Key to Tour
    private int dayNumber;
    private String activityName;
    private Time startTime;
    private Time endTime;
    private String location;
    private String description;
    private String imageUrl;

    public ActivitySchedule(int scheduleId, Tour tour, int dayNumber, String activityName, Time startTime, Time endTime, String location, String description, String imageUrl) {
        this.scheduleId = scheduleId;
        this.tour = tour;
        this.dayNumber = dayNumber;
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public ActivitySchedule(Tour tour, int dayNumber, String activityName, Time startTime, Time endTime, String location, String description, String imageUrl) {
        this.tour = tour;
        this.dayNumber = dayNumber;
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public ActivitySchedule() {
    }

    
    // Getters and Setters

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
}
