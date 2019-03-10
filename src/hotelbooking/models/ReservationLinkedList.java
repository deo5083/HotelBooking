/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import java.util.LinkedList;
import java.util.Calendar;
import java.util.ListIterator;

/**
 *
 * @author David Ortiz
 */
public class ReservationLinkedList {
    
    //fields
    private LinkedList<Reservation> reservationLinkedList;
    
    private LinkedList<Reservation> testReservationList;
    
    //constructor
    public ReservationLinkedList(){
        
        this.reservationLinkedList = new LinkedList<Reservation>();
        
        // ********************************* Test LinkedList *********************************
        //this.testReservationList = new LinkedList<Reservation>();
        //this.buildTestLinkedList();
        //this.printTestLinkedList();
        
    }
    
    //accessors
    public LinkedList<Reservation> getReservationLinkedList(){
        return this.reservationLinkedList;
    }
    
    public int getSize(){
        return this.reservationLinkedList.size();
    }
    
    public Reservation getReservation(String searchName){
        Reservation tempReservation = new Reservation();
        boolean found=false;
        
        for(Reservation r : this.reservationLinkedList){
            if(searchName.equals(r.getCustomer().getFullName())){
                
                tempReservation = r;
                found = true;
                break;
            }
        }
        
        return tempReservation;     
    }
    
    public Reservation getReservationByIndex(int index){
        return this.reservationLinkedList.get(index);
        
    }
    
    
    //mutator
    public void setReservationArrayList(LinkedList<Reservation> _reservationArrayList){
        this.reservationLinkedList = _reservationArrayList;
    }
    
    //others
    public void addReservation(Reservation _reservation){
        boolean added = false;
        boolean insertDecision = false;
        
        ListIterator<Reservation> reservationIterator = this.reservationLinkedList.listIterator();
        
        
        while(reservationIterator.hasNext()){
            insertDecision = reservationIterator.next().insertionDecision(_reservation);
            
            if(insertDecision){
                this.reservationLinkedList.add(reservationIterator.previousIndex(), _reservation);
                added = true;
                break;
            }
            
        }
        
        if(!added){
           this.reservationLinkedList.add(_reservation); 
        }
        
    }
    
    public void removeReservation(Reservation _reservation){
        this.reservationLinkedList.remove(_reservation);
        
    }
    
    public void clearReservations(){
        this.reservationLinkedList.clear();
        
    }
    
    public void printLinkedList(){
        ListIterator<Reservation> reservationIterator = reservationLinkedList.listIterator();
        
        
        while(reservationIterator.hasNext()){
            Reservation current = reservationIterator.next();
            
            System.out.println(current.getCustomer().getFullName() + " " + current.getCheckInDate());

        }
        
        System.out.println("---------------------------------");
        
    }
    
    
    
    // **************************************** Testing ****************************************
    public void buildTestLinkedList(){
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(2018, 2-1, 1);
        
        Calendar secondDate = Calendar.getInstance();
        secondDate.set(2018, 3-1, 1);
        
        Calendar thirdDate = Calendar.getInstance();
        thirdDate.set(2018, 4-1, 1);
        
        Calendar fourthDate = Calendar.getInstance();
        fourthDate.set(2018, 5-1, 1);
        
        Calendar fifthDate = Calendar.getInstance();
        fifthDate.set(2018, 10-1, 1);
        
        Calendar sixthDate = Calendar.getInstance();
        sixthDate.set(2018, 7-1, 1);
        
        Calendar seventhDate = Calendar.getInstance();
        seventhDate.set(2018, 8-1, 1);
        
        Calendar eigthDate = Calendar.getInstance();
        eigthDate.set(2018, 9-1, 1);
        
        Calendar endDate = Calendar.getInstance();
        endDate.set(2018, 13-1, 3);
        
        Dates firstTest = new Dates(firstDate, endDate);
        Dates secondTest = new Dates(secondDate, endDate);
        Dates thirdTest = new Dates(thirdDate, endDate);
        Dates fourthTest = new Dates(fourthDate, endDate);
        Dates fifthTets = new Dates(fifthDate, endDate);
        Dates sixthTest = new Dates(sixthDate, endDate);
        Dates seventhTest = new Dates(seventhDate, endDate);
        Dates eigthTest = new Dates(eigthDate, endDate);
        
        Customer a = new Customer("test", "customer");
        
        this.addTestReservation(new Reservation(a, secondTest));
        this.addTestReservation(new Reservation(a, fifthTets));
        this.addTestReservation(new Reservation(a, firstTest));
        this.addTestReservation(new Reservation(a, fourthTest));
        this.addTestReservation(new Reservation(a, thirdTest));
        this.addTestReservation(new Reservation(a, sixthTest));
        this.addTestReservation(new Reservation(a, eigthTest));
        this.addTestReservation(new Reservation(a, seventhTest));
        
    }
    
    public void addTestReservation(Reservation _reservation){
        boolean added = false;
        boolean insertDecision = false;
        
        ListIterator<Reservation> reservationIterator = this.testReservationList.listIterator();
        
        
        while(reservationIterator.hasNext()){
            
            insertDecision = reservationIterator.next().insertionDecision(_reservation);
            
            if(insertDecision){
                this.testReservationList.add(reservationIterator.previousIndex(), _reservation);
                added = true;
                break;
            }
            
        }
        
        if(!added){
           this.testReservationList.add(_reservation); 
        }
        
    }
    
    public void removeTestReservation(Reservation _reservation){
        this.testReservationList.remove(_reservation);
        
    }
    
    public Reservation getTestReservation(String searchName){
        Reservation tempReservation = new Reservation();
        boolean found=false;
        
        for(Reservation r : this.testReservationList){
            if(searchName.equals(r.getCustomer().getFullName())){
                
                tempReservation = r;
                found = true;
                break;
            }
        }
        
        return tempReservation;        
      
    }
    
    public void printTestLinkedList(){
        ListIterator<Reservation> reservationIterator = testReservationList.listIterator();
        
        
        while(reservationIterator.hasNext()){
            Reservation current = reservationIterator.next();
            
            System.out.println(current.getCustomer().getFullName() + " " + current.getCheckInDate());

        }
        
        System.out.println("---------------------------------");
        
    }
    
    // **************************************** End Testing ****************************************

}
