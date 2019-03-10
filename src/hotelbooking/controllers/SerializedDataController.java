/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.BusinessCustomer;
import hotelbooking.models.Customer;
import hotelbooking.models.FamilyCustomer;
import hotelbooking.models.Reservation;
import hotelbooking.models.ReservationLinkedList;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author David Ortiz
 */
public class SerializedDataController {
    
    //fields
    
    private ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();
    private String reservationsFile = "Reservations.ser";
    
    private ReservationLinkedList reservationLinkedList = new ReservationLinkedList();

    private ArrayList<Customer> customersList = new ArrayList<Customer>();
    private String customersFile = "Customers.ser";
    
    //constructor
    public SerializedDataController() throws FileNotFoundException{
        this.readCustomerListFile();
        
        if(customersList.isEmpty() || customersList == null){
            this.customersList = fetchCustomersFromFile();
            
            this.writeCustomerListFile();
            this.readCustomerListFile();
        }
        
        
        this.readReservationListFile();
        
        if(reservationsList.isEmpty() || reservationsList == null){
            //this.reservationsList = fetchCustomersFromFile();
            
            this.writeReservationListFile();
            this.readReservationListFile();
        }
        
        
        
    }
    
    
    //Reservation
    public void readReservationListFile(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(reservationsFile);
            in = new ObjectInputStream(fis);
            reservationsList = (ArrayList)in.readObject();
            in.close();
        }
        catch(IOException ex){
            System.out.println(reservationsFile + " not found, creating.");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public void writeReservationListFile(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        
       
        try {
            fos = new FileOutputStream(reservationsFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(this.reservationsList);
            out.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    
    
    //Customer
    public void readCustomerListFile(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(customersFile);
            in = new ObjectInputStream(fis);
            customersList = (ArrayList)in.readObject();
            in.close();
        }
        catch(IOException ex){
            System.out.println(customersFile + " not found, creating.");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
    }
    
    public void writeCustomerListFile(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(customersFile);
            out = new ObjectOutputStream(fos);
            out.writeObject(customersList);
            out.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    //mutators
    public void setCustomerToCustomerList(Customer _customer){
        this.customersList.add(_customer);
    }
    
    public void setCustomersList(ArrayList customers){
        this.customersList = customers;
        writeCustomerListFile();
        
    }
    
    public void updateReservationsList(Reservation reservation){
        
        this.reservationLinkedList.addReservation(reservation);
        
        this.reservationsList.add(reservation);
        
        writeReservationListFile();
        
    }
    
    //called when deleting row
    public void setReservationsList(LinkedList<Reservation> list){
        
        reservationLinkedList.clearReservations();
        
        for(Reservation r: list){
            reservationLinkedList.addReservation(r);
        } 
        
        ArrayList<Reservation> myAL = new ArrayList<Reservation>(list);
        
        this.reservationsList = myAL;
        
        writeReservationListFile();
    }
    
    
    //accessors
    public ArrayList<Customer> getCustomersList(){
        return this.customersList;
    }
    
    public ArrayList getReservationsList(){
        return this.reservationsList;
    }
    
    public String[] getLastNames(){
        ArrayList<String> customerNames = new ArrayList<String>();
        for(Customer c : this.customersList){
            customerNames.add(c.getLastName());
        }
        String[] stockArr = new String[customerNames.size()];
        stockArr = customerNames.toArray(stockArr);
        return stockArr;
    }
    
    public String[] getFirstNames(){
        ArrayList<String> customerFirstNames = new ArrayList<String>();
        for(Customer c : this.customersList){
            customerFirstNames.add(c.getFirstName());
        }
        String[] stockArr = new String[customerFirstNames.size()];
        stockArr = customerFirstNames.toArray(stockArr);
        return stockArr;
    }
    
    public String[] getAmountOfTravelers(){
        ArrayList<String> temp = new ArrayList<String>();
        for(Customer c : this.customersList){
            temp.add(Integer.toString(c.getAmountOfTravelers()));
        }
        String[] stockArr = new String[temp.size()];
        stockArr = temp.toArray(stockArr);
        
        return stockArr;
    }
    
    
    
    
    //others
    private static ArrayList<Customer> fetchCustomersFromFile() throws FileNotFoundException{
        String fileName = "customers.txt";
        
        
        ArrayList<Customer> customers = new ArrayList<Customer>();
         
        String line = null;
         
        int index = 0;
        
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                if(line != ""){
                    String[] type = line.split("~");
                    
                    if(type[0].equals("business")){
                        
                        
                        String[] words = type[1].split(";");
                        String fname = words[0];
                        String lname = words[1];
                        String company = words[2];
                        String amt = words[3];
                        
                        BusinessCustomer newB = new BusinessCustomer(company, fname, lname, Integer.parseInt(amt));
                        customers.add(newB);
                        
                    } else if(type[0].equals("family")){
                        
                        String[] words = type[1].split(";");
                        String fname = words[0];
                        String lname = words[1];
                        String kids = words[2];
                        String adults = words[3];
                        
                        FamilyCustomer newF = new FamilyCustomer(fname, lname, Integer.parseInt(kids), Integer.parseInt(adults));
                        customers.add(newF);
                        
                    } else {
                        
                        System.out.println("Invalid customer");
                    }
                    
                    index++;
                } 
                
            }
            
            bufferedReader.close(); 
             
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");   
        }
        return customers;
         
    } // end fetchCustomersFromFile()
    
    
    
    
}
