- 制御構文
    - どんな複雑なプログラムでも作成可能なことが証明されている(構造化定理)

1. 順次
2. 分岐
3. 繰り返し

- 無限ループに注意

```
package com.kiyota;

// "x + y = 15" と表示
public class Main {
    public static void main(String[] args) {

        boolean doorClose = true;

        // 以下は無限ループになるので注意
//        while (doorClose == true) {
//            System.out.println("Knock!");
//            System.out.println("1minite wait...");
//        }
    }
}

```

- 文字列の比較,if文とブロック

```
package com.kiyota;

// "x + y = 15" と表示
public class Main {
    public static void main(String[] args) {
        String s = "sunlight";

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

//        コンパイルエラーにならないが挙動がおかしくなることもあるため、気をつける
//        if (s == "sunlight") {
//            System.out.println(s);
//        }

    }
}

```

- switch文

```
package com.kiyota;

// "x + y = 15" と表示
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

package com.kiyota;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("hello");
        }
    }
}


```

- 男性なら年齢を出力

```
package com.kiyota;

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

- 選択した値を元に処理するswitch文

```
package com.kiyota;

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

- ランダムに出力した数字と一致しているかを確認するプログラム

```
package com.kiyota;

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

- 