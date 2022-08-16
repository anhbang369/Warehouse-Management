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
public class UserOrderDetailView {
    public String orderDetailID;
    public int quantity;
    public int orderId;
    public String productId;

    public UserOrderDetailView() {
    }

    public UserOrderDetailView(String orderDetailID, int quantity, int orderId, String productId) {
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    
}
