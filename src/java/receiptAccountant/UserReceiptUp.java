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
public class UserReceiptUp {

    public int receiptID;
    public String note;

    public UserReceiptUp() {
    }

    public UserReceiptUp(int receiptID, String note) {
        this.receiptID = receiptID;
        this.note = note;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
