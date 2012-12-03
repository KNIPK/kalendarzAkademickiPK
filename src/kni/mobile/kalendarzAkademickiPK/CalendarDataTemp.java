package kni.mobile.kalendarzAkademickiPK;

// klasa pomocnicza, beda w niej przechowywane obiekty bazy danych

public class CalendarDataTemp {
	// deklaracja wszystkich danych obiektu
	private long id;
	private String theme;
	private String sched;
	private int date_year = 0;
	private int date_month = 0;
	private int date_day = 0;
	private int time_hour = 0;
	private int time_minute = 0;

	// metody do ustawiania i pobierania danych obiektu
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSched() {
		return sched;
	}

	public void setSched(String sched) {
		this.sched = sched;
	}

	public int getDateYear() {
		return date_year;
	}

	public void setDateYear(int date_year) {
		this.date_year = date_year;
	}

	public int getDateMonth() {
		return date_month;
	}

	public void setDateMonth(int date_month) {
		this.date_month = date_month;
	}

	public int getDateDay() {
		return date_day;
	}

	public void setDateDay(int date_day) {
		this.date_day = date_day;
	}

	public int getTimeHour() {
		return time_hour;
	}

	public void setTimeHour(int time_hour) {
		this.time_hour = time_hour;
	}

	public int getTieMinute() {
		return time_minute;
	}

	public void setTimeMinute(int time_minute) {
		this.time_minute = time_minute;
	}

	// bedzie uzywane przez adapter w listview i wyswietlana tabela zwrucona
	// przez ta metode
	@Override
	public String toString() {
		return theme;
	}
}
