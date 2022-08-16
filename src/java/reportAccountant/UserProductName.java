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
public class UserProductName {

    public String ProductID;
    public String Name;
    public String Brand;

    public UserProductName() {
    }

    public UserProductName(String ProductID, String Name, String Brand) {
        this.ProductID = ProductID;
        this.Name = Name;
        this.Brand = Brand;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    
    

}
