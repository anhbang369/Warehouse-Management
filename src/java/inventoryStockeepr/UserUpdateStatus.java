/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryStockeepr;

/**
 *
 * @author 84348
 */
public class UserUpdateStatus {
    public int issueID;
    public String note;

    public UserUpdateStatus() {
    }

    public UserUpdateStatus(int issueID, String note) {
        this.issueID = issueID;
        this.note = note;
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

    
    
    
}
