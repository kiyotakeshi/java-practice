### 文字列処理

- 文字列調査のメソッドの利用

```
public class Main {
    public static void main(String[] args) {

        String s1 = "Java";
        String s2 = "java";
        String s3 = "JAVA";

        if (s2.equals(s3)) {
            System.out.println("s2 equals s3");
        }

//      大文字小文字の区別をcaseという
        if (s2.equalsIgnoreCase(s3)) {
            System.out.println("s2 equals s3 that ignore case");
        }

        System.out.println("s1 length is " + s1.length());
//      s1 length is 4

//      空文字でないので出力されない
        if (s1.isEmpty()) {
            System.out.println("s1 is blank");
        }
    }
}
```

---
- 文字列の検索

```
public class Main {
    public static void main(String[] args) {

        String s1 = "Java and JavaScript";

        if (s1.contains("Java")) {
            System.out.println("s1 contains Java");
        }

//      文字列Javaで終わっていないので出力されない
        if (s1.endsWith("Java")) {
            System.out.println("end with s1 contains Java");
        }

//      文字列が最初に登場する位置を表示(先頭は0)
        System.out.println("the start location of Java " + s1.indexOf("Java"));

        System.out.println("the end location of Java " + s1.lastIndexOf("Java"));

    }
}
```

---
- 文字列の切り出し

```
public class Main {
    public static void main(String[] args) {

        String s1 = "Java programing";
        System.out.println("s1 after 4th location: " +
                s1.substring(3));

        System.out.println("s1 between 4th and 8th location: " +
                s1.substring(3, 8));

//        出力結果
//        s1 after 4th location: a programing
//        s1 between 4th and 8th location: a pro

    }
}
```

---
- 文字列を連結
    - 「 + 」 演算子を使うよりはるかに高速で処理できるStringBuilderクラス
    - StringBuilderインスタンスが内部に連結した文字列を蓄えるメモリ領域(バッファ)を持っている

```
public class Main {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 1000; i++) {
//          バッファに文字列を追加
            sb.append("Java");
        }

//      最後に1回だけ連結済みの文字列を取り出す
        String s = sb.toString();

    }
}
```

```
public class Main {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

//      apiリファレンスで見るとappend()メソッドの戻り値はStringBuilder
//      自分自身を戻り値として返しているため自身への参照を返すメソッドを連続して呼び出せる(メソッドチェーン)
        sb.append("hello").append("java").append("world");
        System.out.println(sb);
    }
}
```

- 多数の文字列を連結する場合StringBuilderを使用する

- Stringインスタンスが保持する文字列情報
    - インスタンス化した際に初期化され、二度と変化しない(不変のクラス)
    - Stringに文字列を連結しているときインスタンスが大きくなるというイメージは間違い
    - 古いインスタンスは捨てられ、連結後の情報を持つインスタンスがnewにより生成されている

- + による演算はその回数分インスタンスを生成している
- StringBuilderは可変なクラスとして設計され、append()メソッドを呼び出すたびにバッファを拡大して文字列を追記

---
### 正規表現(パターンマッチング)

```
    boolean isValidPlayerName(String name) {
        return name.matches("[A-Z][A-Z0-9]{7}");
    }
```

- 正規表現を用いた文字列の分割

```
public class Main {
    public static void main(String[] args) {
        String s = "abc,def:ghi";
        String[] words = s.split("[,:]");

        for (String w : words) {
            System.out.println(w + "->");
//          abc->
//          def->
//          ghi->
        }
    }
}
```

- 置換

```
public class Main {
    public static void main(String[] args) {
        String s = "abc,def:ghi";
        String w = s.replaceAll("[beh]", "X");

        System.out.println(w); // aXc,dXf:gXi
    }
}
```

---
### 確認

- StringBuilder

```
public class Main {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

//      1~100までの整数をカンマで連結した文字列sを生成
        for (int i = 1; i <= 100; i++) {
            sb.append(i).append(",");
        }

//      String型に変換
        String s = sb.toString();

//      カンマで分割し、配列に代入
        String[] a = s.split(",");

    }
}
```

- フォルダ名が入っている変数folderとファイル名が入っている変数fileを連結するメソッド

```
    String concatPath(String folder, String file) {

//      末尾に ¥ が付いていない場合にはつける
        if (!folder.endsWith("¥¥")) {
            folder += "¥¥";
        }

        return folder + file;
    }
```

- 正規表現

```
// すべての文字列
.*

// 1文字目はA,2文字目は数字、3文字目は数字か無し
// ¥dは[0-9]と同じ
// {1,2}は1回以上2回以下の繰り返し
A¥d{1,2}

// 1文字目はU,2~4文字目は英大文字
U[A-Z]{3}
```
