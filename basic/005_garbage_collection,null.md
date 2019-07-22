### ガーベッジコレクション

```
public class Main {
    public static void main(String[] args) {
        boolean b = true;
        if (b == true) {

            // iを宣言し、3つの要素を持つ配列を生成
            // int[] i = new int[3];
            // i[0] = 1;
            // i[1] = 2;
            // i[2] = 3;

            int[] i = {1, 2, 3};
        }
        // 配列変数iはブロックが終了したため消滅
        // newで確保された要素はブロック終了後も寿命を迎えない
        // 配列はどの配列変数からも参照されない状態でメモリに残る

        // 使用しなくなった配列を破棄してメモリ領域を返す後片付けのことプログラマが行う必要がある
        // Javaはガベージコレクション(GC:garbage collection)が常に動いており、
        // 参照されなくなったメモリ領域は自動的に片付けてくれる
    }
}

```

---
### null

```
public class Main {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};

        // nullを使用することで意図的に参照されないようにすることが可能
        a = null;

        // 以下はエラーになる(コンパイルは成功)
        // 例外
        // Exception in thread "main" java.lang.NullPointerException
        // a[0] = 10;

        // 参照型変数に代入するとどこも参照していない状態にできる
        // ある番地を参照していた配列変数にnullを代入することを「参照を切る」という
        // int型などの基本型変数にはnullは代入できない

    }
}

```