package com.rentalcar.userful;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidDateTest {

	@Test
	public void isGreaterEighteen() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1990, 11, 12);
		assertTrue(ValidDate.dateBirthGreaterEighteen(calendar.getTime()));
	}
	
	@Test
	public void isNotGreaterEighteen() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR) + 1;
		calendar.set(year, 11, 12);
		assertFalse(ValidDate.dateBirthGreaterEighteen(calendar.getTime()));
	}
	
	@Test
	public void isEighteenYear() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR) - 18;
		calendar.set(Calendar.YEAR, year);
		assertTrue(ValidDate.dateBirthGreaterEighteen(calendar.getTime()));
	}
}