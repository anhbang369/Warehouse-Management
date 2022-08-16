/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import issueAccountant.UserOrder;
import java.util.Comparator;

/**
 *
 * @author 84348
 */
public class SortOrderDateA implements Comparator<UserOrder>{

    @Override
    public int compare(UserOrder o1, UserOrder o2) {
        return o1.getDeliveryDate().compareTo(o2.getDeliveryDate());
    }
    
}
