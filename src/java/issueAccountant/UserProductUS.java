/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package issueAccountant;

/**
 *
 * @author 84348
 */
public class UserProductUS {
    public int orderID;
    public String status;

    public UserProductUS() {
    }

    public UserProductUS(int orderID, String status) {
        this.orderID = orderID;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
