/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.Reservation;
import hotelbooking.models.ReservationLinkedList;
import hotelbooking.views.ReservationsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ortiz
 */
public class ReservationsController {
    
    //fields
    private HotelTableController parentController;
    private SerializedDataController serializedData;
    private ArrayList<Reservation> reservationArrayList; 
    
    private ReservationsView reservationView;
    
    private ReservationLinkedList reservationLinkedList = new ReservationLinkedList();;
    private LinkedList<Reservation> linkedList = reservationLinkedList.getReservationLinkedList();
    
    //constructor
    public ReservationsController(HotelTableController controller, SerializedDataController _serializedData) throws FileNotFoundException{
        this.parentController = controller;
        this.serializedData = _serializedData;
        
        
        this.reservationArrayList = this.serializedData.getReservationsList();
        
        reservationView = new ReservationsView(this.parentController);
        reservationView.setVisible(true);
        
        this.reservationView.addDeleteButtonListener(new DeleteButtonListener());

        for(Reservation r: reservationArrayList){
            
            reservationLinkedList.addReservation(r);
        } 
        
        ListIterator<Reservation> reservationIterator = linkedList.listIterator();

        int count = 0;
        Format formatter = new SimpleDateFormat("MM/dd/yyy");
        String[] customers = new String[reservationArrayList.size()];
        String[] hotels = new String[reservationArrayList.size()];
        String[] dates = new String[reservationArrayList.size()];
        
        while(reservationIterator.hasNext()){
            
            Reservation temp = reservationIterator.next();
            
            customers[count] = temp.getCustomer().getFullName();
            hotels[count] = temp.getHotel().getName();
            String s = formatter.format(temp.getCheckInDate());
            dates[count] = s;
               
            count++;
        
        }
        reservationView.createTable(customers, hotels, dates);
        
        
    }
    
    //listeners
    private class DeleteButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            int selectedRow = reservationView.getSelectedRow();
            DefaultTableModel model = reservationView.getModel();
            
            if(selectedRow == -1){
                System.out.println("User did not select row");
            } else {
                Reservation res = ReservationsController.this.reservationLinkedList.getReservationByIndex(selectedRow);
                ReservationsController.this.reservationLinkedList.removeReservation(res);
                
                ReservationsController.this.linkedList = ReservationsController.this.reservationLinkedList.getReservationLinkedList();
                
                ReservationsController.this.serializedData.setReservationsList(ReservationsController.this.linkedList);
                
                model.removeRow(selectedRow);
                
                
            }
            
         
            
        }
        
    } //end of DeleteButtonListener
}
