- メソッド
    - 複数の文をまとめ、1つの処理として名前をつけたもの
    - 1つのプログラムを複数の部品に分けてつくるために使用する
    - すべてをmainメソッドに書くと修正箇所を探すのが大変に

- 機能単位でメソッドに分割して処理を担当

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {
        System.out.println("Method call");

        // メソッドの呼び出し
        hello();

        System.out.println("Method is called");
    }

    // メソッドの本体
    public static void hello() {
        System.out.println("hello");
    }

    // 出力結果
    // Method call
    // hello
    // Method is called
}

```

- main以外のメソッドからも呼び出せる

```
package com.kiyota;

public class Main {

    public static void methodA() {
        System.out.println("methodA");
        methodB();
    }

    public static void methodB() {
        System.out.println("methodB");
    }

    public static void main(String[] args) {
        methodA();
    }

    // 出力結果
    // methodA
    // methodB

    // mainメソッドから呼ばれる
    // methodAが呼ばれる
    // methodBが呼ばれる
}

```

- 引数
    - メソッドを呼び出す際に、呼び出し元から値を渡される値のこと
    - 受け取った値を使って処理に使用することができる

- 一つの引数を渡す

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {
        System.out.println("Method call");
        hello("Mike");
        hello("John");
        hello("Hiroshi");
    }

    public static void hello(String name) {
        System.out.println("Hello " + name);
    }
    // 出力結果
    // Method call
    // Hello Mike
    // Hello John
    // Hello Hiroshi
}

```