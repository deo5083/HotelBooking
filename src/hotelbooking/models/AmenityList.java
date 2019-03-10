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
import java.util.TreeSet;

/**
 *
 * @author David Ortiz
 */
public class AmenityList {
    
    //fields
    private TreeSet<Amenity> amenityTreeSet;
    
    //constructor
    public AmenityList() throws FileNotFoundException{
        
        this.amenityTreeSet = fetchAmenitiesFromFile();
        
    }
    
    //accessors
    public TreeSet<Amenity> getAmenityTreeSet(){
        return this.amenityTreeSet;
    }
    
    //other
    private static TreeSet<Amenity> fetchAmenitiesFromFile() throws FileNotFoundException{
        String fileName = "amenities.txt";
        
        TreeSet<Amenity> amenities = new TreeSet<Amenity>();
         
        String line = null;
         
        int index = 0;
        
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                if(line != ""){
                    String[] words = line.split(";");
                    
                    String _name = words[0];
                    double _price = Double.parseDouble(words[1]);
                    
                    Amenity temp = new Amenity(_name, _price);
                    
                    amenities.add(temp);
                    index++;
                } 
                
            }
            
            bufferedReader.close(); 
             
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");   
        }
        return amenities;
         
    } // end fetchAmenitiesFromFile()
    
    
}
