/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportAccountant;

/**
 *
 * @author 84348
 */
public class UserProductReport {
    public String productID;
    public String Brand;

    public UserProductReport() {
    }

    public UserProductReport(String productID, String Brand) {
        this.productID = productID;
        this.Brand = Brand;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }
    
}
