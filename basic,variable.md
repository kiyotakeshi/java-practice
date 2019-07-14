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
    - javacコマンドでコンパイルすると .classファイルになる(このファイルが実行可能)

```
// Main.java

public class Main {

    // ここからがmainメソッド
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}}

```

---
### 変数の利用

```
public class Main {
    public static void main(String[] args) {
        // 変数宣言(型を宣言して、代入)
        int age;
        age = 30;
        System.out.println(age); // 30
    }
}

```

- 変数の実体はコンピュータのメモリにある区画
    - 変数に値を入れるとメモリに値が書き込まれる

- Javaでは小文字で始める名前をつけることが望ましい

- 様々な型
    - long はメモリを8バイト使用するため代入可能な整数が莫大
    - int は4バイト使用、21億までの整数を代入できる
    - double は少数を代入できる
    - charは一文字だけ代入できる
    - charは '' ,Stringは "" でデータを記述

```
char gender; gender = 'M';
String name; name = "Mike";

```

- 上書きされたくない変数は final を加えて宣言する
    - 上書きしようとするとコンパイルエラーとなる

```
public class Main {
    public static void main(String[] args) {

        final double TAX = 1.08;
        int fax = 500;
        System.out.println("Price down! 500\$ -> 400\$ ");

        // faxを値下げするはずが、間違えて税率を上書きしてしまった
        TAX = 4;
        System.out.println("New FAX price");
        System.out.println(fax * TAX + "\$");
    }
}

Exception in thread "main" java.lang.Error: Unresolved compilation problem:
	The final local variable TAX cannot be assigned. It must be blank and not using a compound assignment

	at Main.main(Main.java:6)
Main.java:6

```

---

- 縦幅 3 横幅 5 の長方形の面積は 15 と出力するプログラム

```
public class Main {
    public static void main(String[] args) {
        int a = 3;
        int b = 5;
        int c = a * b;
        System.out.println("Squere area \(height:3 width:5\): " + c);
    }
}

```

- 変数宣言の確認

```
// 1. true
// 2. 'F'
// 3. 3.14
// 4. "this is a pen"

boolean result = true;
char oneWord = 'F';
double pi = 3.14;
String sentence = "this is a pen";

```