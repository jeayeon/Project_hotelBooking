package Swing;

import java.util.Calendar;

public class CalendarData {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2020, 5, 13);
		System.out.println(cal.getFirstDayOfWeek());
	}

}
