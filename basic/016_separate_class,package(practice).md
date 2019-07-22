### クラスファイルの分割の練習

- 以下のプログラムを分割する

1. Mainクラスにはmainメソッドだけ,クラスの先頭でZenhanクラスだけをインポート
2. commentパッケージに属するZenhanクラスを作成
3. commentパッケージに属するkouhanクラスを作成

```
public class Main {
    public static void main(String[] args) throws Exception {

        doWarusa();
        doTogame();
        talkBack();
        showPoliceNotebook();

    }

    public static void doWarusa() {
        System.out.println("A: do mischief");
    }

    public static void doTogame() {
        System.out.println("B: blame it");
    }

    public static void talkBack() {
        System.out.println("A: talk back");
    }

    public static void showPoliceNotebook() throws Exception {
        System.out.println("B: show police notebook");
        doTogame();
    }
}

// 出力結果
// $ java Main
// A: do mischief
// B: blame it
// A: talk back
// B: show police notebook
// B: blame it

```

- 分割してみる

```
// Main.java

import comment.Zenhan;

public class Main {
    public static void main(String[] args) throws Exception {

        Zenhan.doWarusa();
        Zenhan.doTogame();
        comment.Kouhan.talkBack();
        comment.Kouhan.showPoliceNotebook();
    }
}

```

```
// Zenhan.java

package comment;

public class Zenhan {

    public static void doWarusa() {
        System.out.println("A: do mischief");
    }

    public static void doTogame() {
        System.out.println("B: blame it");
    }
}

```

```
// Kouhan.java

package comment;

public class Kouhan {

    public static void talkBack() {
        System.out.println("A: talk back");
    }

    public static void showPoliceNotebook() throws Exception {
        System.out.println("B: show police notebook");
        comment.Zenhan.doTogame();
    }
}

```

- 実行

```
$ javac Main.java Zenhan.java Kouhan.java

$ ls
Kouhan.class    Kouhan.java     Main.class      Main.java       Zenhan.class    Zenhan.java

$ pwd
/Users/kiyotatakeshi/Desktop/Java/classPractice/src

$ mkdir -p work/commet

$ mv Zenhan.class Kouhan.class work/commet/
$ mv Main.class work/

$ tree -L 3 work/
work/
├── Main.class
└── commet
    ├── Kouhan.class
    └── Zenhan.class

1 directory, 3 files
$ cd work/

kiyota-MacBook-Pro:work kiyotatakeshi$ pwd
/Users/kiyotatakeshi/Desktop/Java/classPractice/src/work

kiyota-MacBook-Pro:work kiyotatakeshi$ ls
Main.class      comment

kiyota-MacBook-Pro:work kiyotatakeshi$ tree
.
├── Main.class
└── comment
    ├── Kouhan.class
    └── Zenhan.class

1 directory, 3 files

kiyota-MacBook-Pro:work kiyotatakeshi$ java Main
A: do mischief
B: blame it
A: talk back
B: show police notebook
B: blame it

```

- showPoliceNotebook に3秒待ち時間を入れる

```
    public static void showPoliceNotebook() throws Exception {
        System.out.println("B: show police notebook");

        // java.lang.Threadクラスのメソッドを呼び出す
        Thread.sleep(3000);
        comment.Zenhan.doTogame();
    }

```

- javacコマンドは「どのソースファイルをコンパイルするか」をファイル名で指定
- javaコマンドは「どのクラスのmainメソッドを起動するか」をクラス名(FQCN)で指定