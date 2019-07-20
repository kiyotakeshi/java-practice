### 様々なクラス

- 型安全
    - 型は格納するデータに制約をかける仕組み

- Object型だけを利用し、型のないJavaプログラミングをしてみる

```
public class Main {

//  printメソッド
//  第一引数の文字列を第二引数の回数だけ表示
    public static void prints(Object a, Object b) {
        for (int i = 0; i < (Integer) b; i++) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {

//      s には文字列もインスタンスも格納可能
        Object s = "hello";
        s = new Hero();

        Object n = 1;

//      便利そうだが、呼び出す時に神経を使う
//      引数の指定順があっているか、null型でないかなどがコンパイル時にエラーがでないためわからない
//      実行時エラーで初めてミスに気づくことになる
//      本番稼働からしばらくしてメソッドを動作させた時にうまくいかないとなると大惨事
        prints(s, n);
    }
}
```

- 型を用いる
    - **変数に予期しない種類の情報が入ってくることを未然に防ぐ**
    - コンパイラにミスを探させる

```
//  public static void prints(Object a, Object b) {
    public static void prints(String msg, int num) {
        for (int i = 0; i < (Integer) num; i++) {
            System.out.println(msg);
        }
    }
```

- コンパイル時に型を決定し、型安全を積極的に活用するのが「静的型付け言語(static typing)」

- 動的型付け言語は実行時エラーがでたり、予期しない変換が起きるが、手軽で柔軟に変数を利用できる

---
### ジェネリクス


- 実は汎用的にクラス作成時に使用できるテクニック
- コレクションクラスでは <> が使う
- 型を一つ指定できるという意味のジェネリクス(総称型、テンプレートとも)という仕組み

- ジェネリクスを使わないパターン
    - キャストを使うことになる

```
public class Pocket {

//  利用時に何型のインスタンスを入れるかわからないのでObject型で宣言
    private Object data;

    public void put(Object d){
        this.data = d;
    }

    public Object get(){
        return this.data;
    }
}

```

```
public class Main {
    public static void main(String[] args) {

        Pocket p = new Pocket();

//      文字列を格納
//      Object型変数に値を格納した習慣にStringインスタンスであったことは忘れ去られる
        p.put("1100");

//      取り出す際にキャストが必要
//      文字列以外のインスタンスを格納するミスに気づくのは実行時になる(ClassCastException)
        String s = (String) p.get();
        System.out.println(s);
    }
}
```

- ジェネリクスを活用

```
// Eは仮型引数(formal type parameter)と呼ばれる
// クラスで実際に使用する際に実型引数を指定する
public class Pocket<E> {
    private E data;

    public void put(E d){
        this.data = d;
    }

    public E get(){
        return this.data;
    }
}
```

- クラス利用時には宣言して利用する

```
// Eは仮型引数(formal type parameter)と呼ばれる
// クラスで実際に使用する際に実型引数を指定する
public class Pocket<String> {
    private String data;

    public void put(String d){
        this.data = d;
    }

    public String get(){
        return this.data;
    }
}
```

```
public class Main {
    public static void main(String[] args) {

//      Stringという実型引数を指定
        Pocket<String> p = new Pocket();

//      文字列を格納
        p.put("1100");

//      キャストは不要
        String s = p.get();
        System.out.println(s);
    }
}
```

---
### 列挙型

- 口座種別を指定するクラスの書き方
    - 3種類の型の指定のみを受け付けたい

```
//  列挙型で3種類の値だけ入れることができる型を定義
enum AccountType {
    FUTSU, TOUZA, TEIKI;
}
```

```
// 口座クラス
// usage: new Account("15000", AccountType.FUTSU);

public class Account {

    private String accountNo;
    private int balance;
    private AccountType accountType;

    public Account(String aNo, AccountType.FUTSU) {
    }
}
```

- static importにより列挙子やクラスメンバの記述の省略が可能

```
iimport static java.lang.System.*;

public class Main {
    public static void main(String[] args) {

       out.println("you don't need System.");
    }
}
```
