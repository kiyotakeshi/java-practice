### パッケージ

- クラスが増えすぎるとパッケージというグループで分類、管理する
    - パッケージに親子関係や階層関係はない

- 以下のように書く(今の段階ではコンパイルまでしかできない)

```
// Calc.java

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

```

```
// CalcLogic.java

package calcapp.logics;

public class CalcLogic {
    public static int tasu(int a, int b) {
        return (a + b);
    }

    public static int hiku(int a, int b) {
        return (a - b);
    }
}

```

- コンパイルする
    - 今の段階では実行には失敗

```
$ javac Calc.java CalcLogic.java

$ ls
Calc.class      Calc.java       CalcLogic.class CalcLogic.java

$ java Calc
エラー: メイン・クラスCalcを検出およびロードできませんでした
原因: java.lang.NoClassDefFoundError: calcapp/main/Calc (wrong name: Calc)

```

---
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
        int total = CalcLogic.tasu(a, b);
        int delta = calcapp.logics.CalcLogic.hiku(a, b);

        System.out.println("add result:" + total);
        System.out.println("delta result:" + delta);
    }
}

```

---
### パッケージとクラス

- コンパイルはできるがエラーで実行できない
    - `NoClassDefFoundError`、つまりクラス定義が見つからない状態

```
$ javac Calc.java CalcLogic.java

$ ls
Calc.class      Calc.java       CalcLogic.class CalcLogic.java

$ java Calc
エラー: メイン・クラスCalcを検出およびロードできませんでした
原因: java.lang.NoClassDefFoundError: calcapp/main/Calc (wrong name: Calc)

```

- 起動しようとしているクラスの指定が間違っている
    - `java 起動したいクラスのFQCN`でコマンドを実行
    - java Calc だとデフォルトパッケージにあるCalcクラスを実行しようとする

```
// まだエラー
$ java calcapp.main.Calc
エラー: メイン・クラスcalcapp.main.Calcを検出およびロードできませんでした
原因: java.lang.ClassNotFoundException: calcapp.main.Calc

```

- クラスローダーが目的のクラスファイルを探し出せていない
    - クラスパスで指定されたフォルダを対象に探す
    - 現在のクラスパスを基準に、パッケージ階層に対応したフォルダをつくり、クラスファイルを配置する

---
- クラスパス(work)を基準として配置

```
$ pwd
/Users/kiyotatakeshi/Desktop/Java/Calc/src

# ディレクトリの作成
$ mkdir -p ./work/calcapp/main
$ mkdir ./work/calcapp/logics

# クラスファイルの移動
$ mv Calc.class work/calcapp/main/
$ mv CalcLogic.class work/calcapp/logics/

$ tree -L 4 work/
work/
└── calcapp
    ├── logics
    │   └── CalcLogic.class
    └── main
        └── Calc.class

3 directories, 2 files

```

- 実行

```
kiyota-MacBook-Pro:work kiyotatakeshi$ java calcapp.main.Calc
add result:12
delta result:8

```

- IDE(Integrated Development Environment)
    - テキストエディタ、コンパイラ(javac)、インタプリタ(java)全てを内蔵したもの

- エディタでコードを書いたり上書きすると自動的にコンパイルされる