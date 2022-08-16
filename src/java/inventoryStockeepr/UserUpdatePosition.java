/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryStockeepr;

/**
 *
 * @author 84348
 */
public class UserUpdatePosition {
    public String binID;
    public int capacity;
    public String available;

    public UserUpdatePosition() {
    }

    public UserUpdatePosition(String binID, int capacity, String available) {
        this.binID = binID;
        this.capacity = capacity;
        this.available = available;
    }

    

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    
    
    
}
