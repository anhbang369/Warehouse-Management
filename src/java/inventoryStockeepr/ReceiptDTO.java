/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryStockeepr;

import java.sql.Date;

/**
 *
 * @author GMT
 */
public class ReceiptDTO {
    private int receiptID;
    private Date inputDate;
    private String status;
    private int totalQuantity;
    private String note;
    private String accountantID;
    private String stockkeeperID;

    public ReceiptDTO() {
    }

    public ReceiptDTO(int receiptID, Date inputDate, String status, int totalQuantity, String note, String accountantID, String stockkeeperID) {
        this.receiptID = receiptID;
        this.inputDate = inputDate;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.note = note;
        this.accountantID = accountantID;
        this.stockkeeperID = stockkeeperID;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
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

    public String getStockkeeperID() {
        return stockkeeperID;
    }

    public void setStockkeeperID(String stockkeeperID) {
        this.stockkeeperID = stockkeeperID;
    }
    
}
