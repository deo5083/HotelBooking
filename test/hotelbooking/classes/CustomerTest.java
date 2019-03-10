/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.classes;

import hotelbooking.models.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Ortiz
 */
public class CustomerTest {
    
    public CustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setFirstName method, of class Customer.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        Customer instance = new Customer("David", "Ortiz", 2);
        
        instance.setFirstName("Mike");
        String result = instance.getFirstName();
        String expResult = "Mike";
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult,result);
    }

    /**
     * Test of setLastName method, of class Customer.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        Customer instance = new Customer("David", "Ortiz", 2);
        
        instance.setLastName("Stevens");
        String expResult = "Stevens";
        String result = instance.getLastName();
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult,result);
    }

    /**
     * Test of setAmountOfTravelers method, of class Customer.
     */
    @Test
    public void testSetAmountOfTravelers() {
        System.out.println("setAmountOfTravelers");
        Customer instance = new Customer("David", "Ortiz", 2);
        
        instance.setAmountOfTravelers(3);
        int expResult = 3;
        int result = instance.getAmountOfTravelers();
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult,result);
    }

    /**
     * Test of getFirstName method, of class Customer.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Customer instance = new Customer("David", "Ortiz", 2);
        
        String expResult = "David";
        String result = instance.getFirstName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getLastName method, of class Customer.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Customer instance = new Customer("David", "Ortiz", 2);
        
        String expResult = "Ortiz";
        String result = instance.getLastName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullName method, of class Customer.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        Customer instance = new Customer("David", "Ortiz");
        
        String expResult = "David Ortiz";
        String result = instance.getFullName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAmountOfTravelers method, of class Customer.
     */
    @Test
    public void testGetAmountOfTravelers() {
        System.out.println("getAmountOfTravelers");
        Customer instance = new Customer("David", "Ortiz", 2);
        
        int expResult = 2;
        int result = instance.getAmountOfTravelers();
        
        assertEquals(expResult, result);
    }
    
}
