package kni.mobile.kalendarzAkademickiPK;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CalendarDataSource {
	private CalendarSqliteHelper calDatabase;
	private SQLiteDatabase database;

	public CalendarDataSource(Context ctx) {
		calDatabase = new CalendarSqliteHelper(ctx);
	}

	public void open() throws SQLException {
		database = calDatabase.getWritableDatabase();
	}

	public void close() {
		calDatabase.close();
	}

	public CalendarDataTemp createCalendarDataTemp(String theme, String sched,
			int year, int month, int day, int hour, int minute) {
		ContentValues values = new ContentValues();
		values.put(CalendarSqliteHelper.COLUMN_THEME, theme);
		values.put(CalendarSqliteHelper.COLUMN_SCHED, sched);
		values.put(CalendarSqliteHelper.COLUMN_DATE_YEAR, year);
		values.put(CalendarSqliteHelper.COLUMN_DATE_MONTH, month);
		values.put(CalendarSqliteHelper.COLUMN_DATE_DAY, day);
		values.put(CalendarSqliteHelper.COLUMN_TIME_HOUR, hour);
		values.put(CalendarSqliteHelper.COLUMN_TIME_MINUTE, minute);
		long insertId = database.insert(CalendarSqliteHelper.TABLE_NAME, null,
				values);
		Cursor cursor = database.query(CalendarSqliteHelper.TABLE_NAME, null,
				CalendarSqliteHelper.COLUMN_ID + "=" + insertId, null, null,
				null, null);
		cursor.moveToFirst();
		CalendarDataTemp newCalendarDataTemp = cursorToCalendarDataTemp(cursor);
		cursor.close();
		return newCalendarDataTemp;
	}

	public void deleteCalendarDataTemp(CalendarDataTemp calendarDataTemp) {
		long id = calendarDataTemp.getId();
		database.delete(CalendarSqliteHelper.TABLE_NAME,
				CalendarSqliteHelper.COLUMN_ID + " = " + id, null);
	}

	public List<CalendarDataTemp> getAllCalendarDataTemp() {
		List<CalendarDataTemp> allCalendarDataTemp = new ArrayList<CalendarDataTemp>();
		Cursor cursor = database.query(CalendarSqliteHelper.TABLE_NAME, null,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CalendarDataTemp calendarDataTemp = cursorToCalendarDataTemp(cursor);
			allCalendarDataTemp.add(calendarDataTemp);
			cursor.moveToNext();
		}
		cursor.close();
		return allCalendarDataTemp;
	}

	public CalendarDataTemp getCalendarDataTemp(long id) {
		Cursor cursor = database.query(CalendarSqliteHelper.TABLE_NAME, null,
				CalendarSqliteHelper.COLUMN_ID + " = " + id, null, null, null,
				null);
		CalendarDataTemp calendarDataTemp = cursorToCalendarDataTemp(cursor);
		cursor.close();
		return calendarDataTemp;
	}

	private CalendarDataTemp cursorToCalendarDataTemp(Cursor cursor) {
		CalendarDataTemp calendarDataTemp = new CalendarDataTemp();
		calendarDataTemp.setId(cursor.getLong(0));
		calendarDataTemp.setTheme(cursor.getString(1));
		calendarDataTemp.setSched(cursor.getString(2));
		calendarDataTemp.setDateYear(cursor.getInt(3));
		calendarDataTemp.setDateMonth(cursor.getInt(4));
		calendarDataTemp.setDateDay(cursor.getInt(5));
		calendarDataTemp.setTimeHour(cursor.getInt(6));
		calendarDataTemp.setTimeMinute(cursor.getInt(7));
		return calendarDataTemp;
	}
}
