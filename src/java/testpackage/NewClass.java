/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testpackage;

import model.dao.TourDAO;
import model.database.DatabaseConnector;
import model.entity.TourTransportation;

/**
 *
 * @author ADMIN
 */
public class NewClass {
    public static void main(String[] args) {
        TourDAO tourDAO = new TourDAO(DatabaseConnector.getConnection());
            // Insert into TourTransportations
             TourTransportation tourTransportation = new TourTransportation(16, 1);
               tourDAO.addTourTransportation(tourTransportation);
          
                
    }
}
