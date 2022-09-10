package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		//returns temporal matching Friday 13
		// Friday is ChronoField.DAY_OF_WEEK == DayOfWeek.FRIDAY.ordinal() + 1
		// 13 day of month ChronoField.DAY_OF_MONTH == 13
		temporal = ajustTemporal(temporal);
		while(temporal.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.ordinal() + 1) {	
				temporal = temporal.plus(1, ChronoUnit.MONTHS).with(ChronoField.DAY_OF_MONTH, 13);
		}
		return temporal;
	}

	private Temporal ajustTemporal(Temporal temporal) {
		if(temporal.get(ChronoField.DAY_OF_MONTH) >= 13) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);
		}
		return temporal.with(ChronoField.DAY_OF_MONTH, 13);
	}

}
