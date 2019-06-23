### Javaの基礎

- 開発の流れ
    1. ソースコードの作成
        - 人が読める状態のプログラムのこと

    2. コンパイル
        - CPUはマシン語(machine code)しか理解できない
        - ソースコードをコンパイルすることでバイトコードに変換する
        - *コンパイラが変換を行い、その際に文法の誤りをチェックする*

    3. 実行
        - *インタプリタとよばれるソフトウェアがバイトコードの実行を指示する*
        - インタプリタはJVM(Java Virtual Machine)という仕組みを内部に持っている
        - JVMがバイトコードを少しずつ読み込みながらマシン語に変換してCPUに送る

- 必要なもの
    - コンパイラ
    - インタプリタ

- ソースファイルの名前
    - クラス名.java にする必要あり

```
# Main.java

public class Main {

    // ここからがmainメソッド
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}}

```

- 変数の利用

```
public class Main {
    public static void main(String[] args) {
        // 変数宣言
        int age;
        age = 30;
        System.out.println(age); // 30
    }
}

```

- 変数の実体はコンピュータのメモリにある区画
    - 変数に値を入れるとメモリに値が書き込まれる
    - Javaでは小文字で始める名前をつけることが望ましい

- long はメモリを8バイト使用するため代入可能な整数が莫大
- int は4バイト使用、21億までの整数を代入できる
- double は少数を代入できる
- charは一文字だけ代入できる整数
- charは '' ,Stringは "" でデータを記述

```
char gender; gender = '男';
String name; name = 'たかし';

```

- 上書きされたくない変数は final を加えて宣言する
    - コンパイルエラーとなる

```
public class Main {
    public static void main(String[] args) {
        final double TAX = 1.08;
        int fax = 5;
        System.out.println("5万から4万に値下げします");
        TAX = 4;
        System.out.println("FAXの新価格");
        System.out.println(fax * TAX + "万円");
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
	The final local variable TAX cannot be assigned. It must be blank and not using a compound assignment

	at Main.main(Main.java:6)
Main.java:6

```

- 確認

```
# 縦幅 3 横幅 5 の長方形の面積は 15
# と出力するプログラム

public class Main {
    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        int c = a * b;
        System.out.println("縦幅 3 横幅 5 の長方形の面積は " + c);
        // 縦幅 3 横幅 5 の長方形の面積は 15
    }
}

```

```
# 変数宣言
# 1. true
# 2. '鉄'
# 3. 3.14
# 4. "this is a pen"

boolean result = true;
char oneWord = '鉄';
double pi = 3.14;
String sentence = "this is a pen";

```

---

### 式と演算子

- エスケープシーケンス

```
public class Main {
    public static void main(String[] args) {
        System.out.println("二重引用句(\")"); // 二重引用句(")
    }
}

```

- Javaの型変換
    1. 自動型変換
        - 小さな型の値を大きな型に代入する時に限り、自動的に箱の方に変換される
        - 強制的な型変換を支持するキャスト演算子を使うことはできる
            - int age = (int) 3.2;

    2. 明示的な型変換
    3. 演算時の自動型変換


- 命令実行の文

```
# 画面に表示する
System.out.println(x);

# 改行せずに画面に表示する
System.out.print(s);

# 大きい方の数字を代入する
int m = Math.max(a, b);

public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int m = Math.max(a, b);
        System.out.println("比較して" +
                a + "と" + b + "で大きい方は " + m);
                // 比較して5と3で大きい方は 5
    }
}

# 文字列を数字に変換する
int n = Integer.parseInt(s);

public class Main {
    public static void main(String[] args) {
        String age = "31";
        int n = Integer.parseInt(age);
        System.out.println("あたなの来年の年齢は" + (n + 1) + "歳です");
        // あたなの来年の年齢は32歳です
    }
}

# 乱数を発生させる
import java.util.Random;
int r = new Random().nextInt(bound);

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int r = new java.util.Random().nextInt(90);
        System.out.println("乱数を発生させます: " + r);
        // 乱数を発生させます: 53
    }
}

# キーボードから1行の命令を受け取る
import java.util.Scanner;
String input = new Scanner(System.in).nextLine(); # 文字列
String input = new Scanner(System.in).nextInt(); # 整数

```