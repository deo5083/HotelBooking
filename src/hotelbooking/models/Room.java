/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David Ortiz
 */
public class Room implements Details, Serializable {
    
    //fields
    private int roomId;
    private int beds;
    private double price;
    private boolean available;
    
    //constructors
    public Room(){
        
    }
    
    public Room(int _roomId, int _beds, double _price, boolean _available){
        this.roomId = _roomId;
        this.beds = _beds;
        this.price = _price;
        this.available = _available;
    }
    
    //mutators
    public void setRoomId(int _roomId){
        this.roomId = _roomId;
    }
    
    public void setBeds(int _beds){
        this.beds = _beds;
    }

    public void setPrice(double _price){
        this.price = _price;
    } 
    
    public void setAvailable(boolean _available){
        this.available = _available;
    } 
    
    //accessors
    public int getRoomId(){
        return roomId;
    }
    
    public int getBeds(){
        return beds;
    }
    
    public boolean getAvailable(){
        return available;
    }

    public double getPrice() {
        return price;
    }

    //others
    @Override
    public void printDetails() {
        System.out.println("Room number: " + this.roomId);
        System.out.println("Number of beds: "+ this.beds);
        System.out.println("Price per night: " + this.getPrice());
    }
    
    
    
}
