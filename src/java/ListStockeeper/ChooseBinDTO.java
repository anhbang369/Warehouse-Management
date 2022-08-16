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
public class ChooseBinDTO {
    private String binID;
    private int quantity;

    public ChooseBinDTO() {
    }

    public ChooseBinDTO(String binID, int quantity) {
        this.binID = binID;
        this.quantity = quantity;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
