/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package receiptAccountant;

/**
 *
 * @author 84348
 */
public class UserFakeList {
    public String productID;
    public String model;
    public String brand;
    public String name;
    public int quantityInBill;
    public int quantityInShipping;
    public int receiptID;
    public String inputDate;
    public String status;
    public int totalQuantity;
    public String note;
    public String accountantID;
    public String stockKeeperID;
    public String solution;
    public int receiptDetailID;

    public UserFakeList() {
    }

    public UserFakeList(String productID, String model, String brand, String name, int quantityInBill, int quantityInShipping, int receiptID, String inputDate, String status, int totalQuantity, String note, String accountantID, String stockKeeperID, String solution, int receiptDetailID) {
        this.productID = productID;
        this.model = model;
        this.brand = brand;
        this.name = name;
        this.quantityInBill = quantityInBill;
        this.quantityInShipping = quantityInShipping;
        this.receiptID = receiptID;
        this.inputDate = inputDate;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.note = note;
        this.accountantID = accountantID;
        this.stockKeeperID = stockKeeperID;
        this.solution = solution;
        this.receiptDetailID = receiptDetailID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    

    

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

   

    public int getQuantityInBill() {
        return quantityInBill;
    }

    public void setQuantityInBill(int quantityInBill) {
        this.quantityInBill = quantityInBill;
    }

    public int getQuantityInShipping() {
        return quantityInShipping;
    }

    public void setQuantityInShipping(int quantityInShipping) {
        this.quantityInShipping = quantityInShipping;
    }


    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
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

    public String getStockKeeperID() {
        return stockKeeperID;
    }

    public void setStockKeeperID(String stockKeeperID) {
        this.stockKeeperID = stockKeeperID;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getReceiptDetailID() {
        return receiptDetailID;
    }

    public void setReceiptDetailID(int receiptDetailID) {
        this.receiptDetailID = receiptDetailID;
    }
    
    
}
