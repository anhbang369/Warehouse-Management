/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 84348
 */
public class Timer {
    public String timeNow(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(dNow);
    }
    public String timeNowYear(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy");
        return ft.format(dNow);
    }
    
    public String timeNowUser(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        return ft.format(dNow);
    }
    
    public String dateFull(){
        Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		// Trả về giá trị từ 0 - 11
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int millis = c.get(Calendar.MILLISECOND);
                String full = day+""+(month + 1)+""+year+" "+hour+""+minute+""+second+""+millis;

//		System.out.println("Year: " + year);
//		System.out.println("Month: " + (month + 1));
//		System.out.println("Day: " + day);
//		System.out.println("Hour: " + hour);
//		System.out.println("Minute: " + minute);
//		System.out.println("Second: " + second);
//		System.out.println("Minute: " + minute);
//		System.out.println("Milli Second: " + millis);
        return full;
    }
}
