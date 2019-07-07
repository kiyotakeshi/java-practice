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

- 複数の引数を渡す
    - メソッド側で宣言する変数の型と引数として渡される値の順番を合わせる必要がある
    - 渡す値、受け取る変数も共に引数と呼ばれる
        - 呼び分ける場合、渡す値のことを実引数、受け取る変数のことを仮引数とよぶ

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {

        // 100,200は渡す値、実引数
        add(100, 200);
        add(200, 300);
    }

    // x,yは受け取る変数,仮引数
    // x,yはメソッド内で宣言したローカル変数
    public static void add(int x, int y) {
        int ans = x + y;
        System.out.println(x + " + " + y + " = " + ans);
    }
    // 出力結果
    // 100 + 200 = 300
    // 200 + 300 = 500
}

```

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {

        // こんな書き方もできる
        int x = 100;
        int y = 200;
        add(x, y);

        x = 200;
        y = 300;
        add(x, y);

    }

    public static void add(int x, int y) {
        int ans = x + y;
        System.out.println(x + " + " + y + " = " + ans);
    }
    // 出力結果
    // 100 + 200 = 300
    // 200 + 300 = 500
}

```

- 戻り値
    - 呼び出されたメソッドから呼び出し元のメソッドへ値を返すことを値を戻す(返す)という
    - 戻される値のことを戻り値(返り値)と呼ぶ

- メソッドには戻り値の型を記載する

```
public static returnType methodName(arguments){
    system.out.prinln("");
    return returnData;
}

# 何も戻さない場合はvoid
public static void main(String[] args) {
}

```

- 戻り値を使った書き方

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {

    // addメソッドの呼び出し(110が返ってくる)
    int ans = add(100, 10);

    System.out.println("100 + 10 = " + ans);
    // 100 + 10 = 110
    }

    public static int add(int x, int y) {
        int ans = x + y;
        return ans;
    }

}

```

- 戻り値を変数で受けずにそのまま使う

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {

        // メソッドの戻り値を変数で受けずに使用する(極端な例)
        // add(10,20),add(30,40)が実行される
        // 外側のaddメソッドがadd(30,70)になる
        // 画面には100が出力される
        System.out.println(add(add(10, 20), add(30, 40)));
    }

    public static int add(int x, int y) {
        int ans = x + y;

        // returnはメソッドの終了も行う
        return ans;

        // returnの後に処理を書いてもコンパイルエラーになる
        // int x = 10;
    }

}

```

- 