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
public class Amenity implements Serializable, Details, Comparable<Amenity> {
    
    //fields
    private String amenity;
    private double price;
    
    //constructors
    public Amenity(){
        
    }
    
    public Amenity(String _amenities, double _price){
        this.amenity = _amenities;
        this.price = _price;
    }
    
    //mutators
    public void setAmenity(String _amenity){
        this.amenity = _amenity;
    }
    
    public void setPrice(double _price){
        this.price = _price;
    }
    
    //accessors
    public String getAmenity(){
        return amenity;
    }
    
    public double getPrice(){
        return price;
    }

    @Override
    public void printDetails() {
        System.out.println(amenity + ": $" + price);
    }

    @Override
    public int compareTo(Amenity o) {
        
        if(o.getAmenity().compareTo(this.amenity)>0){
            return -1;
        } else if(o.getAmenity().compareTo(this.amenity)<0){
            return 1;
        } else {
            return 0;
        }
        
    }
    
    
}
