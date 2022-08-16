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
public class UserIssueS {
    public int issueID;
    public String note;
    public String accountantID;
    public String sellerID;
    public int orderID;
    public String DateP;

    public UserIssueS() {
    }

    public UserIssueS(int issueID, String note, String accountantID, String sellerID, int orderID, String DateP) {
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

    public String getDateP() {
        return DateP;
    }

    public void setDateP(String DateP) {
        this.DateP = DateP;
    }
    
}
