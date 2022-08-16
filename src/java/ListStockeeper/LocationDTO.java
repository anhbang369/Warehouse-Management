/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListStockeeper;

import java.sql.Date;

/**
 *
 * @author GMT
 */
public class LocationDTO {
    private String binID;
    private String inputDate;
    private int quantity;
    private int capacity;
    private String avalable;

    public LocationDTO() {
    }

    public LocationDTO(String binID, String inputDate, int quantity, int capacity, String avalable) {
        this.binID = binID;
        this.inputDate = inputDate;
        this.quantity = quantity;
        this.capacity = capacity;
        this.avalable = avalable;
    }

    

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAvalable() {
        return avalable;
    }

    public void setAvalable(String avalable) {
        this.avalable = avalable;
    }

    
}
