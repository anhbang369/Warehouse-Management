/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListStockeeper;

/**
 *
 * @author GMT
 */
public class InventoryTempDTO {
    private int quantity;
    private String inputDate;
    private String warranty;
    private String note;
    private String stockKeeperID;
    private String productID;
    private int quantityOnHand;

    public InventoryTempDTO() {
    }

    public InventoryTempDTO(int quantity, String inputDate, String warranty, String note, String stockKeeperID, String productID, int quantityOnHand) {
        this.quantity = quantity;
        this.inputDate = inputDate;
        this.warranty = warranty;
        this.note = note;
        this.stockKeeperID = stockKeeperID;
        this.productID = productID;
        this.quantityOnHand = quantityOnHand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStockKeeperID() {
        return stockKeeperID;
    }

    public void setStockKeeperID(String stockKeeperID) {
        this.stockKeeperID = stockKeeperID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }
    
}
