/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryAccountant;

/**
 *
 * @author 84348
 */
public class UserInventory {
    public String productID;
    public String name;
    public String checkingDate;
    public int quantityInChecking;
    public int quality;
    public int quantity;
    public String note;

    public UserInventory() {
    }

    public UserInventory(String productID, String name, String checkingDate, int quantityInChecking, int quality, int quantity, String note) {
        this.productID = productID;
        this.name = name;
        this.checkingDate = checkingDate;
        this.quantityInChecking = quantityInChecking;
        this.quality = quality;
        this.quantity = quantity;
        this.note = note;
    }

    

    

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(String checkingDate) {
        this.checkingDate = checkingDate;
    }

    public int getQuantityInChecking() {
        return quantityInChecking;
    }

    public void setQuantityInChecking(int quantityInChecking) {
        this.quantityInChecking = quantityInChecking;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

    
    
}
