/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelbooking.models;

import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author David Ortiz
 */
public class HotelTableModel extends AbstractTableModel {

    //fields
    private String[] columnNames = {"Hotel","City", "Rating"};
    private Object[][] data;
    private HashSet<Hotel> hotelList;
    
    //constructor
    public HotelTableModel(HashSet<Hotel> _hotelList){
        this.hotelList = _hotelList;
    }
    
    //mutators
    public void setData(Object[][] _data){
        this.data = _data;
    }
    
    //accessors
    public String[] getColumns(){
        return columnNames;
    }
    
    @Override
    public int getRowCount() {
        return hotelList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    //others
    @Override
    public boolean isCellEditable(int row, int col){
        return true; 
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
