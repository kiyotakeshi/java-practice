### 制御構文

- どんな複雑なプログラムでも作成可能なことが証明されている(構造化定理)

1. 順次
2. 分岐
3. 繰り返し

- 無限ループに注意

```
public class Main {
    public static void main(String[] args) {

        boolean doorClose = true;

//        以下は無限ループになるので注意
//        while (doorClose == true) {
//            System.out.println("Knock!");
//            System.out.println("1minite wait...");
//        }
    }
}

```

---
- 文字列の比較(if文とブロック)

```
public class Main {
    public static void main(String[] args) {

        String s = "sunlight";

        // 文字列の比較
        if (s.equals("sunlight")) {
            System.out.println(s);
        }

        if (s.equals("daytime")) {
            System.out.println(s);
        } else {
            System.out.println("daytime");
        }

        // ブロックを使用しなくても記述できるが非推奨
        // 以下のような1行のものには使用することもある
        if (s.equals("sunlight")) System.out.println(s);

        // コンパイルエラーにならないが挙動がおかしくなることもあるため
        // 文字列の比較には .equals("") を使う
        // if (s == "sunlight") {
        //    System.out.println(s);
        //  }
    }
}

```

---
- switch文

```
public class Main {
    public static void main(String[] args) {

        // switch文
        // breakを忘れると次の結果も表示してしまう
        // 2/5で実行したい処理などはあえてbreakを書かないテクニックも
        // your fortune is...
        // Excellent
        // Happy

        System.out.println("your fortune is...");
        int fortune = 1;
        switch (fortune) {
            case 1:
                System.out.println("Excellent");

            case 2:
                System.out.println("Happy");
                break;

            case 3:
                System.out.println("soso");
                break;

            default:
                System.out.println("bad");
        }
    }
}

```

- ある回数分繰り返す場合はfor文の方がよい

```
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("hello");
        }
    }
}

```

---
- 男性なら年齢を出力

```
public class Main {
    public static void main(String[] args) {
        int sex = 0;
        int age = 25;
        System.out.println("Hello");

        if (sex == 0) {
            System.out.println("I'm Man");
            System.out.println(age + "years old");
        } else {
            System.out.println("I'm Female");
        }
    }
}

```

---
- 選択した値を元に処理するswitch文

```
public class Main {
    public static void main(String[] args) {

        System.out.println("choose operation,please");
        System.out.println("1:search 2:register 3:delete 4:modify");

        int selected = new java.util.Scanner(System.in).nextInt();

        switch (selected){
            case 1:
                System.out.println("search now...");
                break;
            case 2:
                System.out.println("register now...");
                break;
            case 3:
                System.out.println("delete now...");
                break;
            case 4:
                System.out.println("modify now...");
                break;
            default:
                System.out.println("nothing");
        }
        // choose operation,please
        // 1:search 2:register 3:delete 4:modify
        // 2
        // register now...
    }
}

```

---
- ランダムに出力した数字と一致しているかを確認するプログラム

```
public class Main {
    public static void main(String[] args) {
        System.out.println("Match Number Game");

        // ランダムに数字を生成
        int ans = new java.util.Random().nextInt(10);

        // for文の中で一時的に使うiも型宣言が必要
        for (int i = 0; i < 5; i++) {
            System.out.println("Input number(0~9): ");
            int num = new java.util.Scanner(System.in).nextInt();

            if (num == ans) {
                System.out.println("Hit!");
            } else {
                System.out.println("Not Hit!");
            }
        }

        // Match Number Game
        // Input number(0~9):
        // 1
        // Hit!
        // Input number(0~9):
        // 1
        // Hit!
    }

}

```

---
- 配列
    - 関係あるデータをグループにして一つの変数に入れる
    - 構造に整理して後から特定の値を取り出せるようにする(データ構造)

```
public class Main {
    public static void main(String[] args) {

        // 配列と要素を別々に作成するパターン
        // int[] score;
        // score = new int[5];

        // 配列と要素を同時に作成する
        // 要素は自動的に初期化され0が入っている
        // Stringはnull,boaleanはfalseで初期化される
        int[] score = new int[5];

        System.out.println(score[0]); // 0
        System.out.println(score[1]); // 0
        System.out.println(score[2]); // 0

        // 配列の長さを調べる
        int length = score.length;
        System.out.println("Elements:" + length);
        // Elements:5

        // 二番目の要素に代入(1番目の要素は[0])
        score[1] = 30;
        System.out.println(score[1]);

        // 配列の作成と初期化の省略記法
        int[] score1 = new int[]{20, 30, 40, 50};

        System.out.println(score1[0]);
        System.out.println(score1[1]);
    }
}

```

---
- 点数管理プログラム

```

public class Main {
    public static void main(String[] args) {
        int[] score = {20, 30, 40, 50, 80};

        // 変数の初期化
        int sum = 0;
        int avg = 0;

        // 配列の添字の指定に変数を用いる
        // 項目が増えた場合にもコードの回収が少なくなる
        // forの終了条件に .length を用いる
        for (int i = 0; i < score.length; i++) {

            // 配列の値の合計、平均を求める
            sum += score[i];
            avg = sum / score.length;
        }

        // ループの外で出力
        System.out.println("Sum:" + sum);
        System.out.println("Average:" + avg);
        // Sum:220
        // Average:44
    }
}

```

---
- 拡張for文の使用

```

public class Main {
    public static void main(String[] args) {
        int[] score = {20, 30, 40, 50, 80};

        // 変数の初期化
        int sum = 0;
        int avg = 0;

        // 配列の添字の指定に変数を用いる
        // 項目が増えた場合にもコードの回収が少なくなる
        // forの終了条件に .length を用いる
        // for (int i = 0; i < score.length; i++) {

        // 拡張for文
        // ループが1周するたびに次の要素がvalueに入る
        for (int value : score){

            // 配列の値の合計、平均を求める
            // System.out.println(value);
            // 20
            // 30
            // 40
            // 50
            // 80
            sum += value;
            avg = sum / score.length;

        }

        // ループの外で出力
        System.out.println("Sum:" + sum);
        System.out.println("Average:" + avg);
        // Sum:220
        // Average:44
    }
}

```

---
- 配列の初期化(復習)

```

public class Main {
    public static void main(String[] args) {

        // int型の値を4つ格納できる配列
        int[] points = new int[4];

        // doubel型の値を5つ格納できる配列
        double[] weights = new double[5];

        // String型の値を3つ格納できる配列
        String[] names = new String[3];
    }
}

```

- 配列の取り出し(復習)

```

public class Main {
    public static void main(String[] args) {

        // int[] moneyList = new int[3];
        // moneyList[0]=1000;
        // moneyList[1]=5000;
        // moneyList[2]=15000;

        int[] moneyList = {1000, 5000, 15000};

        // javaは変数の宣言が必要！
        // for (i=0;i<moneyList.length;i++){
        for (int i = 0; i < moneyList.length; i++) {

            System.out.println(moneyList[i]);
            // 1000
            // 5000
            // 15000
        }

        // 拡張for
        for (int m : moneyList) {
            System.out.println(m);
        }

    }
}

```

- 配列、ifの組み合わせ

```

public class Main {
    public static void main(String[] args) {

        // int[] numbers = new int[3];
        // numbers[0] = 3;
        // numbers[1] = 4;
        // numbers[2] = 9;
        int[] numbers = {3, 4, 9};

        System.out.println("Type digit number,please");
        int input = new java.util.Scanner(System.in).nextInt();

        // for (n:numbers){
        for (int n : numbers) {
            if (n == input) {
                System.out.println("Match!");
            }
        }
    }
}

```