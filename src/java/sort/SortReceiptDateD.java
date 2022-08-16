/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Comparator;
import receiptAccountant.UserReceipt;

/**
 *
 * @author 84348
 */
public class SortReceiptDateD implements Comparator<UserReceipt>{

    @Override
    public int compare(UserReceipt o1, UserReceipt o2) {
        return o2.getInputDate().compareTo(o1.getInputDate());
    }
    
}
