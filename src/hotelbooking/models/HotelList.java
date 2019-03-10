/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author David Ortiz
 */
public class HotelList {
    
    //field names
    private HashSet<Hotel> hotelHashSet = new HashSet<Hotel>();
    
    //constructor
    public HotelList() throws FileNotFoundException{
        
        fetchHotelsFromFile();
    }
    
    //accessors
    public HashSet<Hotel> getHotelHashSet(){
        return this.hotelHashSet;
    }
    
    public int getSize(){
        return this.hotelHashSet.size();
    }
    
    public String[] getHotelNames(){
        ArrayList<String> hotelNames = new ArrayList<String>();
        for(Hotel hotel : this.hotelHashSet){
            hotelNames.add(hotel.getName());
        }
        String[] stockArr = new String[hotelNames.size()];
        stockArr = hotelNames.toArray(stockArr);
        return stockArr;
    }
    
    public String[] getCities(){
        ArrayList<String> temp = new ArrayList<String>();
        for(Hotel hotel : this.hotelHashSet){
            temp.add(hotel.getCity());
        }
        String[] stockArr = new String[temp.size()];
        stockArr = temp.toArray(stockArr);
        return stockArr;
    }
    
    public String[] getRatingsAsString(){
        ArrayList<String> temp = new ArrayList<String>();
        for(Hotel hotel : this.hotelHashSet){
            temp.add(Double.toString(hotel.getRating()));
        }
        String[] stockArr = new String[temp.size()];
        stockArr = temp.toArray(stockArr);
        
        return stockArr;
    }
    
    
    //mutators
    public void setHotelHashSet(HashSet<Hotel> _hotelHashSet){
        this.hotelHashSet = _hotelHashSet;
    }
       
    //others
    private void fetchHotelsFromFile() throws FileNotFoundException{
        
        String fileName = "hotels.txt";
        
        String line = null;
         
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                if(line != ""){
                    String[] words = line.split(";");
                    
                    int _zip = Integer.parseInt(words[4]);
                    double _rating = Double.parseDouble(words[5]);
                    
                    Hotel temp = new Hotel(words[0], words[1], words[2], words[3], _zip, _rating);
                    
                    hotelHashSet.add(temp);
                } 
                
            }
            
            bufferedReader.close(); 
             
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");   
        }
         
    } // end fetchHotelsFromFile()
    
} //end class

    
    

