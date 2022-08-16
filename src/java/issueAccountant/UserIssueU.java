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
public class UserIssueU {
    public int issueID;
    public String note;

    public UserIssueU() {
    }

    public UserIssueU(int issueID, String note) {
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
