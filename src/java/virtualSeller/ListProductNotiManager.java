/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualSeller;

import java.util.HashMap;
import java.util.Map;
import productManager.UserProducNotiManager;

/**
 *
 * @author 84348
 */
public class ListProductNotiManager {
    private Map<String, UserProducNotiManager> listProductNotiManager;

    public ListProductNotiManager() {
    }

    public ListProductNotiManager(Map<String, UserProducNotiManager> listProductNotiManager) {
        this.listProductNotiManager = listProductNotiManager;
    }

    public Map<String, UserProducNotiManager> getListProduct() {
        return listProductNotiManager;
    }

     
    
    public boolean add(UserProducNotiManager tm){
        boolean check = false;
        if(this.listProductNotiManager == null){
            this.listProductNotiManager=new HashMap<>();
        }if(this.listProductNotiManager.containsKey(tm.getProductID())){
            
        }
        listProductNotiManager.put(tm.getProductID(), tm);
        check=true;
        return check;
    }
}
