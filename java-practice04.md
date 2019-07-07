- JDKを用いた開発の流れ

1. ソースコードを作成(クラス名がMainなら Main.java)

2. javacコマンドでJavaコンパイラを起動

3. コンパイラがソースファイルを実行可能なクラスファイル(class file)に変換
    - クラスファイルはバイトコードなどからなる

4. javaコマンドでインタプリタを起動

5. クラスファイルの中身がJVMに読み込まれ実行

- javacコマンドで出力されるバイトコードは汎用的なマシン語
    - 特定のCPUに依存しないためパソコンでもスパコンでも動く

- それぞれのインタプリタによってバイトコードを実行環境に合わせたマシン語に変換して実行される

- 汎用的なマシン語(バイトコード)を理解するコンピュータが実行しているように見えるためJVM(Java Virtual Machine)と呼ばれる

- Javaプログラムを実行してみる

- ソースコードの確認
```
kiyota-MacBook-Pro:java kiyotatakeshi$ ls
Main.java               SpeedConverter.java

kiyota-MacBook-Pro:java kiyotatakeshi$ cat Main.java
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {

        // 別クラスのメソッドを呼び出している
        long miles = SpeedConverter.toMilesPerHour(10.5);
        System.out.println("Miles = " + miles);

        // 別クラスのメソッドを呼び出している
        SpeedConverter.printConversion(10.5);
    }
}

kiyota-MacBook-Pro:java kiyotatakeshi$ cat SpeedConverter.java
/**
 * SpeedConverter
 */
public class SpeedConverter {

    public static long toMilesPerHour(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            return -1;
        }

        return Math.round(kilometersPerHour / 1.609);
    }

    public static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            System.out.println("Invalid Value");
        } else {
            long milesPerHour = toMilesPerHour(kilometersPerHour);
            System.out.println(kilometersPerHour +
            " km/h = " + milesPerHour +
            "mi/h");
        }
    }
}

```

- コンパイル、確認

```
kiyota-MacBook-Pro:java kiyotatakeshi$ javac Main.java

kiyota-MacBook-Pro:java kiyotatakeshi$ ls
Main.class              SpeedConverter.class
Main.java               SpeedConverter.java

```

- プログラムの実行(.classを取り除いたものを指定)

```
kiyota-MacBook-Pro:java kiyotatakeshi$ java Main
Miles = 7
10.5 km/h = 7mi/h

```

---

- ソースファイルの分割(Classの分割)

    - mainメソッドを複数のメソッドに分割するだけでは1つのソースファイルが肥大化して見通しが悪くなる

    - ソールファイルを分けて開発することは、複数のクラスに分けて開発すること
        - ソースファイルには1つ以上のクラスが必要なため

    - 1つのプログラムを複数の部品に分けることを「部品化」という

---

- 計算機プログラムを分割

```
# 1つのプログラム

package com.kiyota;

public class Main {

    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        int total = tasu(a, b);
        int delta = hiku(a, b);
        System.out.println("add result:" + total);
        System.out.println("delta result:" + delta);
    }

    public static int tasu(int a, int b) {
        return (a + b);
    }

    public static int hiku(int a, int b) {
        return (a - b);
    }
}

```

- 計算処理をするメソッドと画面に表示するメソッドに分割する

1. 計算処理メソッドを記述するためのソースファイルを作る(CalcLogic.java)
2. tasu() と hiku() を移動する

```
# CalcLogic.java

package com.kiyota;

public class CalcLogic {
    public static int tasu(int a, int b) {
        return (a + b);
    }

    public static int hiku(int a, int b) {
        return (a - b);
    }
}


# Calc.java
package com.kiyota;

public class Calc {

    public static void main(String[] args) {
        int a = 10;
        int b = 2;

        // このソースファイルには存在しないメソッドのためコンパイルエラーになる
        // 同じクラスに属していないため CalcLogic のメソッドと指定する必要がある
        // int total = tasu(a, b);
        // int delta = hiku(a, b);

        int total = CalcLogic.tasu(a, b);
        int delta = CalcLogic.hiku(a, b);
        System.out.println("add result:" + total);
        System.out.println("delta result:" + delta);
    }
}

```

