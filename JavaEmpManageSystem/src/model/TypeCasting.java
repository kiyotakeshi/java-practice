package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 *  DBから取得した日付データを
 *  yyyy/MM/dd の形に成形するクラス
 */
public class TypeCasting {
	public static String toString(LocalDate day) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		String date = day.format(dtf);
		return date;
	}

}
