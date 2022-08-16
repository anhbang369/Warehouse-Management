/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryStockeepr;

/**
 *
 * @author GMT
 */
public class BinDTO {
    private String binID;
    private int inventoryID;
    private int quantity;
    private int capacity;
    private String available;

    public BinDTO() {
    }

    public BinDTO(String binID, int inventoryID, int quantity, int capacity, String available) {
        this.binID = binID;
        this.inventoryID = inventoryID;
        this.quantity = quantity;
        this.capacity = capacity;
        this.available = available;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }    
}
