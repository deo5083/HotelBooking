/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.tests;

import hotelbooking.models.Amenity;
import hotelbooking.models.BusinessCustomer;
import hotelbooking.models.Customer;
import hotelbooking.models.Dates;
import hotelbooking.models.Details;
import hotelbooking.models.FamilyCustomer;
import hotelbooking.models.Hotel;
import hotelbooking.models.Reservation;
import hotelbooking.models.Room;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author David Ortiz
 */
public class TestHarness {
    
    public TestHarness(){
        System.out.println("---------- Begin manual class tests ------------");
        
        Amenity testAmenity = new Amenity("Pool", 20.0);
        
        
        if(testAmenity!=null){
            
            System.out.println("Test amenity created succesfully.");
            System.out.println("Amenity info: " + testAmenity.getAmenity() + ", " + testAmenity.getPrice());

            testAmenity.setAmenity("Wifi");
            System.out.println("Amenity info: " + testAmenity.getAmenity() + ", " + testAmenity.getPrice());

            testAmenity.setPrice(10.0);
            System.out.println("Amenity info: " + testAmenity.getAmenity() + ", " + testAmenity.getPrice());

        }
        
        Customer testCustomer = new Customer("David", "Ortiz", 2);
        
        if(testCustomer != null){
            System.out.println("\nTest customer created succesfully.");
            System.out.println("Customer info: " + testCustomer.getFirstName() + ", " + testCustomer.getLastName() + ", " + testCustomer.getFullName() + ", " + testCustomer.getAmountOfTravelers());

            testCustomer.setFirstName("Mike");
            System.out.println("Customer info: " + testCustomer.getFirstName() + ", " + testCustomer.getLastName() + ", " + testCustomer.getFullName() + ", " + testCustomer.getAmountOfTravelers());

            testCustomer.setLastName("Stevens");
            System.out.println("Customer info: " + testCustomer.getFirstName() + ", " + testCustomer.getLastName() + ", " + testCustomer.getFullName() + ", " + testCustomer.getAmountOfTravelers());

            testCustomer.setAmountOfTravelers(1);
            System.out.println("Customer info: " + testCustomer.getFirstName() + ", " + testCustomer.getLastName() + ", " + testCustomer.getFullName() + ", " + testCustomer.getAmountOfTravelers());

           
        }
        
        System.out.println("---------- End manual class tests ------------");
        System.out.println();

        
        testInterface();
        testClassHierarchy();

        
    } // end method
    
    public void testInterface(){
        System.out.println("---------- Begin interface tests ------------");
        
        //setting objects for testing
        Amenity breakfast = new Amenity("Breakfast", 20.0);
        Amenity pool = new Amenity("Pool", 15.0);
        Amenity wifi = new Amenity("Wifi", 5.0);
        Amenity valet = new Amenity("Valet", 40.0);
        Amenity[] amenities = {pool, breakfast, wifi, valet};
        Amenity[] preferences = {valet, breakfast};

        Customer customer = new Customer("David", "Ortiz", 2);
        
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(2018, 6-1, 1);
        Calendar secondDate = Calendar.getInstance();
        secondDate.set(2018, 6-1, 3);
        Dates dates = new Dates(firstDate, secondDate);
        
        Hotel hotel = new Hotel("Hyatt", "45 W North St", "Bethlehem", "PA", 18018, 4.5, amenities);

        //just in case
        //ArrayList<Amenity> amenityArrayList = new ArrayList<Amenity>(Arrays.asList(amenities));
        
        Room room = new Room(123, 2, 90.0, true);

        //Reservation reservation = new Reservation(customer, room, dates, preferences);


        //testing Details interface
        ArrayList<Details> detailsList = new ArrayList<Details>();
        
        detailsList.add(customer);
        detailsList.addAll(Arrays.asList(preferences));
        detailsList.add(dates);
        detailsList.add(hotel);
        detailsList.add(room);
        //detailsList.add(reservation);


        int i =0;
	for(Details _item : detailsList)
        {
            detailsList.get(i).printDetails();
            i++;
        }
        
        System.out.println("---------- End interface tests ------------");
        System.out.println();
        
    } // end testInterface()
    
    public void testClassHierarchy(){
        System.out.println("---------- Begin Class Hierarchy tests ------------");
        BusinessCustomer elon = new BusinessCustomer("Tesla", "Elon", "Musk", 1);
        BusinessCustomer tim = new BusinessCustomer("Apple", "Tim", "Cook", 2);

        FamilyCustomer ortiz = new FamilyCustomer("David", "Ortiz", 0, 2);
        FamilyCustomer fake = new FamilyCustomer("Mike", "Stevens", 3, 2);
        
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        customerList.add(elon);
        customerList.add(tim);
        customerList.add(ortiz);
        customerList.add(fake);
        
        int i =0;
	for(Customer cus : customerList)
        {
            System.out.println(customerList.get(i).getInformation());
            i++;
        }
        
        System.out.println("---------- End Class Hierarchy tests ------------");
       
    }
} // end class
