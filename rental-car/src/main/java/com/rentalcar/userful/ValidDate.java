package com.rentalcar.userful;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class ValidDate {
	
	public static final int GREATER_EIGHTEEN = 18;
	
	private ValidDate() {}
	
	public static boolean dateBirthGreaterEighteen(Date date) {
		LocalDate today = LocalDate.now();
		LocalDate birth = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		birth = birth.plusYears(GREATER_EIGHTEEN);
		if (birth.equals(today) || birth.isBefore(today)) {
			return true;
		}
		return false;
	}

}
