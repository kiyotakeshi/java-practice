package model;

import java.time.LocalDate;
import java.time.Period;

/**
 *  birthdayを取得して年齢計算を行うクラス
 */
public class CalcAge {

	/**
	 * 現在時刻とbirthdayの差を
	 * 年単位に換算し、年齢を算出
	 * @param birthday
	 * @return int
	 */
	public static int calcAge(LocalDate birthday) {

		// 現在時刻
		LocalDate now = LocalDate.now();
		Period p = Period.between(birthday, now);
		int age = p.getYears();
		return age;
	}
}
