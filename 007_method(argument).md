### 引数

- メソッドを呼び出す際に、呼び出し元から値を渡される値のこと
    - 受け取った値を使って処理に使用することができる

- 1つの引数を渡す

```
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

---
- 複数の引数を渡す
    - メソッド側で宣言する変数の型と引数として渡される値の順番を合わせる必要がある
    - 渡す値、受け取る変数も共に引数と呼ばれる
        - 呼び分ける場合、渡す値のことを実引数、受け取る変数のことを仮引数とよぶ

```
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