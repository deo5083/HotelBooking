/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

/**
 *
 * @author David Ortiz
 */
public class BusinessCustomer extends Customer {
    
    //field 
    private String business;
    
    //constructors
    public BusinessCustomer(String _business, String _firstName, String _lastName, int _amtOfTravelers) {
        super(_firstName, _lastName, _amtOfTravelers);
        this.business = _business;
    }
    
    //mutators
    public String getCompany(){
        return this.business;
    }
    
    @Override
    public String getType(){
        return "business";
    }
    
    @Override
    public String getInformation(){
        return this.getFullName()+ ", " + business + ", "+this.getAmountOfTravelers();
    }
    
    //accessors
    @Override
    public void setCompany(String _company){
        this.business = _company;
    }
    
    
    
    
    
}
