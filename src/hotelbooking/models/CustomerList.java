/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import hotelbooking.controllers.SerializedDataController;
import java.io.*;
import java.util.ArrayList;


/**
 *
 * @author David Ortiz
 */
public class CustomerList {
    
    //fields
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    private SerializedDataController serializedData;
    
    //constructor
    public CustomerList() throws FileNotFoundException{
        
        this.serializedData = new SerializedDataController();
        this.customerArrayList = serializedData.getCustomersList();
        
    }
    
    //accessors
    public ArrayList<Customer> getCustomerArrayList(){
        return this.customerArrayList;
    }
    
    public int getSize(){
        return this.customerArrayList.size();
    }
    
    public String[] getLastNames(){
        ArrayList<String> customerNames = new ArrayList<String>();
        for(Customer c : this.customerArrayList){
            customerNames.add(c.getLastName());
        }
        String[] stockArr = new String[customerNames.size()];
        stockArr = customerNames.toArray(stockArr);
        return stockArr;
    }
    
    public String[] getFirstNames(){
        ArrayList<String> customerFirstNames = new ArrayList<String>();
        for(Customer c : this.customerArrayList){
            customerFirstNames.add(c.getFirstName());
        }
        String[] stockArr = new String[customerFirstNames.size()];
        stockArr = customerFirstNames.toArray(stockArr);
        return stockArr;
    }
    
    public String[] getAmountOfTravelers(){
        ArrayList<String> temp = new ArrayList<String>();
        for(Customer c : this.customerArrayList){
            temp.add(Integer.toString(c.getAmountOfTravelers()));
        }
        String[] stockArr = new String[temp.size()];
        stockArr = temp.toArray(stockArr);
        
        return stockArr;
    }
    
    //mutator
    public void setCustomerArrayList(ArrayList<Customer> _customerArrayList){
        this.customerArrayList = _customerArrayList;
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
