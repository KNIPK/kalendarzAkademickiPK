package kni.mobile.kalendarzAkademickiPK;

/*
 * clasa definiujaca baze danych
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CalendarSqliteHelper extends SQLiteOpenHelper {
	// deklaracja nazw column bazy danych
	public static final String TABLE_NAME = "calendar";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_THEME = "theme";
	public static final String COLUMN_SCHED = "sched";

	public static final String COLUMN_DATE_YEAR = "date_year";
	public static final String COLUMN_DATE_MONTH = "date_month";
	public static final String COLUMN_DATE_DAY = "date_day";
	public static final String COLUMN_TIME_HOUR = "time_hour";
	public static final String COLUMN_TIME_MINUTE = "time_minute";

	// deklaracja nazwy pliku i wersji bazy danych
	public static final String DATABASE_NAME = "calendar_pk.db";
	public static final int DATABASE_VERSION = 1;

	// deklaracja lancucha do stworzenia bazy danych
	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_THEME + " TEXT NOT NULL, " + COLUMN_SCHED
			+ " TEXT NOT NULL, " + COLUMN_DATE_YEAR + " BYTE, "
			+ COLUMN_DATE_MONTH + " BYTE, " + COLUMN_DATE_DAY + " BYTE, "
			+ COLUMN_TIME_HOUR + " BYTE, " + COLUMN_TIME_MINUTE + " BYTE);";

	// kontruktor
	public CalendarSqliteHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// metoda inicjujaca baze danych
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	// metoda aktualizujaca baze danych
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(CalendarSqliteHelper.class.getName(),
				"Aktualizacja bazy danych z wersji " + oldVersion + " do "
						+ newVersion + " ktora skasuje wszystkie stare dane!");
		database.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
		onCreate(database);
	}
}
