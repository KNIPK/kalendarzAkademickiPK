package kni.mobile.kalendarzAkademickiPK;

import java.util.List;
import kni.mobile.kalendarzAkademickiPK.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CalendarMenu extends Activity {
	private CalendarDataSource datasource;
	private ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_menu);

		datasource = new CalendarDataSource(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.acm_but1:
			MyApplication.setCalendatDataTemp(null);
			startActivity(new Intent(getApplicationContext(),
					CalendarInsertSched.class));
			break;
		}
	}

	@Override
	public void onResume() {
		datasource.open();

		list = (ListView) findViewById(R.id.list);
		List<CalendarDataTemp> values = datasource.getAllCalendarDataTemp();
		final ArrayAdapter<CalendarDataTemp> adapter = new ArrayAdapter<CalendarDataTemp>(
				this, android.R.layout.simple_list_item_1, values);
		list.setAdapter(adapter);

		super.onResume();

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CalendarDataTemp cdt = (CalendarDataTemp) parent
						.getItemAtPosition(position);

				MyApplication.setCalendatDataTemp(cdt);
				startActivity(new Intent(getApplicationContext(),
						CalendarInsertSched.class));

			}
		});
	}

	@Override
	public void onPause() {
		datasource.close();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_calendar_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.calendar_menu:
			Toast.makeText(getApplicationContext(), "Jesteś już w kalendarzu!",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.forum:
			startActivity(new Intent(getApplicationContext(),
					WebviewActivity.class));
			return true;
		case R.id.author:
			return true;
		case R.id.exit:
			finish();
			return true;
		default:
			
		}
		return super.onOptionsItemSelected(item);
	}
}
