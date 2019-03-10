/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.src;

import hotelbooking.controllers.HotelTableController;
import hotelbooking.controllers.LoginController;
import hotelbooking.models.Customer;
import hotelbooking.models.Dates;
import hotelbooking.models.Reservation;
import hotelbooking.models.ReservationLinkedList;
import hotelbooking.tests.TestHarness;
import java.io.*;
import java.util.Calendar;


/**
 *
 * @author David Ortiz
 */
public class HotelBooking  {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    public static void main(String[] args) throws FileNotFoundException{
        
        //HotelTableController tableController = new HotelTableController(); //Initiate GUI
        LoginController loginController = new LoginController();
        
        //Test model classes
        //TestHarness test = new TestHarness(); //test classes
        
        //test linkedlist
        //HotelBooking.testLinkedList();
               
    } //end main()
    
    
    
    private static void testLinkedList(){
       // ************************************* start of linked list test *************************************
        //System.out.println("Original linked list:");

        //Instantate linkedlist
        ReservationLinkedList linkedList = new ReservationLinkedList();
        
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(2018, 6-1, 1);
        Calendar secondDate = Calendar.getInstance();
        secondDate.set(2018, 8-1, 3);
        Dates testDates = new Dates(firstDate, secondDate);
        
        //test addTestReservation
        Customer testCustomer = new Customer("inserted", "testcustomer");
        linkedList.addTestReservation(new Reservation(testCustomer, testDates));
        
        //test getTestReservation
        Reservation fetchedReservation = linkedList.getTestReservation("inserted testcustomer");
        
        //test removeTestReservation
        linkedList.removeTestReservation(fetchedReservation);
        
        // ************************************* end of linked list test *************************************
        
    }
    
} // end class
