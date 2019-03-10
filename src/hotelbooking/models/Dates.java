/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David Ortiz
 */
public class Dates implements Details, Serializable {
    
    //fields
    private Calendar checkIn;
    private Calendar checkOut;
    
    //constructor
    public Dates(Calendar _checkIn, Calendar _checkOut){
        this.checkIn = _checkIn;
        this.checkOut = _checkOut;
    }
    
    //mutators
    public void setCheckIn(Calendar _checkIn){
        this.checkIn = _checkIn;
    } 
    
    public void setCheckOut(Calendar _checkOut){
        this.checkOut = _checkOut;
    }
    
    
    //accessors
    public Date getCheckIn(){
        return checkIn.getTime();
    }
    
    public Date getCheckOut(){
        return checkOut.getTime();
    }

    @Override
    public void printDetails() {
        System.out.println("Check-in: " + checkIn.getTime() + " \nCheck-out: " + checkOut.getTime());
    }
    
  
}
