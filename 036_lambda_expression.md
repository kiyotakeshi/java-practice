### ラムダ式

- 第1級オブジェクト
    - プログラムの実行中に生み出したり、変数に代入したりできるもの
    - データ、データ構造、インスタンス
    - Java8からfunction(関数)も加わった

- 関数は何かの入力(input)を受け取り、処理(process)を行い、出力(output)を返すもの

- メソッドと関数の違い
    - 以下のは関数ともメソッドともいえる
    - メソッドはクラスに属する一種の関数
    - 関数にとっては名前は重要でない(メソッドでは名前は必須)
        - 料理の手順を書いた名もなきメモ(レシピ名はない)のイメージ

```
public class Main {

    public static void main(String[] args) {

        int num = twice(3);
        System.out.println(num);

        int sub = sub(5, 2);
        System.out.println(sub);
    }

//  これはメソッドだが、条件を満たしているので関数
    public static int twice(int x) {
        return x * 2;
    }

    public static int sub(int a, int b) {
        return a - b;
    }
}
```

- 変数に代入できる第1級オブジェクトとして扱われる関数を**関数オブジェクト**と呼ぶ

```
import java.util.function.IntBinaryOperator;

public class Main {

    public static void main(String[] args) {

//      変数funcに代入しているのはsub()メソッドへの参照
//      JVMのメモリ空間の中のsub()メソッドの実体のメモリ番地を指す情報が入っている
        IntBinaryOperator func = Main::sub;

        int a = func.applyAsInt(5, 3);
        System.out.println("5-3: " + a);
    }

    public static int sub(int a, int b) {
        return a - b;
    }

}
```

- SAMインターフェースは抽象メソッドを一つしか持たないインターフェース
    - 格納したい関数オブジェクトと同じ引数や戻り値を持ったメソッドを1つだけ含んだインターフェースを宣言し使用する

```
// MyFunction型を使えるようにするために
// SAMインターフェースを定義する
public interface MyFunction {

//  格納したい関数(sub)と同じ、引数と戻り値の抽象メソッドを定義
    public abstract int call(int x, int y);
}

```

```
public class Main {

    public static void main(String[] args) {

//      宣言したインターフェースの型に代入
        MyFunction func = Main::sub;

//      インターフェースのメソッドで呼び出し
        int a = func.call(5, 3);
        System.out.println("5-3: " + a);
    }

    public static int sub(int a, int b) {
        return a - b;
    }

}
```

- メソッドの実体はそのメソッドを含むクラスがJVMに読み込まれた時点でメモリ中に生み出される

- 関数は必要になった時にその場で生み出すことが可能(newでインスタンスを生み出せるように)
    - 事前にメソッドとして宣言しておく必要がない
    - プログラム実行中の必要なタイミングで生み出して即時利用できる

```
import java.util.function.IntBinaryOperator;

public class Main {

    public static void main(String[] args) {

//      sub()メソッドとしては宣言せず、mainメソッドの動作中に生成して利用
        IntBinaryOperator func = (int a, int b) -> {
            return a - b;
        };

        int a = func.applyAsInt(5, 3);

        System.out.println("5-3: " + aj);
    }

}
```

- IntBinaryOperatorなどは開発者がいちいちSAMインターフェースを定義する手間を省くために用意
- 関数を引数として受け取る関数を「高階関数(higher-order function)」とよぶ

---
- ラムダの省略記法

```
import java.util.function.IntToDoubleFunction;

public class Main {

    public static void main(String[] args) {

        IntToDoubleFunction func = (int x) -> {
            return x * x * 3.14;
        };

        double a = func.applyAsDouble(1);
        System.out.println(a);
    }
}

```

```
// 引数宣言の型を省略可能
// 代入先の変数の型(この場合、抽象メソッドapplyAsDoubleの型)が採用される
IntoDoubleFunction func = (x) ->{

// 引数が一つなら()も省略可能
// IntoDoubleFunction func = x ->{

            return x * x * 3.14;
}

```

```
// 単一のreturn分の場合、以下のように記載可能
IntoDoubleFunction func = x -> x * x * 3.14;
```
