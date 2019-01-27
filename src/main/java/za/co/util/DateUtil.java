package za.co.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	public static Long daysBetween(String dateString) {

		if (dateString.length() > 10) {
			dateString = dateString.substring(0, 10);
		}
		LocalDate dueDate = LocalDate.parse(dateString);
		LocalDate currentDate = LocalDate.now();

		return ChronoUnit.DAYS.between(currentDate, dueDate);
	}

}
