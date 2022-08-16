/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import issueAccountant.UserIssueS;
import java.util.Comparator;

/**
 *
 * @author 84348
 */
public class SortIssueDateA implements Comparator<UserIssueS>{

    @Override
    public int compare(UserIssueS o1, UserIssueS o2) {
        return o1.getDateP().compareTo(o2.getDateP());
    }
    
}