---

- 複数クラスのコンパイル
    - 完成品としてはこの2つのクラスファイルが必要
    - IDEで作るときは雛形を使用するとMain.javaが生成されてうまくいかないことも

```
kiyota-MacBook-Pro:src kiyotatakeshi$ ls
Calc.java       CalcLogic.java

kiyota-MacBook-Pro:src kiyotatakeshi$ javac Calc.java CalcLogic.java

kiyota-MacBook-Pro:src kiyotatakeshi$ ls
Calc.class      Calc.java       CalcLogic.class CalcLogic.java

kiyota-MacBook-Pro:src kiyotatakeshi$ java Calc
add result:12
delta result:8

```

- Javaプログラムの完成品は複数のクラスファイルの集合体(すべてのクラスファイルが必要)
    - JAR(Java ARchive)形式で一つにまとめる(jarコマンドが使える)

- 渡された複数のクラスファイルのうちmainメソッドが含まれているクラスの名前を指定する必要がある
    - 今回だと `java Calc` で実行

- クラスが増えすぎるとパッケージというグループで分類、管理する
    - パッケージに親子関係や階層関係はない

- 以下のように書く(今の段階ではコンパイルまでしかできない)

```
# Calc.java

package calcapp.main;

public class Calc {

    public static void main(String[] args) {
        int a = 10;
        int b = 2;

        // 所属パッケージ名を添えたクラス名を指定
        // あるクラスから別のパッケージのクラス名を使用する場合、完全限定クラス名(FQCN)が必要
        int total = calcapp.logics.CalcLogic.tasu(a, b);
        int delta = calcapp.logics.CalcLogic.hiku(a, b);
        System.out.println("add result:" + total);
        System.out.println("delta result:" + delta);
    }
}

# CalcLogic.java

package calcapp.logic;

public class CalcLogic {
    public static int tasu(int a, int b) {
        return (a + b);
    }

    public static int hiku(int a, int b) {
        return (a - b);
    }
}

```

- パッケージを使うことで自分が作るクラスに対して開発者が自由な名前がつけられる
    - 大規模なプロジェクトで偶然同じクラス名を使用してしまっても大丈夫
    - 名前の衝突を避け、限られた名前空間を区別して使えるようにできる
    - パッケージ名の衝突についてはインターネットドメインをパッケージ名に使うことで衝突を回避できる

- FQCNの入力の負担はimport文で軽減できる
    - あくまでも入力の負担を軽減するもの

```
package calcapp.main;
import calcapp.logics.CalcLogic;

public class Calc {

    public static void main(String[] args) {
        int a = 10;
        int b = 2;

        // FQCNで指定してもしなくてもよくなる
        int total = tasu(a, b);
        int delta = calcapp.logics.CalcLogic.hiku(a, b);

        System.out.println("add result:" + total);
        System.out.println("delta result:" + delta);
    }
}

```

---

- 作成したクラスは様々なプログラムと連携して動く
    - 標準搭載されているクラス(API)は200パッケージ、3500以上のクラスがある

```
# JVMに組み込まれたクラスの完全限定クラス名を出力
# 300件以上のプログラムと連携して動いていることがわかる

kiyota-MacBook-Pro:Calc kiyotatakeshi$ java -verbose:class HelloWorld

```

- APIが組み込まれているおかげで命令部分を作る必要がない
    - arrays.classなどの形でJDKをインストールした際にコピーされている

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {
        int[] heights = {172, 180, 163, 157};

        // APIを使用することで自動で並び替える
        // APIは専門家がつくったもので高速かつバグが少ない
        java.util.Arrays.sort(heights);

        for(int h:heights){
            System.out.println(h);
        }
    }
}

```

- APIに含まれるパッケージ

    - java.lang
        - 重要クラス群

    - java.util
        - プログラミングを便利にするクラス群

    - java.math
        - 数学に関するパッケージ

    - java.net
        - ネットワークに関するもの

    - java.io
        - データを逐次処理するためのもの

- System.out.println() は java.lang.System

---
