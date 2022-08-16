/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productManager;

/**
 *
 * @author 84348
 */
public class UserProducNotiManager {
    public String productID;
    public String name;

    public UserProducNotiManager() {
    }

    public UserProducNotiManager(String productID, String name) {
        this.productID = productID;
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
