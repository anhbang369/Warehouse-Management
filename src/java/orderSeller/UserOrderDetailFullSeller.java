/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderSeller;

/**
 *
 * @author 84348
 */
public class UserOrderDetailFullSeller {
    public String productId;
    public int orderID;
    public int quantity;

    public UserOrderDetailFullSeller() {
    }

    public UserOrderDetailFullSeller(String productId, int orderID, int quantity) {
        this.productId = productId;
        this.orderID = orderID;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
