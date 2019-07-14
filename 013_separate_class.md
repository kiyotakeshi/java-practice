### ソースファイルの分割(Classの分割)

- mainメソッドを複数のメソッドに分割するだけでは1つのソースファイルが肥大化して見通しが悪くなる

- ソールファイルを分けて開発することは、複数のクラスに分けて開発すること
    - ソースファイルには1つ以上のクラスが必要なため

- 1つのプログラムを複数の部品に分けることを「部品化」という

---
- 計算機プログラムを分割

```
// 1つのプログラム

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
// CalcLogic.java

public class CalcLogic {
    public static int tasu(int a, int b) {
        return (a + b);
    }

    public static int hiku(int a, int b) {
        return (a - b);
    }
}

// Calc.java

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
$ ls
Calc.java       CalcLogic.java

$ javac Calc.java CalcLogic.java

$ ls
Calc.class      Calc.java       CalcLogic.class CalcLogic.java

$ java Calc
add result:12
delta result:8

```

- Javaプログラムの完成品は複数のクラスファイルの集合体(すべてのクラスファイルが必要)
    - JAR(Java ARchive)形式で一つにまとめる(jarコマンドが使える)

- 渡された複数のクラスファイルのうちmainメソッドが含まれているクラスの名前を指定する必要がある
    - 今回だと `java Calc` で実行

