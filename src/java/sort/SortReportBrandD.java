/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Comparator;
import reportAccountant.UserReport;

/**
 *
 * @author 84348
 */
public class SortReportBrandD implements Comparator<UserReport>{

    @Override
    public int compare(UserReport o1, UserReport o2) {
        return o2.getBrand().compareTo(o1.getBrand());
    }

    
    
}
