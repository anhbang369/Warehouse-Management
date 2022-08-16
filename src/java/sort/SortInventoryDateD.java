/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import inventoryAccountant.UserInventory;
import java.util.Comparator;

/**
 *
 * @author 84348
 */
public class SortInventoryDateD implements Comparator<UserInventory>{

    @Override
    public int compare(UserInventory o1, UserInventory o2) {
        return o2.getCheckingDate().compareTo(o1.getCheckingDate());
    }
    
}
