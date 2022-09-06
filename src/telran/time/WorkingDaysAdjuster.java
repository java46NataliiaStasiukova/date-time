package telran.time;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDaysAdjuster implements TemporalAdjuster {

int[] daysOff;
int nDays;
public int[] getDaysOff() {
	return daysOff;
}
public void setDaysOff(int[] daysOff) {
	this.daysOff = daysOff;
}
public int getnDays() {
	return nDays;
}
public void setnDays(int nDays) {
	this.nDays = nDays;
}
public WorkingDaysAdjuster(int[] daysOff, int nDays) {
	
	this.daysOff = daysOff;
	this.nDays = nDays;
}
public WorkingDaysAdjuster() {
}
	@Override
	public Temporal adjustInto(Temporal temporal) {
		//return new temporal matching a date after the given days
		if(daysOff.length == 7) {
			return temporal;
		}
		do {
			for(int day: daysOff) {
				if(temporal.get(ChronoField.DAY_OF_WEEK) == day) {		
					temporal = temporal.plus(1, ChronoUnit.DAYS);
				} 
			}
			temporal = temporal.plus(1, ChronoUnit.DAYS);
			
		}while(nDays-- != 0);

		return temporal.minus(1, ChronoUnit.DAYS);
	}


}
