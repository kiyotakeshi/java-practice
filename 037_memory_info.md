### JVM制御とリフレクション

- JVMに対してプログラムが終了するように明示的に指示を与える

```
// 以下のコードはそのままでは使えない

public class Main {
    public static void main(String[] args) {
        /* 何かしらのデータを読み込む処理 */
        if (/* データが正しくないなら */) {
            System.out.println("data is broken. program stop");

//          終了コードをエラー内容に応じて変えることでバッチ処理の異常終了時の原因究明ができる
            System.exit(1);
        }
        System.out.println("finish normary");
    }
}
```

---
- 外部プログラムの実行

```
// 計算処理が完了したらメモ帳を起動する
// 以下のはエラーになるが、参考までに

public class Main {
    public static void main(String[] args) {
        System.out.println("calculate now");

        System.out.println("finish, view with vscode");

        ProcessBuilder pb = new ProcessBuilder("/Applications/Visual Studio Code.app","result.txt");

        pb.start();
    }
}
```

---
- システムプロパティ
    - JVMの動作に関する様々情報を格納、利用しているJVM内部のMap<String, String> 情報のこと

```
import java.util.Iterator;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("Java version: ");
        System.out.println(System.getProperty("java.version"));

        Properties p = System.getProperties();

        Iterator<String> i = p.stringPropertyNames().iterator();

        System.out.println("System properties:");

        while (i.hasNext()) {
            String key = i.next();
            System.out.println(key + " = ");
            System.out.println(System.getProperty(key));
        }
    }
}

// 実行結果
Java version:
11.0.3
System properties:
gopherProxySet =
false
awt.toolkit =
sun.lwawt.macosx.LWCToolkit
java.specification.version =
11
```

- どのOSでも正しく改行されるようにする

```
final String BR = System.getProperty("line.separator");
System.out.println("today is" + BR + "sunny day");
```

---
- メモリに関する情報の取得
    - javaコマンドでJVMが起動する際に、メモリの一部がJVMのために割り当てられる
    - 変数を宣言、インスタンスの生成、メソッドの呼び出しなどで利用可能なメモリは減っていく

- メモリの状態を把握する

```
// MB単位で残りメモリ容量を取得

long f = Runtime.getRuntime().freeMemory() / 1024 / 1024;

// maxMemory()についてはOSからの追加メモリ割り当ての限界値を示す
```

---
- 実行時型情報(RTTI:run-time type information)
    - ある型に関する様々な情報(持ってるフィールド、メソッドなど)
    - リフレクションAPIを利用してJVMに対して型情報を調べるように指示する

- Classクラスのインスタンスを戻り値として返す

```
public class Main {
    public static void main(String[] args) {

//      Stringに関する情報を取得して表示
        Class<?> info1 = String.class;

//      クラス名部分のみを取得
        System.out.println(info1.getSimpleName()); // String

//      FQCNを取得
        System.out.println(info1.getName()); // java.lang.String

//      所属するパッケージ情報を返す
        System.out.println(info1.getPackage().getName()); // java.lang

//      配列であるか
        System.out.println(info1.isArray()); // false

//      Stringの親クラスの情報を取得
        Class<?> info2 = info1.getSuperclass();
        System.out.println(info2.getName()); // java.lang.Object

//      argsは文字列配列として判定される
        Class<?> info3 = args.getClass();
        System.out.println(info3.isArray()); // true
    }
}
```

- リフレクションを用いたメンバの取得と操作

```
public class RefSample {

    public int times = 0;

    public RefSample(int t) {
        this.times = t;
    }

    public void hello(String msg) {
        this.hello(msg, this.times);
    }

    public void hello(String msg, int t) {
        System.out.println("hello, " + msg + " x" + t);
    }
}
```

```
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws Exception {

//      RefSampleクラスの情報の取得
        Class clazz = RefSample.class;

//      引数1つのコンストラクタを取得し、インスタンスを生成
        Constructor<?> cons = clazz.getConstructor(int.class);
        RefSample rs = (RefSample) cons.newInstance(256);

//      timesフィールドに関するFieldを取得して読み書き
        Field f = clazz.getField("times");
        f.set(rs, 2);
        System.out.println(f.get(rs));

//      引数2つのhelloメソッドを取得し、呼び出す
        Method m = clazz.getMethod("hello", String.class, int.class);
        m.invoke(rs, "reflection!", 128);

//      クラスやメソッドへの修飾(public,finalの有無)を調べる
        boolean pubc = Modifier.isPublic(clazz.getModifiers());
        boolean finm = Modifier.isFinal(m.getModifiers());
    }
}
```

- メソッドを呼び出したほうがシンプル、あまり用途は多くない
    - テスト解析のためにprivateメンバを操作したい場合
    - 利用するクラスを動的に追加、変更できるようにしたい場合
        - 対戦相手となるコンピュータのアルゴリズムが書き込まれたクラスのFQCNをコマンドライン引数で受け取る

---
### 確認

```
public class MemoryEater {
    public static void main(String[] args) {
        System.out.println("eating memory...");

//      要素数1280000のlong型配列を確保する処理
        long[] larray = new long[1280000];

        for (int i = 0; i < larray.length; i++) {
            larray[i] = i;
        }
    }
}
```

```