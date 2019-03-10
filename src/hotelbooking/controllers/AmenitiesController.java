/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.Amenity;
import hotelbooking.views.AmenitiesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author David Ortiz
 */
public class AmenitiesController {
    
    //fields
    private FinalizeDetailsController parentFinalizeController;
    private AmenitiesView amenitiesView;
    private HotelTableController hotelTableController;
    
    private TreeSet<Amenity> amenityList;
    private ArrayList<Amenity> availableList;
    private ArrayList<Amenity> selectedList = new ArrayList<Amenity>();
    
    //constructor
    public AmenitiesController(FinalizeDetailsController finalizeController, HotelTableController tableController){
        
        this.hotelTableController = tableController;
        this.parentFinalizeController = finalizeController;
        
        this.amenityList = hotelTableController.getAmenityTreeSet();
        
        this.availableList = new ArrayList<Amenity>(amenityList);
        
        this.amenitiesView = new AmenitiesView(amenityList);
        
        this.amenitiesView.createAvailableTable(amenityList);
        
        this.selectedList = parentFinalizeController.getSelectedAmenitiesList();
        
        //checking if selectedList is empty. 
        if(selectedList.size()>0){
            amenitiesView.createSelectedTable(selectedList);
        }
        
        amenitiesView.addBackButtonListener(new BackButtonListener());
        amenitiesView.addAddButtonListener(new AddButtonListener());
        amenitiesView.addRemoveButtonListener(new RemoveButtonListener());
        amenitiesView.addSaveButtonListener(new SaveButtonListener());

 
    }
    
    //others
    public boolean isItAlreadyThere(Amenity amenity){
        boolean found = false;
        
        for(Amenity a: this.selectedList){
            
            if(amenity.getAmenity().equals(a.getAmenity())){
                found = true;
                break;
            }
        }
        
        return found;
        
    }
    
    //Listeners
    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = amenitiesView.getSelectedRowFromAvailable();
            
            if(selectedRow != -1){
                
                if(!isItAlreadyThere(availableList.get(selectedRow))){

                    amenitiesView.addRowToSelected(availableList.get(selectedRow));
                    selectedList.add(availableList.get(selectedRow));
                
                } 
                  
            } else {
                System.out.println("No amenity selected.");
            }
        }

    }

    private class RemoveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = amenitiesView.getSelectedRowFromSelected();
            
            if(selectedRow != -1){
                
                selectedList.remove(selectedRow);
                amenitiesView.getSelectedModel().removeRow(selectedRow);
                
                
            } else {
                System.out.println("No amenity selected.");
                
            } 
            
        }

    }

    private class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            parentFinalizeController.setFinalizeView(true);
            AmenitiesController.this.amenitiesView.setVisible(false);
        }

    }
    
    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           
            parentFinalizeController.setSelectedAmenitiesList(selectedList);
            
            parentFinalizeController.setFinalizeView(true);
            AmenitiesController.this.amenitiesView.setVisible(false);
            
            parentFinalizeController.update();
        }

    }
    
}
