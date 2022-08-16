/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualSeller;

import java.util.HashMap;
import java.util.Map;
import orderSeller.UserProductError;

/**
 *
 * @author 84348
 */
public class ListErrorProduct {
    private Map<String, UserProductError> listErrorProduct;

    public ListErrorProduct() {
    }

    public ListErrorProduct(Map<String, UserProductError> listErrorProduct) {
        this.listErrorProduct = listErrorProduct;
    }

    public Map<String, UserProductError> getListErrorProduct() {
        return listErrorProduct;
    }

    public void setListErrorProduct(Map<String, UserProductError> listErrorProduct) {
        this.listErrorProduct = listErrorProduct;
    }   
    
    public boolean add(UserProductError tm){
        boolean check = false;
        if(this.listErrorProduct == null){
            this.listErrorProduct=new HashMap<>();
        }if(this.listErrorProduct.containsKey(tm.getProductID())){
            
        }
        listErrorProduct.put(tm.getProductID(), tm);
        check=true;
        return check;
    }
}
