/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.AmenityList;
import hotelbooking.models.Amenity;
import hotelbooking.models.Customer;
import hotelbooking.models.Hotel;
import hotelbooking.models.HotelList;
import hotelbooking.models.HotelTableModel;
import hotelbooking.models.Reservation;
import hotelbooking.views.CustomerTableView;
import hotelbooking.views.FinalizeDetailsView;
import hotelbooking.views.HotelDetailsView;
import hotelbooking.views.HotelTableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ortiz
 */
public class HotelTableController{
    
    //fields
    private HotelTableModel hotelTableModel;
    private HotelList hotelList;
    private HotelTableView tableView;
    private HotelDetailsView detailView;
    private HashSet<Hotel> hotelArrayList;
    
    private AmenityList amenityList = new AmenityList();
    private TreeSet<Amenity> amenityTreeSet;
    
    private SearchController searchController;
    
    private FinalizeDetailsController finalizeDetails;
    private FinalizeDetailsView finalizeView;
    
    private ReservationsController reservationController;
    
    private String[] hotels;
    private String[] cities;
    private String[] ratingsString;
    
    private CustomerTableView customerDetailView;
    private ArrayList<Customer> customerArrayList;

    private String[] fnames;
    private String[] lnames;
    private String[] amounts;
    
    private int selectedRow;
    
    private Customer currentCustomer;
    private Hotel currentHotel;
    
    private SerializedDataController serializedData;
    
    private LoginController loginController;

    //constructor
    public HotelTableController(SerializedDataController _serializedData, LoginController _login) throws FileNotFoundException{
        this.serializedData = _serializedData;
        this.loginController = _login;
        
        this.tableView = new HotelTableView();
        this.hotelList = new HotelList();
        
        this.hotelArrayList = hotelList.getHotelHashSet();

        this.hotelTableModel = new HotelTableModel(hotelArrayList);
        this.tableView.setVisible(true);
        
        this.amenityList = new AmenityList();
        this.amenityTreeSet = this.amenityList.getAmenityTreeSet();
       
        this.customerArrayList = serializedData.getCustomersList();

        hotels = hotelList.getHotelNames();
        cities = hotelList.getCities();
        ratingsString = hotelList.getRatingsAsString();
        
        tableView.createTable(hotels, cities, ratingsString);
        
        this.tableView.setHotelTableController(this);
        
        this.tableView.addChangeUserButtonListener(new ChangeUserButtonListener());
        this.tableView.addContinueButtonListener(new ContinueButtonListener());
        this.tableView.addReservationButtonListener(new ReservationsButtonListener());
        this.tableView.addSearchButtonListener(new SearchButtonListener());
        
        this.detailView = new HotelDetailsView(this);
        
        this.tableView.setUserName(customerArrayList.get(0).getFirstName());
                 
    }
    
    //accessors
    public HotelTableModel getHotelTableModel(){
        return this.hotelTableModel;
    }
    
    public HashSet<Hotel> getHotelArrayList(){
        return this.hotelArrayList;
    }
    
    public String[] getSelectedHotel(int _selectedRow){
        String[] temp = new String[3];
        
        hotels = hotelList.getHotelNames();
        cities = hotelList.getCities();
        ratingsString = hotelList.getRatingsAsString();
        
        temp[0] = hotels[_selectedRow];
        temp[1] = cities[_selectedRow];
        temp[2] = ratingsString[_selectedRow];
        return temp;
        
    }
    
    public TreeSet<Amenity> getAmenityTreeSet(){
        return this.amenityTreeSet;
    }
    
    public int getSelectedRow(){
        return this.selectedRow;
    }
    
    public SerializedDataController getSerializedDataCollectionController(){
        return this.serializedData;
    }
    
    public void getHotelDetailView(int _selectedRow){
        this.detailView = new HotelDetailsView(this, _selectedRow);
        
        this.tableView.setVisible(false);
        this.detailView.setVisible(true);
    }
    
    public Customer getCurrentCustomer(){
        return this.currentCustomer;
    }
    
    public Hotel getCurrentHotel(){
        return this.currentHotel;
    }
    
