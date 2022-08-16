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
public class UserReportInventory {
    public String checkingDate;

    public UserReportInventory() {
    }

    public UserReportInventory(String checkingDate) {
        this.checkingDate = checkingDate;
    }

    public String getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(String checkingDate) {
        this.checkingDate = checkingDate;
    }
    
}
