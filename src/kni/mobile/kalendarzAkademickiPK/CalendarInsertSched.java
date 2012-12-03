package kni.mobile.kalendarzAkademickiPK;

import java.util.Calendar;

import kni.mobile.kalendarzAkademickiPK.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class CalendarInsertSched extends Activity {
	CalendarDataSource datasource;
	Calendar realCal;

	private EditText edT;
	private EditText edS;
	private DatePicker calDate;
	private TimePicker calTime;

	private String strEdT = "";
	private String strEdS = "";
	private int calPicDatY;
	private int calPicDatM;
	private int calPicDatD;
	private int calPicTimH;
	private int calPicTimM;

	private CalendarDataTemp calDatTem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_insert_sched);

		datasource = new CalendarDataSource(this);

		edT = (EditText) findViewById(R.id.cal_ins_sch_et1);
		edS = (EditText) findViewById(R.id.cal_ins_sch_et2);
		calDate = (DatePicker)findViewById(R.id.cal_ins_sch_dp);
		calTime = (TimePicker)findViewById(R.id.cal_sch_ins_tp);
		realCal = Calendar.getInstance();
		
	}

	@Override
	public void onResume() {
		datasource.open();

		calDatTem = MyApplication.getCalendarDataTemp();
		
		calPicDatY = realCal.get(Calendar.YEAR);
		calPicDatM = realCal.get(Calendar.MONTH);
		calPicDatD = realCal.get(Calendar.DAY_OF_MONTH);
		calPicTimH = realCal.get(Calendar.HOUR_OF_DAY);
		calPicTimM = realCal.get(Calendar.MINUTE);
		
		if (calDatTem != null) {
			strEdT = calDatTem.getTheme();
			edT.setText(strEdT);
			strEdS = calDatTem.getSched();
			edS.setText(strEdS);
			

		}
		calDate.init(calPicDatY, calPicDatM, calPicDatD, null);
		calTime.setCurrentHour(calPicTimH);
		calTime.setCurrentMinute(calPicTimM);

		super.onResume();
	}

	@Override
	public void onPause() {
		datasource.close();
		super.onPause();
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.cal_ins_sch_but1:
			if (calDatTem != null)
				datasource.deleteCalendarDataTemp(calDatTem);

			strEdT = edT.getText().toString();
			strEdS = edS.getText().toString();
			calPicDatY = calDate.getYear();
			calPicDatM = calDate.getMonth();
			calPicDatD = calDate.getDayOfMonth();
			calPicTimH = calTime.getCurrentHour();
			calPicTimM = calTime.getCurrentMinute();

			datasource.createCalendarDataTemp(strEdT, strEdS, calPicDatY, calPicDatM,
					calPicDatD, calPicTimH, calPicTimM);
			finish();
			break;

		case R.id.cal_ins_sch_but2:
			finish();
			break;

		case R.id.cal_ins_sch_but3:
			if (calDatTem != null)
				datasource.deleteCalendarDataTemp(calDatTem);
			finish();
			break;
		}
	}
}
