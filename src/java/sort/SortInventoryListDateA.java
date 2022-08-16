/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import inventoryStockeepr.InventoryDTO;
import java.util.Comparator;

/**
 *
 * @author 84348
 */
public class SortInventoryListDateA implements Comparator<InventoryDTO>{
    @Override
    public int compare(InventoryDTO o1, InventoryDTO o2) {
        return o1.getInputDate().compareTo(o2.getInputDate());
    }
}
