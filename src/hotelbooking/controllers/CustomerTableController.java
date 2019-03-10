/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.controllers;

import hotelbooking.models.BusinessCustomer;
import hotelbooking.models.Customer;
import hotelbooking.models.CustomerList;
import hotelbooking.models.FamilyCustomer;
import hotelbooking.views.CustomerTableView;
import hotelbooking.views.EditCustomerView;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David Ortiz
 */
public class CustomerTableController {
    
    //fields
    private EditCustomerView editView;
    private CustomerTableView customerView;
    private CustomerList customerList;
    private int selectedRow;
    private ArrayList<Customer> customerArrayList;
 
    private String[] fnames;
    private String[] lnames;
    private String[] amounts;
    
    private SerializedDataController serializedData;
    
    
    //constructor
    public CustomerTableController(SerializedDataController _serializedData) throws FileNotFoundException{
        
        this.serializedData = _serializedData;
        
        fnames = serializedData.getFirstNames();
        lnames = serializedData.getLastNames();
        amounts = serializedData.getAmountOfTravelers();
        
        
        this.customerArrayList = this.serializedData.getCustomersList();

    }
    
    //accessors
    public void getEditCustomerView(int _selectedRow){
        this.editView = new EditCustomerView(this, _selectedRow);
        this.editView.setVisible(true);
    }
    
    public void getEditCustomerViewNew(){
        this.editView = new EditCustomerView(this);
        this.editView.setVisible(true);
    }
    
    public ArrayList<Customer> getCustomerArrayList(){
        
        return this.serializedData.getCustomersList();
    }
    
    public String[] getSelectedCustomer(int _selectedRow){
        
        
        String[] temp = new String[3];
        
        fnames = serializedData.getFirstNames();
        lnames = serializedData.getLastNames();
        amounts = serializedData.getAmountOfTravelers();
        
        
        temp[0] = fnames[_selectedRow];
        temp[1] = lnames[_selectedRow];
        temp[2] = amounts[_selectedRow];
        
        return temp;
    }
    
    public CustomerTableView getCustomerDetailView(){
        return this.customerView;
    }
    
    public EditCustomerView getCustomerEditView(){
        return this.editView;
    }
    
    public int getSelectedRow(){
        return this.selectedRow;
    }
    
    //mutators
    public void setParentViewVisible(){
        this.customerView.getCustomerDetailView().setVisible(true);
    }
    
    public void setSelectedRow(int num){
        this.selectedRow = num;
    }
    
    public void setCustomerArrayList(ArrayList<Customer> _customerArrayList){
        this.serializedData.setCustomersList(_customerArrayList);
    }
    
    public void setCustomerDetailView(CustomerTableView _current){
        this.customerView = _current;
    }
    
    public void setEditCustomerView(EditCustomerView _current){
        this.editView = _current;
    }
    
    //others
    public void removeDeletedRow(int _row){
        DefaultTableModel temp = customerView.getModel();
        temp.removeRow(_row);
    }
    
    public void prepareNewFamily(){
        this.editView.setTypeFamily();
        this.editView.hideDeleteButton();
        this.editView.setMainLabel("Enter customer details");
        this.editView.setAdultsField("");
        this.editView.setFnameField("");
        this.editView.setLnameField("");
        this.editView.setKidsField("");
        this.editView.setAdultsField("");
        this.editView.hideCompanyField();
    }
    
    public void prepareNewBusiness(){
        this.editView.setTypeBusiness();
        this.editView.hideDeleteButton();
        this.editView.setMainLabel("Enter customer details");
        this.editView.setAdultsField("");
        this.editView.setFnameField("");
        this.editView.setLnameField("");
        this.editView.hideKidsField();
        this.editView.setAdultsField("");
        this.editView.setCompanyField("");
    }
    
    public void saveCustomer(String type, String fname, String lname, String adults, String kids, String company){
  
        boolean isItNew = this.editView.getIsItNew();
        
        if(isItNew){
            this.selectedRow = serializedData.getCustomersList().size();

            if(type.equals("Family")){
                FamilyCustomer tempFam = new FamilyCustomer(fname, lname, Integer.parseInt(kids), Integer.parseInt(adults));
                customerArrayList.add(tempFam);
                int total = Integer.parseInt(kids) + Integer.parseInt(adults);
                adults = Integer.toString(total);
            } else if(type.equals("Business")){
                BusinessCustomer tempBus = new BusinessCustomer(company, fname, lname, Integer.parseInt(adults));
                customerArrayList.add(tempBus);
            }

        } else {
            
            this.selectedRow = this.editView.getSelectedRow();

            if(customerArrayList.get(this.selectedRow).getType().equals("family")){

                customerArrayList.get(this.selectedRow).setFirstName(fname);
                customerArrayList.get(this.selectedRow).setLastName(lname);
                customerArrayList.get(this.selectedRow).setNumOfKids(Integer.parseInt(kids));
                customerArrayList.get(this.selectedRow).setNumOfAdults(Integer.parseInt(adults));
                
                int total = Integer.parseInt(kids) + Integer.parseInt(adults);
                adults = Integer.toString(total);

            } else if(customerArrayList.get(this.selectedRow).getType().equals("business")){

                customerArrayList.get(this.selectedRow).setFirstName(fname);
                customerArrayList.get(this.selectedRow).setLastName(lname);
                customerArrayList.get(this.selectedRow).setAmountOfTravelers(Integer.parseInt(adults));
                customerArrayList.get(this.selectedRow).setCompany(company);

            }//end update if
        } //end if new
        
        serializedData.setCustomersList(this.customerArrayList);
        
        this.updateRowOnSave(fname, lname, adults);
        
    } //end save
    
    private void updateRowOnSave(String fname, String lname, String numOfPeople){
        
        DefaultTableModel temp = customerView.getModel();
        String[] tempArray = new String[3];

        tempArray[0] = fname;
        tempArray[1] = lname;
        tempArray[2] = numOfPeople;
     
        customerView.setCurrentCustomer(tempArray);
        boolean newCustomer = true;
        for(int i=0; i < customerView.getModel().getRowCount(); i++){
            if(this.selectedRow == i){
                temp.removeRow(i);
                
                Object[] obj = new Object[]{fname, lname, numOfPeople};

                temp.insertRow(i, obj);
                newCustomer = false;
            }
            
        }
        
        if(newCustomer){
            Object[] obj = new Object[]{fname, lname, numOfPeople};
            temp.addRow(obj);
        }
        
    } //end update
      
} //end class
