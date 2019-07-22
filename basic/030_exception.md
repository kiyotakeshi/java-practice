### 例外(exception)

- 想定外の事態や誤操作があってもエラーを起こさず正常に動作するプログラムをつくる

- Javaの不具合、エラーは3種類

1. 文法エラー(syntax error)
    - 変数名のあやまり
    - privateメソッドを外部から呼び出す

2. 実行時エラー(runtime error)
    - 異常事態が起きて、動作が継続されなくなる
    - コンパイルは成功し、実行できるがエラーメッセージがでて強制終了
    - 配列の適用範囲外へのアクセス、0での割り算、存在しないファイルのオープン

3. 論理エラー(logic error)
    - 強制終了もしない、実行結果が想定内容と違う

- 文法エラーと論理エラーは開発者の過失でテストを行いコードを修正すれば予防できる
- 実行時エラーは「想定外の事態(例外)が発生したことでおきる」

---
- 例外として起きることの例
    - 動作中にメモリが足りなくなる
    - 存在するべきファイルが見つからない
    - nullが入っている変数を利用しようとする

- 例外の発生を防ぐことは困難だが、例外の発生時にどう対処するかは用意できる
    - 対策を実施することを**例外処理(exception handling)**という

- Javaは例外処理と本来の処理を分けて書くことができる(例外処理用の文法と仕組みが備わっている)
    - try-catch文

- 現実世界に形がないものからオブジェクトを作ることもある
    - イベント情報プログラムの「イベント」や例外的状況をクラスにした「例外クラス」

---
- 例外の種類

1. Error系
    - 回復の見込みのない致命的な状況を表すクラス
    - キャッチしても打つ手がないのでキャッチしなくて良い
    - ex) OutOfMemoryError,ClassFormatError(クラスファイルが壊れている)

2. Exception系
    - 発生を十分に想定して対処を考える必要がある例外的状況を表すクラス
    - ex) IOExcption(ファイルの読み書きができない),ConnectError(ネットワークに接続できない)

3. RuntimeException系
    - 必ずしも常に発生を想定する必要もない(きりがない)例外的状況を表すクラス
    - ex) NullPointerException(変数がnull),ArrayIndexOutOfBoundsException(配列の添字が不正)

- Exception系例外が発生しそうな命令を呼び出す場合、try-catch文を用いて代替処理を用意しないとコンパイルエラーになる

---
### 例外処理を実装する

```
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // exception系例外は、コンパイル時に対策が用意されているかをチェックする
        // チェック例外と呼ばれる
        try {

            // FileWriterのコンストラクタは
            // IOExceptionを発生させることがある
            FileWriter fw = new FileWriter("data.txt");

        // tryブロック内で例外が発生した際に
        // JVMは処理をcatchブロックに移行し、
        // 例外が発生すると変数eに代入
        } catch (IOException e) {
            System.out.println("Error occur");
        }
    }
}

```

- どのメソッドを呼び出したらどの例外が発生するかはAPIリファレンスに記載されている

- 例外インスタンスが使用する主なメソッド
    - e.getMessage();
    - e.printStackTrace();

- スタックトレース
    - 「JVMがプログラムのメソッドをどのような順序で呼び出し、どこで例外が発生したかを記録したもの」
    - いつも実行時エラーが発生した時のエラー画面に出力されているもの

---
- ざっくりと例外をキャッチする

```
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // FileWriterのコンストラクタは
            // IOExceptionを発生させることがある
            FileWriter fw = new FileWriter("dat.txt");

        // ざっくりとキャッチ
        } catch (Exception e) {
            System.out.println("Error occur");
        }
    }
}

```
---
### finally

- tryブロックの実行を開始したら、例外発生の有無を問わずに必ず処理を実行する
    -「後片付け処理」にはfinallyを使う
    - ファイルを閉じる、データベースやネットワークとの接続を閉じるなど

```
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        FileWriter fw = null;

        try {

            // FileWriterのコンストラクタは
            // IOExceptionを発生させることがある
            fw = new FileWriter("dat.txt");
            fw.write("hello!")

        } catch (IOException e) {
            System.out.println("Error occur");
        } finally {
            fw.close();
        }
    }
}

```

---
### 例外の伝播

- 例外はキャッチされなければ、メソッドの呼び出し元まで処理をたらい回しにする

- Exception系例外(チェック例外)は try-catch によるキャッチが必須のため例外の伝播は起こらない
    - メソッド宣言時にスロー宣言を行うことでチェック例外を呼び出し元へ伝播できる
    - 呼び出し元で例外の処理をする必要がある

```
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    // スロー宣言することでチェック例外のtry-catchを無しにできる
    public static void subsub() throws IOException {
        FileWriter fw = new FileWriter("data.txt");
    }
}

```

---
### 例外を発生させる

- throwを使って例外インスタンスをJVMに投げつける

```
public class Person {
    int age;

    public void setAge(int age) {

        if (age < 0) {
            throw new IllegalArgumentException("age is over 0 number. now:" + age);
        }
        // 引数に問題がないのでフィールドに値をセット
        this.age = age;
    }
}

```

```
public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(-128);
    }

}

// エラーになる
// setAge()メソッドで発生した例外は呼び出し元のmainメソッドに伝播
// 例外をキャッチしていないのでJVMがプログラムを強制停止する

Exception in thread "main" java.lang.IllegalArgumentException: age is over 0 number. now:-128
	at Person.setAge(Person.java:7)
	at Main.main(Main.java:4)

Process finished with exit code 1

```

---

- 既存の例外クラスを継承してオリジナルの例外クラスを作成

```
public class UnsupportedMusicFileException extends Exception {

    // エラーメッセージを受け取るコンストラクタ
    public UnsupportedMusicFileException(String msg){
        super(msg);
    }
}

```

```
public class Main {
    public static void main(String[] args) {
        try {
            // インスタンスの生成と例外を発生させる
            throw new UnsupportedMusicFileException("not supported file");

            // 例外はeに代入される
        } catch (Exception e) {

            // 例外インスタンスのメソッドを呼び出す
            e.printStackTrace();
        }
    }

}

// 実行結果
UnsupportedMusicFileException: not supported file
	at Main.main(Main.java:5)

Process finished with exit code 0

```
---
### 例外処理の確認

- try-catch,StackTraceの確認

```
public class Main {
    public static void main(String[] args) {
        try {
            String s = null;
            System.out.println(s.length());

        } catch (NullPointerException e) {

            System.out.println("NullPointException catch");
            System.out.println("--- StackTrace (Start) ---");

            e.printStackTrace();

            System.out.println("--- StackTrace (End) ---");
        }
    }
}

// 出力結果

NullPointException catch
--- StackTrace (Start) ---
java.lang.NullPointerException
	at Main.main(Main.java:5)
--- StackTrace (End) ---

Process finished with exit code 0

```

- parseInt()メソッドの例外処理を記述

```
public class Main {
    public static void main(String[] args) {

        try {
            int i = Integer.parseInt("四");

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException catch");
        }
    }
}

// 実行
NumberFormatException catch

```

- 起動直後にIOExceptionを送出して異常終了するプログラムを作成

```
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Program is starting...");
        throw new IOException();
    }
}

// 出力結果
Program is starting...
Exception in thread "main" java.io.IOException
	at Main.main(Main.java:6)

Process finished with exit code 1

```