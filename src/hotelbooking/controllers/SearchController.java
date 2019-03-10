/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.Customer;
import hotelbooking.models.Hotel;
import hotelbooking.views.FinalizeDetailsView;
import hotelbooking.views.SearchView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Ortiz
 */
public class SearchController {
    
    //field names
    private HotelTableController parentController;
    
    private FinalizeDetailsController finalizeDetailsController;
    private FinalizeDetailsView finalizeDetailsView;
    
    private SearchView searchView;
    private HashSet<Hotel> hotelHashSet;
    private int selectedRow;
    private boolean firstSearch = true;
    private ArrayList<Hotel> foundHotel = new ArrayList<Hotel>();
    private Hotel selectedHotel;
    
    
    
    //constructor
    public SearchController(HotelTableController parent){
        
        this.parentController = parent;
        
        searchView = new SearchView();
        searchView.setVisible(true);
        
        hotelHashSet = parentController.getHotelArrayList();
        
        searchView.setNumFound(0);
        
        searchView.addBackButtonListener(new BackButtonListener());
        searchView.addSelectButtonListener(new SelectButtonListener());
        searchView.addSearchFieldKeyPressed(keyListener);
    }
    
    //accessors
    public Hotel getSelectedHotel(){
        return this.selectedHotel;
    }
    
    //others
    public void performSearch(){
        
        String searchQuery = searchView.getSearchField();
            
        if(!firstSearch){
            searchView.clearTable();
        }
        
        boolean validSearch = false;
        boolean found = false;

        foundHotel.clear();

        if(!searchQuery.isEmpty()){
            validSearch = true;
        } else {
            searchView.setNumFound(0);
        }
            
        if(validSearch){

            for(Hotel h:hotelHashSet){
                if(h.getDetails().toLowerCase().contains(searchQuery.toLowerCase())){
                    found = true;
                    foundHotel.add(h);
                }
            }
            
            if(found){
                searchView.setNumFound(foundHotel.size());
                searchView.createTable(foundHotel);

                firstSearch = false;

            } else {
                searchView.setNumFound(0);
            }

        }//end validSearch

    } //end performSearch
    
    //listeners
    private class BackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            SearchController.this.parentController.setParentViewVisible();
            SearchController.this.searchView.dispose();
            
        }
        
    } // end BackButtonListener
    
    private KeyListener keyListener = new KeyListener() {
        public void keyPressed(KeyEvent keyEvent) {
        }
        public void keyTyped(KeyEvent keyEvent) {   
        }
        public void keyReleased(KeyEvent e) {
            performSearch();
        }
        
    }; //end keyListener
    
    private class SelectButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {    
            
            selectedRow = searchView.getSelectedRow();
            selectedHotel = foundHotel.get(selectedRow);
            
            SearchController.this.finalizeDetailsView = new FinalizeDetailsView(parentController);
            SerializedDataController serialData = parentController.getSerializedDataCollectionController();
            
            String selectedCustString = parentController.getTableViewUserName();
            parentController.setCurrentCustomer(selectedCustString);
            Customer selectedCust = parentController.getCurrentCustomer();
            
            try {
                SearchController.this.finalizeDetailsController = new FinalizeDetailsController(SearchController.this.finalizeDetailsView, serialData, parentController, selectedHotel, selectedCust);
                
                SearchController.this.finalizeDetailsView.setVisible(true);
                searchView.setVisible(false);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    } // end SelectButtonListener
    
}
