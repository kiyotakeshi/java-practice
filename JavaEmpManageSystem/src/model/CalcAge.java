package model;

import java.time.LocalDate;
import java.time.Period;

/*
 *  birthdayを取得して年齢計算を行うクラス
 */
public class CalcAge {

	public static int calcAge(LocalDate birthday) {

		// 現在時刻
		LocalDate now = LocalDate.now();
		Period p = Period.between(birthday, now);
		int age = p.getYears();
		return age;
	}
}
