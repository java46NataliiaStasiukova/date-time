package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintCalendar {

	public static void main(String[] args) {
		DayOfWeek weekDay = DayOfWeek.valueOf("SUNDAY");
		System.out.printf("%s\n", weekDay.getDisplayName(TextStyle.FULL,
				Locale.forLanguageTag("en")));
		
		int monthYear[];
		try {
			monthYear = getMonthYear(args);
			printCalendar(monthYear[0], monthYear[1], monthYear[2]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void printCalendar(int month, int year, int weekDay) {
		pribtTitle(month, year);
		printWeekDays(weekDay);
		printDates(month, year, weekDay);
		
	}

	private static void printDates(int month, int year, int wDay) {
		int column = getFirstColumn(month, year, wDay);
		printOffSet(column);
		int nDays = getMonthDays(month, year);
		int nWeekDays = DayOfWeek.values().length;
		
		for(int day = 1; day <= nDays; day++) {
			System.out.printf("%4d", day);
			  column++;
			  if(column == nWeekDays) {
				  column = 0;
				  System.out.println();
			  }
		}
		
	}

	private static int getMonthDays(int month, int year) {
		YearMonth ym = YearMonth.of(year, month);
		return ym.lengthOfMonth();
	}

	private static void printOffSet(int column) {
		System.out.printf("%s", " ".repeat(column * 4));
		
	}

	private static int getFirstColumn(int month, int year, int wDay) {
		LocalDate firstMonthDate = LocalDate.of(year, month, 1);
		int weekDay = firstMonthDate.getDayOfWeek().getValue();
		int res;
		if(wDay <= 4) {
			res = (weekDay + 7) - wDay;
		} else {
			res = (weekDay + 7) - wDay;
		}
		if(res > 7) {
			res = res - 7;
		}
		return res;
	}
	private static void printWeekDays(int wDay) {
		DayOfWeek current = DayOfWeek.of(1);
		if(wDay != 0) {
			current = DayOfWeek.of(wDay);
		}
		int count = 1;
		System.out.print("  ");
		do {
			System.out.printf("%s ", current.getDisplayName(TextStyle.SHORT,
					Locale.getDefault()));
			current = current.plus(1);
		}while(count++ < 7);
		System.out.println();
	}
	private static void pribtTitle(int month, int year) {
		Month monthEn = Month.of(month);
		System.out.printf("%s, %d\n", monthEn.getDisplayName(TextStyle.FULL, 
				Locale.getDefault()), year);
		
	}

	private static int[] getMonthYear(String[] args) throws Exception {
		LocalDate current = LocalDate.now();
		int [] res = {current.getMonthValue(), current.getYear(), 1};
		if(args.length > 0) {
			res[0] = getMonth(args[0]);
			if(args.length > 1) {
				res[1] = getYear(args[1]);
				if(args.length > 2) {
					res[2] = getWeekDay(args[2]);
				}
			}
		}
		
		return res;
	}
	private static int getWeekDay(String weekDay) throws Exception {
		try {
			int res = DayOfWeek.valueOf(weekDay.toUpperCase()).getValue();
			int nDaysWeek = DayOfWeek.values().length;
			if(res < 0 || res > nDaysWeek) {
				throw new Exception(String.format("Day of the week %d id wrong value;"
						+ "should be in range from Sunday to Saturday", weekDay));
			}
		return res;

		} catch (NumberFormatException e) {
			throw new Exception("Day of the week not existing");
		}
	}

	private static int getYear(String yearStr) throws Exception{
		try {
			int res = Integer.parseInt(yearStr);
			if(res <= 0) {
				throw new Exception("Year should be positive");
				
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("Year should be a number");
		}
	}
	private static int getMonth(String monthStr) throws Exception{
		try {
			int res = Integer.parseInt(monthStr);
			int nMonth = Month.values().length;
			if(res < 1 || res > nMonth) {
				throw new Exception(String.format("month %d id wrong value;"
						+ "should be in range [1, %d]", res, nMonth));
				
			}
			return res;
		} catch (NumberFormatException e) {
			throw new Exception("Month should be a number");
		}
	}



}
