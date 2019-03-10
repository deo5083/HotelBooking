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

/**
 *
 * @author David Ortiz
 */
public class RoomList {
    //fields
    private ArrayList<Room> roomArrayList = new ArrayList<Room>();
    
    //constructor
    public RoomList() throws FileNotFoundException{
        
        ArrayList<Room> roomsFromFile = fetchRoomsFromFile();

        this.roomArrayList = roomsFromFile;
        
    }
    
    //accessors
    public ArrayList<Room> getRoomArrayList(){
        return this.roomArrayList;
    }
    
    public int getSize(){
        return this.roomArrayList.size();
    }
    
    public String[] getNumBeds(){
        ArrayList<String> beds = new ArrayList<String>();
        for(Room room : this.roomArrayList){
            beds.add(Integer.toString(room.getBeds()));
        }
        String[] stockArr = new String[beds.size()];
        stockArr = beds.toArray(stockArr);
        return stockArr;
    }
    
    public String[] getPrices(){
        ArrayList<String> prices = new ArrayList<String>();
        for(Room room : this.roomArrayList){
            prices.add(Double.toString(room.getPrice()));
        }
        String[] stockArr = new String[prices.size()];
        stockArr = prices.toArray(stockArr);
        return stockArr;
    }
    
    //mutator
    public void setRoomArrayList(ArrayList<Room> _roomArrayList){
        this.roomArrayList = _roomArrayList;
    }
    
    //others
    private static ArrayList<Room> fetchRoomsFromFile() throws FileNotFoundException{
        String fileName = "rooms.txt";
        
        
        ArrayList<Room> rooms = new ArrayList<Room>();
         
        String line = null;
         
        int index = 0;
        
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                
                if(line != ""){
                    String[] words = line.split(";");
                    
                    int _roomId = Integer.parseInt(words[0]);
                    int _beds = Integer.parseInt(words[1]);
                    double _price = Double.parseDouble(words[2]);
                    boolean _available = Boolean.parseBoolean(words[3]);
                    
                    Room temp = new Room(_roomId, _beds, _price, _available);
                    
                    rooms.add(temp);
                    index++;
                } 
                
            }
            
            bufferedReader.close(); 
             
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");   
        }
        
        return rooms;
         
    } // end fetchRoomsFromFile()
    
    
    
    
    
}
