package kni.mobile.kalendarzAkademickiPK;

import android.app.Application;

public class MyApplication extends Application{
	private static CalendarDataTemp cal_dat_temp;
	
	public static CalendarDataTemp getCalendarDataTemp(){
		return cal_dat_temp;
	}
	
	public static void setCalendatDataTemp(CalendarDataTemp cdt){
		cal_dat_temp = cdt;
	}
	
}
