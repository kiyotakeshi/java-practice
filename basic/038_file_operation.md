### ファイルの操作

- FileWriterを使ってみる

```
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

//      FileWriterのインスタンス化(ファイルが開けるようになる)
        FileWriter fw = new FileWriter("/Users/kiyotatakeshi/Desktop/Java/Exception/test.txt", true);
        fw.write("\ntest");

//      flush()メソッドを呼び出すことでファイルに正しくデータが書き込まれる
//      バッファにある書き込み内容を書き込むことをJVMに命令している
        fw.flush();
        fw.close();
    }
}

// 実行した後にファイルができている
testkiyota-MacBook-Pro:Exception kiyotatakeshi$ cat test.txt
test
```

- 読み込み

```
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        FileReader fw = new FileReader("/Users/kiyotatakeshi/Desktop/Java/Exception/test.txt");

        System.out.println("file contents:");

//      1文字読む
        int i = fw.read();

//      これ以上読めるデータがない場合は -1
//      char型だと -1 が表現できないためint型で返している
        while (i != -1) {
            char c = (char) i;
            System.out.println(c);

//          read()では1文字ずつ読み込まれる
            i = fw.read();
        }

        System.out.println("file contents end");
        fw.close();
    }
}

// 実行結果
file contents:
t
e
s
t


file contents end
```

---
### closeを忘れない

- IOExceptionなどが発生し、close()が実行されないことも
- 必ずclose()されることを保証する必要がある
- try-finally で確実にキャッチする

```
// これは以前使われていた書き方で今はもっと簡潔に書ける

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

//      tryブロックの外で宣言しnullで初期化しないとfinallyブロック内でclose()を呼べない
        FileWriter fw = null;

        try {
//          append:trueのため追記される
            fw = new FileWriter("/Users/kiyotatakeshi/Desktop/Java/Exception/test.txt", true);
            fw.write("try-finally");
            fw.flush();

        } catch (IOException e) {
            System.out.println("file write error");

//j      ファイルを閉じるための処理
        } finally {
            if (fw != null) {

//              close()がIOExceptionを出す可能性があるためtry-catchにしているが失敗しても特にできることはない
                try {
                    fw.close();
                } catch (IOException e2) {
                }
            }
        }
    }
}
```

---
### ストリーム

- データを一気に読み込むと巨大なファイルだとメモリ不足に
- JVMの外の世界のファイルやネットワーク上にはサイズのわからないデータだらけ
    - シーケンシャルに少しづつ処理するのが基本的なアプローチ
    - ストリームという概念で捉えられる
    - データの読み書きを小川として抽象的に捉えることで文字の表示を同じアプローチで実現することができる
        - ネットワークサーバが繋がっている小川を使う,通信
        - キーボードが繋がっている小川を使う,キー入力
        - ディスプレイが繋がっている小川を使う,画面への文字の表示

- 標準出力(stdout),標準エラー出力(stderr),標準入力(stdin)はJVMが起動直後から提供するストリーム

```
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws IOException {

        String msg = "first Saturday";

        // StringReaderはコンストラクタで指定した文字列を1文字づつ読み取る
        Reader sr = new StringReader(msg);

        char c1 = (char) sr.read();
        System.out.println(c1);
        char c2 = (char) sr.read();
        System.out.println(c2);

    }
}
```

---
- フィルタ
    - ストリームの途中に変換処理をする部品のインスタンス

- バッファリングフィルタはデータの変換は行わないが、データを溜め込みまとまった量になった際に下流に送る
    - SSDへの読み書き要求に時間がかかるためその頻度を下げる

- フィルタはFilterReader,FilterWriter,FilterInputStream,FilterOutputStreamのいずれかのクラスを継承

```
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

//      closeがよばれるtry-catch構文
        try (FileReader fr = new FileReader("/Users/kiyotatakeshi/Desktop/Java/Exception/test.txt");) {

//          BufferReaderがファイルのデータを内部に溜め込むため
//          readLine()メソッドの利用が可能になる
            BufferedReader br = new BufferedReader(fr);
            String line = null;

            while ((line = br.readLine()) !=null){
                System.out.println(line); // try-finally
            }

        } catch (IOException e) {
            System.out.println("file write error");
        }
    }
}
```

---
### 確認

- 起動パラメータに指定したファイルをコピーするプログラム

```
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {

        String inFile = args[0];
        String outFile = args[1];

//      ストリームを接続先としてコンストラクタで指定して生成
        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);

        int i = fis.read();

        while (i != -1){
//          読めるデータがある場合、コピー先に書き込む
            fos.write(i); i = fis.read();
        }

        fos.flush();
        fos.close();
        fis.close();

    }
}

// 使ってみる
kiyota-MacBook-Pro:src kiyotatakeshi$ touch copy01.txt
kiyota-MacBook-Pro:src kiyotatakeshi$ java Main copy01.txt copy02.txt
kiyota-MacBook-Pro:src kiyotatakeshi$ ls
Main.class      Main.java       copy01.txt      copy02.txt

```

- 圧縮機能を追加

```
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Main {
    public static void main(String[] args) {

        String inFile = args[0];
        String outFile = args[1];

//      tryブロックの外で宣言しnullで初期化しないとfinallyブロック内でclose()を呼べない
        FileInputStream fis = null;
        GZIPOutputStream gzos = null;

        try {
            fis = new FileInputStream(inFile);
            FileOutputStream fos = new FileOutputStream(outFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            gzos = new GZIPOutputStream(bos);

//          ファイルに内容があれば
            int i = fis.read();
            while (i != -1) {

//              圧縮して書き込み
                gzos.write(i);
                i = fis.read();
            }

            gzos.flush();
            gzos.close();

            fis.close();
            
        } catch (IOException e) {
            System.err.println("file operation error");

            try {
                if (fis != null) {
                    fis.close();
                }
                if (gzos != null) {
                    gzos.close();
                }
            } catch (IOException ee) {
            } // 何もしない
        }
    }
}

// 実行してみる
kiyota-MacBook-Pro:src kiyotatakeshi$ java Main copy01.txt copy01.zip
kiyota-MacBook-Pro:src kiyotatakeshi$ ls
Main.class      Main.java       copy01.txt      copy01.zip      copy02.txt

```
