/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual;

import java.util.HashMap;
import java.util.Map;
import inventoryAccountant.UserInventoryVirtual;

/**
 *
 * @author 84348
 */
public class ListInventory {
    private Map<String, UserInventoryVirtual> listInventory;

    public ListInventory() {
    }

    public ListInventory(Map<String, UserInventoryVirtual> listInventory) {
        this.listInventory = listInventory;
    }

    public Map<String, UserInventoryVirtual> getListInventory() {
        return listInventory;
    }

    public void setListInventory(Map<String, UserInventoryVirtual> listInventory) {
        this.listInventory = listInventory;
    }   
    
    public boolean add(UserInventoryVirtual tm){
        boolean check = false;
        if(this.listInventory == null){
            this.listInventory=new HashMap<>();
        }if(this.listInventory.containsKey(tm.getProductID())){
            
        }
        listInventory.put(tm.getProductID(), tm);
        check=true;
        return check;
    }
    
    public boolean remove(String productID){
        boolean check = false;
        if(this.listInventory!=null){
            if(this.listInventory.containsKey(productID)){
                this.listInventory.remove(productID);
            }
        }
        return check;
    }
    public boolean edit(String productID, UserInventoryVirtual tm){
        boolean check = false;
        if(this.listInventory!=null){
            if(this.listInventory.containsKey(productID)){
                this.listInventory.replace(productID, tm);
            }
        }
        return check;
    }
}
