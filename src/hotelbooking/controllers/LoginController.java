/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.Customer;
import hotelbooking.views.CustomerTableView;
import hotelbooking.views.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Ortiz
 */
public class LoginController {
    
    //fields
    private HotelTableController tableController; 
    private LoginView loginView = new LoginView();
    private int selectedRow;
    
    private SerializedDataController serializedData = new SerializedDataController();

    //constructor
    public LoginController() throws FileNotFoundException{
        
        this.loginView = new LoginView();
        
        loginView.setVisible(true);
         
        loginView.addLoginButtonListener(new LoginButtonListener());
        loginView.addChangeUserButtonListener(new ChangeUserButtonListener());
        
        loginView.setUsername(serializedData.getFirstNames()[0] +" "+ serializedData.getLastNames()[0]);
    }
    
    //mutators
    public void setSelectedRow(int num){
        this.selectedRow = num;
    }
    
    public void setLoginView(boolean b){
        this.loginView.setVisible(b);
    }
    
    public void setNameLabel(String _name){
        this.loginView.setUsername(_name);
    }
    
    //accessors
    public SerializedDataController getSerializedDataCollectionController(){
        return this.serializedData;
    }
    
    //listeners
    private class LoginButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                String cust = loginView.getUsername();
                LoginController.this.tableController = new HotelTableController(serializedData, LoginController.this);
                LoginController.this.loginView.setVisible(false);
                
                for(Customer c:serializedData.getCustomersList()){
                    
                    if(c.getFullName().equals(cust)){
                        LoginController.this.tableController.setCurrentCustomer(c.getFirstName());
                        LoginController.this.tableController.setNameLabel(c.getFirstName());
                        break;
                    }
                }
               
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    } // end LoginButtonListener
    
    private class ChangeUserButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                CustomerTableController customerController = new CustomerTableController(serializedData);
                CustomerTableView customerView = new CustomerTableView(LoginController.this);
                customerView.createTable(serializedData.getFirstNames(), serializedData.getLastNames(), serializedData.getAmountOfTravelers());
                
                
                customerView.setVisible(true);
                LoginController.this.loginView.setVisible(false);

                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    } // end ChangeUserButtonListener
}
