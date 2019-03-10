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
public class Customer implements Details, Serializable {
    //fields
    private String firstName;
    private String lastName;
    private int amountOfTravelers;
    
    //constructors
    public Customer(){
        
    }
    
    public Customer(String _firstName, String _lastName){
        this.firstName = _firstName;
        this.lastName = _lastName;
    }
    
    public Customer(String _firstName, String _lastName, int _amountOfTravelers){
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.amountOfTravelers = _amountOfTravelers;
    }
    
    //mutators
    public void setFirstName(String _firstName){
        this.firstName = _firstName;
    }
    
    public void setLastName(String _lastName){
        this.lastName = _lastName;
    }
    
    public void setAmountOfTravelers(int _amountOfTravelers){
        this.amountOfTravelers = _amountOfTravelers;
    }
    
    //accessors
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getFullName(){
        return firstName + " " + lastName;
    }
    
    public int getAmountOfTravelers(){
        return amountOfTravelers;
    }
    
    public String getInformation(){
        return this.getFullName()+" "+amountOfTravelers;
    }

    @Override
    public void printDetails() {
        System.out.println("Name: " + this.getFullName() + " \nNumber of guests: " + amountOfTravelers);
    }
    
    public String getType(){
        return "generic";
    }
    
    public int getNumOfKids(){
        return 0;
    }
    public int getNumOfAdults(){
        return 0;
    }
    
    public void setNumOfKids(int num){
        
    }
    public void setNumOfAdults(int num){
        
    }
    
    public String getCompany(){
        return "";
    }
    
    public void setCompany(String _company){
        
    }
     
}
