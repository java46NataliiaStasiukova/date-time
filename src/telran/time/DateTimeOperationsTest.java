package telran.time;

import static org.junit.Assert.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DateTimeOperationsTest {
	LocalDate birthAS = LocalDate.of(1799, 6, 6);
	@Test
void dateOperationsTest() {
	LocalDate birthAS = LocalDate.of(1799, 6, 6);
	LocalDate barMizvaAS = birthAS.plusYears(13);
	assertEquals(LocalDate.of(1812, 6, 6), barMizvaAS);
	
}
	@Test
	void adjusterTest() {
		LocalDate birthAS = LocalDate.of(1799, 6, 6);
		LocalDateTime  lt = LocalDateTime.now();
		assertEquals(LocalDate.of(1812, 6, 6), birthAS.with(new BarMizvaAdjuster()));
		System.out.println(lt.with(new BarMizvaAdjuster()));
	}
	@Test
	void nextFriday13test() {
		TemporalAdjuster nextFriday13 = new NextFriday13();
		assertEquals(LocalDate.of(2023, 1, 13), LocalDate.of(2022, 9, 5)
				.with(nextFriday13));
		assertEquals(LocalDate.of(2023, 10, 13), LocalDate.of(2023, 1, 13)
				.with(nextFriday13));
		assertEquals(LocalDate.of(2023, 1, 13), LocalDate.of(2023, 1, 11)
				.with(nextFriday13));
	}
	@Test
	void workingDaysTest() {
		WorkingDaysAdjuster workingDays = new WorkingDaysAdjuster();
		workingDays.setDaysOff(new int[] {1, 2, 3, 4, 5, 6, 7});
		workingDays.setnDays(1000);
		assertEquals(LocalDate.now(),LocalDate.now().with(workingDays) );
		
		workingDays.setDaysOff(new int[] {});
		assertEquals(LocalDate.now().plusDays(1000), LocalDate.now().with(workingDays));
		
		workingDays.setDaysOff(new int[] {5, 6});
		workingDays.setnDays(7);
		assertEquals(LocalDate.of(2022, 9, 14), LocalDate.of(2022, 9, 5).with(workingDays));
		
		workingDays.setDaysOff(new int[] {7});
		workingDays.setnDays(20);
		assertEquals(LocalDate.of(2022, 9, 29), LocalDate.of(2022, 9, 6).with(workingDays));
	}
	@Test
	void getPersonAge() {
		String format = "birthdate is %s, today is %s, age is %d\n";
		LocalDateTime current = LocalDateTime.now();
		System.out.printf(format, LocalDateTime.of(1950, 1, 20, 0,0,0), current,
				ChronoUnit.YEARS.between(LocalDateTime.of(1950, 1, 20, 0,0,0), current));
		System.out.printf(format, LocalDateTime.of(1950, 10, 20, 0,0,0), current,
				ChronoUnit.SECONDS.between(LocalDateTime.of(1950, 10, 20,0,0,0), current));
		
	}
	@Test
	void chronoUnitTest() {
		ChronoUnit unit = ChronoUnit.WEEKS;
		System.out.printf("From AS birthday %d %s passed\n",
				unit.between(birthAS, LocalDate.now()), unit);
		
	}
	@Test
	void periodTest() {
		Period period = Period.between(birthAS, LocalDate.now());
		System.out.printf("number years %d, number month %d, number days %d\n",
				period.getYears(), period.getMonths(), period.getDays() );		
	}
	@Test
	void timeTest() {
		Instant current = Instant.now();
		System.out.printf("amount milliseconds from EPOCH time point %d\n", 
				current.toEpochMilli());
		System.out.printf("date time of EPOCH %s GMT\n", Instant.EPOCH);
		LocalDateTime ldt  =LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
		System.out.printf("date time of EPOCH %s Israel - LocalDateTime\n", ldt);
		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault());
		System.out.printf("date time of EPOCH %s Israel - ZonnedDateTime\n" ,zdt);
		ldt = LocalDateTime.now();
		zdt = ZonedDateTime.now();
		System.out.printf("date time of EPOCH %s Israel - LocalDateTime - now\n", ldt);
		System.out.printf("date time of EPOCH %s Israel - ZonedDateTime - now\n", zdt);
	}
	@Test
	void zonnedTimeTest() {
		ZoneId.getAvailableZoneIds()
		.stream().forEach(z -> System.out.printf("time zone name: %s, zone: %s\n", z, 
				ZoneId.of(z)));
	}
	@Test
	void timeInCanadaTest() {
		ZoneId.getAvailableZoneIds()
		.stream().filter(x -> x.toLowerCase().contains("canada"))
		.map(z -> ZonedDateTime.now(ZoneId.of(z))).forEach(System.out::println);;
	}
	@Test
	void dateTimeFormatterTest() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d, yyyy E h:m a B",
				Locale.forLanguageTag("en"));
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt.format(dtf));
	}
		
}
