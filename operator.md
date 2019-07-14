### 式と演算子

- エスケープシーケンス

```
public class Main {
    public static void main(String[] args) {

        // " を出力するために \ でエスケープする
        System.out.println("二重引用句(\")");
        // 二重引用句(")
    }
}

```

- Javaの型変換

1. 自動型変換
    - 小さな型の値を大きな型に代入する時に限り、自動的に変換される
    - 強制的な型変換を支持するキャスト演算子を使うことはできる
        - int age = (int) 3.2;

2. 明示的な型変換

3. 演算時の自動型変換

- 命令実行の文

```
// 画面に表示する
System.out.println(x);

// 改行せずに画面に表示する
System.out.print(s);

```

- 大きい方の数字を代入する

```
public class Main {
    public static void main(String[] args) {

        int a = 5;
        int b = 3;

        // 大きい数字がmに代入される
        int m = Math.max(a, b);

        System.out.println("比較して" +
                a + "と" + b + "で大きい方は " + m);

        // 比較して5と3で大きい方は 5
    }
}

```

- 文字列を数字に変換する

```

public class Main {
    public static void main(String[] args) {

        String age = "31";

        // 文字列を数字に型変換する
        int n = Integer.parseInt(age);

        // 数字として演算
        System.out.println("Next year, Your age is " + (n + 1));
        // Next year, Your age is 32
    }
}

```

-  乱数を発生させる

```
public class Main {

    public static void main(String[] args) {

        // 90までの間で乱数を発生させる
        int r = new java.util.Random().nextInt(90);

        System.out.println("your age is " + r + " right?");
        // 実行するたびに値が変わる
        // your age is 86 right?
    }
}

```

- キーボードから1行の命令を受け取る
- キーボードから1つの整数の入力を受け付ける

```

public class Main {

    public static void main(String[] args) {

        System.out.println("What's your name?: ");

        // キーボードの入力から文字列を受け取る
        String name = new java.util.Scanner(System.in).nextLine();

        System.out.println("How old are you?: ");

        // 数字を受け取る
        int age = new java.util.Scanner(System.in).nextInt();

        System.out.println("Welcome," + name + "(" + age + ")");

        // What's your name?:
        // kiyota
        // How old are you?:
        // 25
        // Welcome,kiyota(25)
    }
}

```

- String変数にintの計算結果を代入する

```
public class Main {
    public static void main(String[] args) {

        int x = 5;
        int y = 10;

        // オペランドの中に文字列が含まれると他のオペランドも文字列に変換される
        // int変数の計算は()で囲むことで計算の評価順位を引き上げることで可能
        String ans = "x + y = " + (x + y);

        System.out.println(ans);
        // x + y = 15
    }
}

```

- fortune program

```

// "x + y = 15" と表示
public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Fortune House");
        System.out.println("Type your name, please: ");

        String name = new java.util.Scanner(System.in).nextLine();

        System.out.println("Tell me your age, please: ");

        // 年齢をintで受け取る(数字以外の入力はエラーになる)
        int age = new java.util.Scanner(System.in).nextInt();

        // 年齢を文字列として受け付ける場合は型変換が必要
        // String ageString = new java.util.Scanner(System.in).nextLine();

        // 文字列をintに変換
        //int age = Integer.parseInt(ageString);

        // 0~3の乱数を変数に代入
        int fortune = new java.util.Random().nextInt(4);

        // インクリメント演算子で1増やして、1~4の乱数にする
        fortune++;

        System.out.println("Your fortune is...");
        System.out.println(name + "(" + age + ")" + "'s fortune is " + fortune);
        System.out.println("1: Excellent 2: Happy 3: soso 4: bad");

        // Welcome to Fortune House
        // Type your name, please:
        // kiyota
        // Tell me your age, please:
        // 25
        // Your fortune is...
        // kiyota(25)'s fortune is 2
        // 1: Excellent 2: Happy 3: soso 4: bad
    }
}

```