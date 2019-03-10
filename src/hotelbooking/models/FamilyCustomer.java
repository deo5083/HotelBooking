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
public class FamilyCustomer extends Customer {

    //fields
    private int numOfKids;
    private int numOfAdults;
    
    //constructors
    public FamilyCustomer(String _firstName, String _lastName, int _numOfKids, int _numOfAdults) {
        super(_firstName, _lastName, (_numOfKids+_numOfAdults));
        this.numOfAdults = _numOfAdults;
        this.numOfKids = _numOfKids;
    }
    
    //mutators
    @Override
    public int getNumOfKids(){
        return this.numOfKids;
    }
    
    @Override
    public int getNumOfAdults(){
        return this.numOfAdults;
    }
    
    @Override
    public String getInformation(){
        return this.getFullName() + ", Adults: " + numOfAdults + ", Kids: " + numOfKids;
    }
    
    @Override
    public String getType(){
        return "family";
    }
    
    @Override
    public void setNumOfKids(int num){
        this.numOfKids = num;
    }
    @Override
    public void setNumOfAdults(int num){
        this.numOfAdults = num;

    }
    
    
  
    
}
