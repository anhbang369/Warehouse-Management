/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderSeller;

/**
 *
 * @author 84348
 */
public class UserProductError {
    public String productID;

    public UserProductError() {
    }

    public UserProductError(String productID) {
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    
}
