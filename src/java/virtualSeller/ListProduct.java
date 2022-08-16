/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualSeller;

import java.util.HashMap;
import java.util.Map;
import user.UserProduct;

/**
 *
 * @author 84348
 */
public class ListProduct {
    private Map<String, UserProduct> listProduct;

    public ListProduct() {
    }

    public ListProduct(Map<String, UserProduct> listProduct) {
        this.listProduct = listProduct;
    }

    public Map<String, UserProduct> getListProduct() {
        return listProduct;
    }

     
    
    public boolean add(UserProduct tm){
        boolean check = false;
        if(this.listProduct == null){
            this.listProduct=new HashMap<>();
        }if(this.listProduct.containsKey(tm.getProductID())){
            
        }
        listProduct.put(tm.getProductID(), tm);
        check=true;
        return check;
    }
}
