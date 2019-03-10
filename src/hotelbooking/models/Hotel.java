/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import java.io.Serializable;

/**
 *
 * @author David Ortiz
 */
public class Hotel implements Details, Serializable {
    
    //fields
    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private int zip;
    private double rating;
    private Amenity[] amenities;
    
    //constructors
    public Hotel(){
        
    }
    
    public Hotel(String _name, String _streetAddress, String _city, String _state, int _zip, double _rating){
        this.name = _name;
        this.streetAddress = _streetAddress;
        this.city = _city;
        this.state = _state;
        this.zip = _zip;
        this.rating = _rating;
    }
    
    public Hotel(String _name, String _streetAddress, String _city, String _state, int _zip, double _rating,Amenity[] _amenities){
        this.name = _name;
        this.streetAddress = _streetAddress;
        this.city = _city;
        this.state = _state;
        this.zip = _zip;
        this.rating = _rating;
        this.amenities = _amenities;
    }
    
    //mutators
    public void setName(String _name){
        this.name = _name;
    }
    
    public void setStreetAddress(String _streetAddress){
        this.streetAddress = _streetAddress;
    }
    
    public void setCity(String _city){
        this.city = _city;
    }
    
    public void setState(String _state){
        this.state = _state;
    }
    
    public void setZip(int _zip){
        this.zip = _zip;
    }
    
    public void setRating(double _rating){
        this.rating = _rating;
    }
    
    public void setAmenities(Amenity[] _amenities){
        this.amenities = _amenities;
    }
    
    
    //accessors
    public String getName(){
        return name;
    }
    
    public String getStreetAddress(){
        return streetAddress;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getState(){
        return state;
    }
    
    public int getZip(){
        return zip;
    }
    
    public double getRating(){
        return rating;
    }
    
    public String getFullAddress(){
        return streetAddress + ", " + city + ", " + state + ", " + zip;
    }
    
    public Amenity[] getAmenities(){
        return amenities;
    }
    
    public String getDetails(){
        return name + " " + this.getFullAddress();
    }

    @Override
    public void printDetails() {
        System.out.println("Hotel: "+ name);
        System.out.println("Address: " + this.getFullAddress());
    }
    
    
}
