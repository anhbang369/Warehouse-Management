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
public class UserBinQuantity {
    public int quantity;
    public String binID;

    public UserBinQuantity() {
    }

    public UserBinQuantity(int quantity, String binID) {
        this.quantity = quantity;
        this.binID = binID;
    }
    

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
