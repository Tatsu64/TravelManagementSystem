/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

/**
 *
 * @author ADMIN
 */
public class Employee {
    private int employeeId;
    private String fullName;
    private String email;
    private String position;

    public Employee(int employeeId, String fullName, String email, String position) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.position = position;
    }

    public Employee(String fullName, String email, String position) {
        this.fullName = fullName;
        this.email = email;
        this.position = position;
    }

    public Employee() {
    }

    
    // getters and setters

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
}

