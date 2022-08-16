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
public class UserOrderFullSeller {
    public int orderID;
    public String customerName;
    public String address;
    public String phoneNumber;
    public String status;
    public String note;
    public String deliveryDate;
    public String sellerID;
    public String orderDetailID;
    public int quantity;
    public String productID;

    public UserOrderFullSeller() {
    }

    public UserOrderFullSeller(int orderID, String customerName, String address, String phoneNumber, String status, String note, String deliveryDate, String sellerID, String orderDetailID, int quantity, String productID) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.note = note;
        this.deliveryDate = deliveryDate;
        this.sellerID = sellerID;
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.productID = productID;
    }

    

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    
    
    
    
    
}
