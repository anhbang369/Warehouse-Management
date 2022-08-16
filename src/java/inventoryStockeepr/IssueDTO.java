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
public class IssueDTO {
    private int issueID;
    private String note;
    private String accountantID;
    private String sellerID;
    private int orderID;
    private Date DateP;

    public IssueDTO() {
    }

    public IssueDTO(int issueID, String note, String accountantID, String sellerID, int orderID, Date DateP) {
        this.issueID = issueID;
        this.note = note;
        this.accountantID = accountantID;
        this.sellerID = sellerID;
        this.orderID = orderID;
        this.DateP = DateP;
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

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDateP() {
        return DateP;
    }

    public void setDateP(Date DateP) {
        this.DateP = DateP;
    }
    
}
