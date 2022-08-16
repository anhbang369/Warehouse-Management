/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual;

import java.util.HashMap;
import java.util.Map;
import user.UserNotify;

/**
 *
 * @author 84348
 */
public class ListNotify {
    private Map<String, UserNotify> listNotify;

    public ListNotify() {
    }

    public ListNotify(Map<String, UserNotify> listNotify) {
        this.listNotify = listNotify;
    }

    public Map<String, UserNotify> getListNotify() {
        return listNotify;
    }

    public void setListNotify(Map<String, UserNotify> listNotify) {
        this.listNotify = listNotify;
    }   
    
    public boolean add(UserNotify tm){
        boolean check = false;
        if(this.listNotify == null){
            this.listNotify=new HashMap<>();
        }if(this.listNotify.containsKey(tm.getName())){
            
        }
        listNotify.put(tm.getName(), tm);
        check=true;
        return check;
    }
    
    
}
