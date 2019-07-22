### メソッド

- 複数の文をまとめ、1つの処理として名前をつけたもの

- 1つのプログラムを複数の部品に分けてつくるために使用する

- すべてをmainメソッドに書くと修正箇所を探すのが大変に

- 機能単位でメソッドに分割して処理を担当

```
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

---
- main以外のメソッドからも呼び出せる

```
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