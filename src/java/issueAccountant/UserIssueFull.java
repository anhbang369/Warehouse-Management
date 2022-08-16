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
public class UserIssueFull {
    public int issueID;
    public String note;
    public String accountantID;
    public String sellerID;
    public String DateP;
    public int orderID;
    public String customerName;
    public String address;
    public int phoneNumber;
    public String deliveryDate;
    public int orderDetailID;
    public int quantity;
    public String productID;
    public String name;
    public String model;
    public String brand;
    

    public UserIssueFull() {
    }

    public UserIssueFull(int issueID, String note, String accountantID, String sellerID, String DateP, int orderID, String customerName, String address, int phoneNumber, String deliveryDate, int orderDetailID, int quantity, String productID, String name, String model, String brand) {
        this.issueID = issueID;
        this.note = note;
        this.accountantID = accountantID;
        this.sellerID = sellerID;
        this.DateP = DateP;
        this.orderID = orderID;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
        this.productID = productID;
        this.name = name;
        this.model = model;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAccountantID() {
        return accountantID;
    }

    public void setAccountantID(String accountantID) {
        this.accountantID = accountantID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getDateP() {
        return DateP;
    }

    public void setDateP(String DateP) {
        this.DateP = DateP;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
    
    
}
