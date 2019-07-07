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

---

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

---

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

---

- オーバーロード
    - 例外的に同じ名前のメソッドを定義すること(多重定義)
        - 仮引数の型が異なっている場合、個数が違う場合に可能

    - 基本はエラーになる
        - 似たような処理をするメソッドを複数作るときにメソッドに同じ名前をつけるとJVMはどれを実行したらいいかわからなくなるため

- 仮引数の型が異なる場合

```

package com.kiyota;

public class Main {
    public static void main(String[] args) {

        // 呼び出し元の引数(渡す値、実引数:10,20など)をみてその引数の型に一致するメソッドを呼び出す
        System.out.println(add(10, 20));
        System.out.println(add(3.5, 2.7));
        System.out.println(add("hello", "world"));

        // 出力結果
        // 30
        // 6.2
        // helloworld
    }

    public static int add(int x, int y) {
        return x + y;
    }

    // 仮引数の型が違うため同じ名前のメソッドを使用可能
    public static double add(double x, double y) {
        return x + y;
    }

    // 仮引数(受け取る変数:x,yのこと)の型が違うため同じ名前のメソッドを使用可能
    public static String add(String x, String y) {
        return x + y;
    }
}

```

- 引数の数が違う場合

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {

        // 仮引数の個数の違い(x,yかx,y,z)で呼び出すメソッドを区別
        System.out.println("10+20=" + add(10, 20));
        System.out.println("10+20+30=" + add(10, 20, 30));

    }

    public static int add(int x, int y) {
        return x + y;
    }

    public static int add(int x, int y, int z) {
        return x + y + z;
    }

}

```

- メソッド宣言で戻り値の型の後に記載する情報はまとめて、「メソッドのシグネチャ」と呼ぶ
    - メソッド名、引数の個数、型、並び順
    - シグネチャが重複しない場合、オーバーロードが可能

---

- 引数に配列を使う

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};

        // メソッドの実引数として配列を渡す
        // 渡しているのは配列まるごとではなく、アドレスの情報のみ(参照渡し)
        printArray(array);
    }

    // メソッドの仮引数(受け取る変数)で配列を指定
    // int[]のような配列型変数には配列の実体を指し示すメモリ番号が入っている
    public static void printArray(int[] array) {
        for (int element : array) {
            // 配列の要素を出力
            System.out.println(element);
        }

    }

}

```

- 参照渡し(厳密には「参照の値渡し」と呼ばれる)の確認

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};

        for (int i : array) {
            System.out.println(i);
            // 1
            // 2
            // 3
        }

        // メソッドが呼ばて値が1増える
        // 呼び出し先のメソッドでの変更が呼び出し元に影響を与える
        incArray(array);

        // arrayの全要素を出力
        for (int i : array) {
            System.out.println(i);
            // 2
            // 3
            // 4
        }
    }

    // 配列の要素に1を加えるメソッド
    public static void incArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i]++;
        }
    }


}

```

---

- 戻り値に配列を使う

```
package com.kiyota;

public class Main {
    public static void main(String[] args) {

        // makeArrayメソッドを実引数(渡す値)3で呼び出して配列arrayに入れる
        int[] array = makeArray(3);

        // 配列の中身を出力
        for (int i : array) {
            System.out.println(i);
            // 0
            // 1
            // 2
        }
    }


    // int型配列を作成して戻すメソッド
    // 配列を戻すからstaticの後は int[]
    public static int[] makeArray(int size) {
        int[] newArray = new int[size];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = i;
        }

        // 配列を戻す
        return newArray;
    }


}

```

---

-