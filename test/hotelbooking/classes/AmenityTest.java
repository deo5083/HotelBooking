/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.classes;

import hotelbooking.models.Amenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Ortiz
 */
public class AmenityTest {
    
    public AmenityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setAmenity method, of class Amenity.
     */
    @Test
    public void testSetAmenity() {
        System.out.println("setAmenity");
        Amenity instance = new Amenity();
        instance.setAmenity("Wifi");
        
        String expResult = "Wifi";
        String result = instance.getAmenity();
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrice method, of class Amenity.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        Amenity instance = new Amenity();
        instance.setPrice(20.0);
        
        double expResult = 20.0;
        double result = instance.getPrice();
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAmenity method, of class Amenity.
     */
    @Test
    public void testGetAmenity() {
        System.out.println("getAmenity");
        Amenity instance = new Amenity("Pool", 20.0);
        
        String expResult = "Pool";
        String result = instance.getAmenity();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Amenity.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Amenity instance = new Amenity("Pool", 20.0);
        
        double expResult = 20.0;
        double result = instance.getPrice();
        
        assertEquals(expResult, result, 0.0);
    }
    
}
