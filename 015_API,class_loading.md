### 標準API

- 作成したクラスは様々なプログラムと連携して動く
    - 標準搭載されているクラス(API)は200パッケージ、3500以上のクラスがある

```
// JVMに組み込まれたクラスの完全限定クラス名を出力
// 300件以上のプログラムと連携して動いていることがわかる

kiyota-MacBook-Pro:Calc kiyotatakeshi$ java -verbose:class HelloWorld

```

- APIが組み込まれているおかげで命令部分を作る必要がない
    - arrays.classなどの形でJDKをインストールした際にコピーされている

```
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

---
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
### クラスが読みこまれる仕組み

- JVMが必要なクラスファイルを読み込む処理を「クラスローディング」という

- 必要になった時に必要なクラスだけメモリに読み込む

- JVMの中のクラスローダーがクラスファイルの読み書きをする
    - ハードディスク内の特定のクラスファイルを読む
    - クラスファイルがどのフォルダにあるかはクラスパスを基に探し出される

- クラスパスを指定する方法

1. javaコマンドでJVMを起動する際に、 -cp か -classpath オプションで指定

2. ユーザー環境変数 CLASSPATH に検索場所を登録
    - export CLASSPATH=/var/javadev

3. 指定しない
    - javaコマンドが実行されたフォルダがクラスパスになる

- クラスパスで指定できる対象

1. フォルダの場所
2. クラスファイルが入ったJARやZIPファイル
3. 複数のフォルダやJAR,ZIP( : で区切って指定)

- rt.jar が自動的にクラスパスに登録されている
    - Javaをインストールしたフォルダの配下(lib)に含まれている
    - 展開するとjavaフォルダ、langフォルダ、System.classなどがある
    - 意識することなく、SystemクラスなどのAPIが利用できる