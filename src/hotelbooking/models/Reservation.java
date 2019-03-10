/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David Ortiz
 */
public class Reservation implements Details, Serializable {
    
    //fields
    private Customer customer;
    private Room room;
    private int numOfNights;
    private double priceForAmenities;
    private double totalPrice;
    private double totalForRoom;
    private Hotel hotel;
    private Dates dates;
    private ArrayList<Amenity> preferences;
    
    //constructors
    public Reservation(){
        
    }
    
    //for testing only
    public Reservation(Customer _customer, Dates _dates){
        
        this.customer = _customer;
        this.dates = _dates;
    }
    
    public Reservation(Customer _customer, Hotel _hotel, Room _room, Dates _dates){
        this.customer = _customer;
        this.room = _room;
        this.dates = _dates;
        this.hotel = _hotel;
        
        Date first = _dates.getCheckOut();
        Date second = _dates.getCheckIn();
        this.numOfNights = (int)((first.getTime() - second.getTime())/ (1000 * 60 * 60 * 24));

        this.totalPrice = room.getPrice()*this.numOfNights;
        this.totalForRoom = this.totalPrice;
        
    }
    
    public Reservation(Customer _customer, Hotel _hotel, Room _room, Dates _dates, ArrayList<Amenity> _preferences){
        this.customer = _customer;
        this.room = _room;
        this.dates = _dates;
        this.hotel = _hotel;
        this.preferences = _preferences;
        
        double priceForAmens=0.0;
        for(Amenity eachAmen : preferences){
            priceForAmens += eachAmen.getPrice();
        }
        
        Date first = _dates.getCheckOut();
        Date second = _dates.getCheckIn();
        this.numOfNights = (int)((first.getTime() - second.getTime())/ (1000 * 60 * 60 * 24));

        this.totalPrice = priceForAmens + (room.getPrice()*this.numOfNights);
        this.totalForRoom = this.room.getPrice() * this.numOfNights;
        
    }
    
    //mutators
    public void setCustomer(Customer _customer){
        this.customer = _customer;
    }
    
    public void setRoom(Room _room){
        this.room = _room;
    }
    
    public void setNumOfNights(Dates _dates){
        Date first = _dates.getCheckOut();
        Date second = _dates.getCheckIn();
        this.numOfNights = (int)((first.getTime() - second.getTime())/ (1000 * 60 * 60 * 24));    
    }
    
    public void setNumOfNights(int _days){
        this.numOfNights = _days;
    }
    
    public void setTotalPrice(Room _room){
        this.totalPrice = this.priceForAmenities + (_room.getPrice()*this.numOfNights);
    }
    
    public void setTotalPrice(double _price){
        this.totalPrice = _price;
    }
    
    public void setTotalForRoom(Room _room){
        this.totalForRoom = _room.getPrice() * this.numOfNights;
    }
    
    public void setCheckInDate(Calendar _checkInDate){
        this.dates.setCheckIn(_checkInDate);
    }
    
    public void setCheckOutDate(Calendar _checkOutDate){
        this.dates.setCheckOut(_checkOutDate);
    }
    
    public void setPreferences(ArrayList<Amenity> _preferences){
        this.preferences = _preferences;
    }
    
    
    //accessors
    public Hotel getHotel(){
        return hotel;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public Room getRoom(){
        return room;
    }
    
    public int getNumOfNights(){
        return numOfNights;
    }
    
    public double getPriceOfAmenities(){
        priceForAmenities=0.0;
        
        for(Amenity eachAmenity : preferences){
            priceForAmenities += eachAmenity.getPrice();
        }
        
        return priceForAmenities;
    }
    
    public double getTotalForRoom(){
        return totalForRoom;
    }
    
    public double getTotalPrice(){
        return totalPrice;
    }
    
    public Date getCheckInDate(){
        return dates.getCheckIn();
    }
    
    public Date getCheckOutDate(){
        return dates.getCheckOut();
    }
    
    public ArrayList<Amenity> getPreferences(){
        return preferences;
    }
    
    //others
    public boolean insertionDecision(Reservation _reservation){
        
        boolean decision = false;
        Customer tempToCheck = this.customer;
        Format formatter = new SimpleDateFormat("MM/dd/yyy");
        
        Date current = this.dates.getCheckIn();
        Date compare = _reservation.getCheckInDate();
        
        if (current.compareTo(compare) > 0) {
            decision = true;
        } 
        
        return decision;
    }

    @Override
    public void printDetails() {
        System.out.println("Nights: " + this.getNumOfNights());
        //System.out.println("Amount due for room: " + this.getTotalForRoom());
        //System.out.println("Price for amenities: " + this.getPriceOfAmenities());
        System.out.println("Total Price: "+ this.getTotalPrice()); 
    }
    
    
    
}
