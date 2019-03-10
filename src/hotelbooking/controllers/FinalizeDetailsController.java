/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.Amenity;
import hotelbooking.models.Customer;
import hotelbooking.models.Dates;
import hotelbooking.models.Hotel;
import hotelbooking.models.Reservation;
import hotelbooking.models.Room;
import hotelbooking.models.RoomList;
import hotelbooking.views.FinalizeDetailsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David Ortiz
 */
public class FinalizeDetailsController {
    
    //fields
    private HotelTableController tableController;
    private SerializedDataController serializedData;
    
    private FinalizeDetailsView finalizeView;
    
    private Hotel selectedHotel;
    private Customer selectedCustomer;
    
    private Room assignedRoom;
    private RoomList roomList;
    private ArrayList<Room> roomArrayList = new ArrayList<Room>();
    
    private Double perNight;
    private String enteredNights;
    private Double totalPrice;
    
    private Date startDate;
    
    private ArrayList<Reservation> reservationArrayList;
    private ArrayList<Amenity> selectedAmenitiesList = new ArrayList<Amenity>();
    
    private Reservation currentReservation;
    
    //constructors
    public FinalizeDetailsController(FinalizeDetailsView finalizeView, SerializedDataController _serializedData, HotelTableController _parentController, Hotel hotel, Customer customer) throws FileNotFoundException{
        this.finalizeView = finalizeView;
        this.serializedData = _serializedData;
        this.tableController = _parentController;
        
        this.finalizeView.addContinueButtonListener(new ContinueButtonListener());
        this.finalizeView.addTodayButtonListener(new TodayButtonListener());
        this.finalizeView.addAmenityButton(new AmenityButtonListener());
        this.finalizeView.addNumNightKeyPressed(keyListener);
        
        this.selectedCustomer = customer;
        this.selectedHotel = hotel;
        
        this.finalizeView.setHotelLabels(this.selectedHotel.getName(), this.selectedHotel.getFullAddress());
        this.finalizeView.setCustomerLabels(this.selectedCustomer.getFullName(), this.selectedCustomer.getAmountOfTravelers());

        this.roomList = new RoomList();
        this.roomArrayList = this.roomList.getRoomArrayList();
        
        int numTravelers = this.selectedCustomer.getAmountOfTravelers();
        finalizeView.setAmenityPrice(0.0);
        
        boolean matched = false;
        
        for(Room r : this.roomArrayList){
            if(r.getBeds() >= numTravelers && r.getAvailable()){
                
                if(!matched){
                    this.assignedRoom = r;
                }
                
                matched = true;
            }
        }
        
        
        this.finalizeView.setRoomLabels(assignedRoom.getRoomId(), assignedRoom.getBeds());
        
        this.reservationArrayList = FinalizeDetailsController.this.tableController.getSerializedDataCollectionController().getReservationsList();

    }
    
    //accessors
    public ArrayList<Amenity> getSelectedAmenitiesList(){
        return this.selectedAmenitiesList;
    }
    
    //mutators
    public void setSelectedAmenitiesList(ArrayList<Amenity> amenities){
        this.selectedAmenitiesList = amenities;
    }
    
    public void setFinalizeView(boolean state){
        this.finalizeView.setVisible(state);
    }
   
    //others
    public boolean checkDates(){
        String enteredNights = this.finalizeView.getNumNights();
        String enteredStart = this.finalizeView.getStartDate();
        
        
        try {
            int nightsToInt = Integer.parseInt(enteredNights);

        } catch (NumberFormatException e) { 
            System.out.println("Invalid number of nights");
            return false;
        }
        
        if(!isThisDateValid(enteredStart)) return false;
        
        return true;
        
    }
    
    public boolean isThisDateValid(String dateToValidate){
        String dateFromat = "MM/dd/yyyy";
        
        if(dateToValidate == null){
                return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {
                Date date = sdf.parse(dateToValidate);
                
                this.startDate = date;

        } catch (ParseException e) {
                System.out.println("Invalid date format");
                return false;
        }

        return true;
    }
    
    private Calendar dateToCalendar(Date date) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;

    }
    
    public Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    
    public void update(){
        double roomPrice = 0.0;
        double amenTotal = 0.0;
            
        if(checkDates()){
            FinalizeDetailsController.this.perNight = FinalizeDetailsController.this.assignedRoom.getPrice();
            FinalizeDetailsController.this.enteredNights = FinalizeDetailsController.this.finalizeView.getNumNights();
            
            roomPrice = perNight * Double.parseDouble(enteredNights);
            
        } else {
            finalizeView.setRoomPrice(0.0);
            finalizeView.setTotalPriceLabel(0.0);
        }
        
        if(!FinalizeDetailsController.this.selectedAmenitiesList.isEmpty()){
               
                for(Amenity a: selectedAmenitiesList){
                    amenTotal += a.getPrice();
                }
        }
        
        finalizeView.setRoomPrice(roomPrice);
        totalPrice = roomPrice + amenTotal;
        
        finalizeView.setTotalPriceLabel(FinalizeDetailsController.this.totalPrice);

        finalizeView.setAmenityPrice(amenTotal);

    }
    
    
    //listeners
    private class AmenityButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AmenitiesController controller = new AmenitiesController(FinalizeDetailsController.this, tableController);
            finalizeView.setVisible(false);
            
        }
    }
    
    private class TodayButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Date todaysDate = new Date(); 
            finalizeView.setStartDate(todaysDate);             
        }
        
    }
    
    private KeyListener keyListener = new KeyListener() {
        public void keyPressed(KeyEvent keyEvent) {
        }
        public void keyTyped(KeyEvent keyEvent) {   
        }
        public void keyReleased(KeyEvent e) {
            update();
        }
        
    }; //end keyListener
    
    private class ContinueButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {           
            
            if(checkDates()){
                
                update();
                
                Date endDate = FinalizeDetailsController.this.addDays(startDate, Integer.parseInt(FinalizeDetailsController.this.enteredNights));
                Dates dates = new Dates(dateToCalendar(FinalizeDetailsController.this.startDate), dateToCalendar(endDate));
                
                if(FinalizeDetailsController.this.selectedAmenitiesList.isEmpty()){
                    FinalizeDetailsController.this.currentReservation = new Reservation(FinalizeDetailsController.this.selectedCustomer, FinalizeDetailsController.this.selectedHotel, FinalizeDetailsController.this.assignedRoom, dates);
                } else {
                    FinalizeDetailsController.this.currentReservation = new Reservation(FinalizeDetailsController.this.selectedCustomer, FinalizeDetailsController.this.selectedHotel, FinalizeDetailsController.this.assignedRoom, dates, selectedAmenitiesList);
                }
                
                FinalizeDetailsController.this.tableController.getSerializedDataCollectionController().updateReservationsList(FinalizeDetailsController.this.currentReservation);
                
                FinalizeDetailsController.this.tableController.setParentViewVisible();
                FinalizeDetailsController.this.finalizeView.dispose();
                
            } 
        
            
        } 
    } // end ContinueButtonListener
    
    private class UpdateButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {           
            
            update();
            
        } 
    } // end UpdateButtonListener
    
}