    public ArrayList<Reservation> getReservationArrayList(){
        return this.serializedData.getReservationsList();
    }
    
    public String getTableViewUserName(){
        return tableView.getUserName();
    }
    
    //mutators
    public void setCurrentCustomer(String _current){
        for(Customer c: customerArrayList){
            if(_current.equals(c.getFirstName())){
                this.currentCustomer = c;
            }
        }
    }
     
    public void setCurrentHotel(String _current){
        for(Hotel c: hotelArrayList){
            if(_current.equals(c.getName())){
                this.currentHotel = c;
            }
        }
    }
     
    public void setHotelArrayList(HashSet<Hotel> _temp){
        this.hotelList.setHotelHashSet(_temp);
    }
    
    public void setParentViewVisible(){
        this.tableView.setVisible(true);
    }
    
    public void setSelectedRow(int num){
        this.selectedRow = num;
    }
    
    public void setNameLabel(String _name){
        this.tableView.setUserName(_name);
    }
    
    //others
    public void removeDeletedRow(int _row){
        DefaultTableModel temp = tableView.getModel();
        temp.removeRow(_row);
    }
    
    private void updateRowOnSave(String name, String city, String ratingString){
        DefaultTableModel temp = tableView.getModel();
        String[] tempArray = new String[3];

        tempArray[0] = name;
        tempArray[1] = city;
        tempArray[2] = ratingString;
        
        detailView.setCurrentHotel(tempArray);
        boolean newHotel = true;
        for(int i=0; i < tableView.getModel().getRowCount(); i++){
            if(this.selectedRow == i){
                temp.removeRow(i);
                
                Object[] obj = new Object[]{name, city, ratingString};

                temp.insertRow(i, obj);
                newHotel = false;
            }
            
        }
        
        if(newHotel){
            Object[] obj = new Object[]{name, city, ratingString};
            temp.addRow(obj);
        }
        
    }

    //listeners
    private class SearchButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            HotelTableController.this.searchController = new SearchController(HotelTableController.this);
            HotelTableController.this.tableView.setVisible(false);

            
        }
        
    }
    
    private class ChangeUserButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            loginController.setLoginView(true);
            tableView.setVisible(false);
            
        }
    }
    
    private class ContinueButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            int selectedRow = HotelTableController.this.tableView.getTableSelectedRow();

            if(selectedRow != -1){
                
                HotelTableController.this.setCurrentCustomer(HotelTableController.this.tableView.getUserName());
                HotelTableController.this.setCurrentHotel(HotelTableController.this.getSelectedHotel(selectedRow)[0]);

                HotelTableController.this.finalizeView = new FinalizeDetailsView(HotelTableController.this);

                try {
                    HotelTableController.this.finalizeDetails = new FinalizeDetailsController(HotelTableController.this.finalizeView, HotelTableController.this.serializedData, HotelTableController.this, HotelTableController.this.currentHotel, HotelTableController.this.currentCustomer);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(HotelTableController.class.getName()).log(Level.SEVERE, null, ex);
                }
                

                HotelTableController.this.finalizeView.setVisible(true);
                HotelTableController.this.tableView.setVisible(false);
                     
            } else {
                System.out.println("User did not select hotel");
            }
        }
    }
    
    private class NewButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) { 
            
            HotelTableController.this.detailView = new HotelDetailsView(HotelTableController.this);
            HotelTableController.this.detailView.setVisible(true);
            
            HotelTableController.this.detailView.setMainLabel("New Hotel");      
            HotelTableController.this.detailView.setHotelField("");
            HotelTableController.this.detailView.setAddressField("");
            HotelTableController.this.detailView.setCityField("");
            HotelTableController.this.detailView.setStateField("");
            HotelTableController.this.detailView.setZipField("");
            HotelTableController.this.detailView.setRatingField("");
            
           
        }
        
    }
    
    private class ReservationsButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            
            HotelTableController.this.tableView.setVisible(false);
            try {
                HotelTableController.this.reservationController = new ReservationsController(HotelTableController.this, HotelTableController.this.serializedData);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HotelTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }
    
    
}
