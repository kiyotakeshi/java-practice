### 日付と時刻

- 日付はDate型を使う

```
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Date now = new Date();
        System.out.println(now);

//      インスタンス内に保持するlong値を取得
        System.out.println(now.getTime())

//      long値を引数として渡してインスタンス化すると指定時刻の情報が格納される
        Date past = new Date(136666666L);
        System.out.println(past);
    }
}
```

- Date,Calender型の問題点
    - 並列処理(スレッド)で使用すると変数の中身が書き換わってしまうことがある
    - 日常的に利用する曖昧な時間の表現ができない
    - 直感的に使えない(Chalender型で月の情報を取得する際に0~11を指定)

- Java8からTimeAPIを使用できるようになった

```
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {

//      現在時刻の取得
//      Instantクラスはエポックからの経過時間をナノ秒で格納する
        Instant i1 = Instant.now();

//      Instantとlong値の相互変換
        Instant i2 = Instant.ofEpochMilli(319202913332L);
        long l = i2.toEpochMilli();

//      ZonedDateTimeの生成
//      Instance同様にある瞬間を格納できる
        ZonedDateTime z1 = ZonedDateTime.now();
        ZonedDateTime z2 = ZonedDateTime.of(2014, 1, 2, 3, 4, 5, 6, ZoneId.of("Asia/Tokyo"));

//      InstantとZoneDateTimeの相互変換
        Instant i3 = z2.toInstant();
        ZonedDateTime z3 = i3.atZone(ZoneId.of("Europe/London"));

//      ZonedDateTimeの利用
        System.out.println("Tokyo:" + z2.getYear() + z2.getMonthValue() + z2.getDayOfMonth());
        System.out.println("London:" + z3.getYear() + z3.getMonthValue() + z3.getDayOfMonth());

//      同じ瞬間の判定には equals()は使わない
        if (z2.isEqual(z3)) {
            System.out.println("Same value");
        }
    }
}

// Tokyo:201412
// London:201411
// Same value
```

- 曖昧な日時を表す

```
import java.time.*;

public class Main {
    public static void main(String[] args) {

//      LocalDateTimeクラスはZonedDateTimeと違いタイムゾーン情報だけ格納しない
//      そのためLocalDateTimeインスタンス単体では
//      どの瞬間を指しているかは確定できないが、日常的に使う日時情報には最適
        LocalDateTime l1 = LocalDateTime.now();
        LocalDateTime l2 = LocalDateTime.of(2014, 1, 1, 9, 5, 0, 0);

//      LocalDateTimeとZoneDateTimeの相互変換
        ZonedDateTime z1 = l2.atZone(ZoneId.of("Europe/London"));
        LocalDateTime l3 = z1.toLocalDateTime();
    }
}
```

- 各種日時クラスのメソッドの利用例

```
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

//      文字列からLocalDateを生成
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate d = LocalDate.parse("2018/08/21", f);

//      1000日後を計算する
        d = d.plusDays(1000);
        String str = d.format(f);
        System.out.println("1000days after :" + str);
//      1000days after :2021/05/17

//      現在日付との比較
        LocalDate now = LocalDate.now();
        if (now.isAfter(d)) {
            System.out.println("now is after d");
        }

        LocalDate d1 = LocalDate.of(2012, 1, 1);
        LocalDate d2 = LocalDate.of(2012, 1, 4);

//      3日間を表す
        Period p1 = Period.ofDays(3);
        Period p2 = Period.between(d1, d2);
    }
}
```

---
### 確認

- Date型、Calendar型を使用
- 100日後の日付を西暦で表示するプログラム

```
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

//      現在の日時をDate型で取得
        Date now = new Date();
        Calendar c = Calendar.getInstance();

//      取得した日時情報をCalendarにセット
        c.setTime(now);

//      Chalendarから「日」の情報を取得
        int day = c.get(Calendar.DAY_OF_MONTH);

//      取得した値に100を足してCalendarの「日」にセット
        day += 100;
        c.set(Calendar.DAY_OF_MONTH, day);

//      Chalendar型の日付情報をDate型に変換
        Date future = c.getTime();

//      表示
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(f.format(future));
//      2019/10/26
    }
}
```

- Java8以降で使用できるTime APIを使用して実装

```
// なぜかエラー

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        LocalDate now = LocalDate.now();
//        LocalDate future = now.plusDays(100);
        LocalDate future = now.plus(Period.ofDays(100));

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyymmdd");
        System.out.println(future.format(f));
    }
}
```
