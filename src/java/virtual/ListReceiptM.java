/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual;

import java.util.HashMap;
import java.util.Map;
import receiptAccountant.UserReceiptDetail;

/**
 *
 * @author 84348
 */
public class ListReceiptM {
    private Map<String, UserReceiptDetail> listReceipt;

    public ListReceiptM() {
    }

    public ListReceiptM(Map<String, UserReceiptDetail> listReceipt) {
        this.listReceipt = listReceipt;
    }

    public Map<String, UserReceiptDetail> getListReceipt() {
        return listReceipt;
    }

    public void setListReceipt(Map<String, UserReceiptDetail> listReceipt) {
        this.listReceipt = listReceipt;
    }   
    
    public boolean add(UserReceiptDetail tm){
        boolean check = false;
        if(this.listReceipt == null){
            this.listReceipt=new HashMap<>();
        }if(this.listReceipt.containsKey(tm.getProductID())){
            
        }
        listReceipt.put(tm.getProductID(), tm);
        check=true;
        return check;
    }
    public boolean remove(String productID){
        boolean check = false;
        if(this.listReceipt!=null){
            if(this.listReceipt.containsKey(productID)){
                this.listReceipt.remove(productID);
            }
        }
        return check;
    }

    public boolean edit(String productID, UserReceiptDetail tm){
        boolean check = false;
        if(this.listReceipt!=null){
            if(this.listReceipt.containsKey(productID)){
                this.listReceipt.replace(productID, tm);
            }
        }
        return check;
    }
    
}
