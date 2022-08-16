/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualSeller;

import java.util.HashMap;
import java.util.Map;
import orderSeller.UserOrderDetailSeller;

/**
 *
 * @author 84348
 */
public class ListOrder {
    private Map<String, UserOrderDetailSeller> listOrder;

    public ListOrder() {
    }

    public ListOrder(Map<String, UserOrderDetailSeller> listOrder) {
        this.listOrder = listOrder;
    }

    public Map<String, UserOrderDetailSeller> getListOrder() {
        return listOrder;
    }

    public void setListOrder(Map<String, UserOrderDetailSeller> listOrder) {
        this.listOrder = listOrder;
    }   
    
    public boolean add(UserOrderDetailSeller tm){
        boolean check = false;
        if(this.listOrder == null){
            this.listOrder=new HashMap<>();
        }if(this.listOrder.containsKey(tm.getProductId())){
            
        }
        listOrder.put(tm.getProductId(), tm);
        check=true;
        return check;
    }
    public boolean remove(String productID){
        boolean check = false;
        if(this.listOrder!=null){
            if(this.listOrder.containsKey(productID)){
                this.listOrder.remove(productID);
            }
        }
        return check;
    }

    public boolean edit(String productID, UserOrderDetailSeller tm){
        boolean check = false;
        if(this.listOrder!=null){
            if(this.listOrder.containsKey(productID)){
                this.listOrder.replace(productID, tm);
            }
        }
        return check;
    }
}
