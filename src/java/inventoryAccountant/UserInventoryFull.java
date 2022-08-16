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
public class UserInventoryFull {

    public int reportID;
    public String checkingDate;
    public int reportDetailID;
    public String productID;
    public int quality;
    public int quantityInChecking;
    public int quantity;
    public String note;

    public UserInventoryFull() {
    }

    public UserInventoryFull(int reportID, String checkingDate, int reportDetailID, String productID, int quality, int quantityInChecking, int quantity, String note) {
        this.reportID = reportID;
        this.checkingDate = checkingDate;
        this.reportDetailID = reportDetailID;
        this.productID = productID;
        this.quality = quality;
        this.quantityInChecking = quantityInChecking;
        this.quantity = quantity;
        this.note = note;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(String checkingDate) {
        this.checkingDate = checkingDate;
    }

    public int getReportDetailID() {
        return reportDetailID;
    }

    public void setReportDetailID(int reportDetailID) {
        this.reportDetailID = reportDetailID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getQuantityInChecking() {
        return quantityInChecking;
    }

    public void setQuantityInChecking(int quantityInChecking) {
        this.quantityInChecking = quantityInChecking;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
