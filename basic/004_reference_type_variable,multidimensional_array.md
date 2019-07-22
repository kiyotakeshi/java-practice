### 参照型変数について

```
public class Main {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b;

        // 参照型変数を代入したことにより、
        // bはaと同じ配列を参照することになる
        b = a;
        b[0] = 100;

        // 100が出力される(b[0]と同じものをさす)
        System.out.println(a[0]);

        // コンピュータは使用するデータをメモリに記録する
        // 変数宣言するとメモリの区画の中からアドレスを指定して確保する
        // 変数に代入するということは、確保していた区画に値を記録すること
        // ex) int型だと4bytes確保

        // 配列を作成すると配列の実体がメモリ上の区画に作成される(int [] score = new int[5];)
        // 配列の変数には5つの要素まるごとではなく、最初の要素のアドレスが代入される

        // 配列変数scoreが[n]と指定して値を取り出される時、
        // scoreが配列の実体があるメモリ上の区画の先頭要素を参照し、
        // 見つけた配列の先頭からn個後ろの要素の区画を読み書きする

        // メモリ上の番地を代入する変数のことを参照型変数(reference type variable)と呼ぶ
    }
}

```

---

- 多次元配列

```

public class Main {
    public static void main(String[] args) {
        // 2人の3教科のテスト結果を格納する2次元配列
        // 最初の[]で行、次の[]で列を指定
        // 配列の配列という構造になっている

        // int[][] scores = new int[2][3];
        // scores[0][0] = 40;
        // scores[0][1] = 50;
        // scores[0][2] = 60;
        // scores[1][0] = 80;
        // scores[1][1] = 60;
        // scores[1][2] = 70;
        // System.out.println(scores[1][1]); // 60

        int[][] scores = {{10, 20, 30}, {30, 40, 50}};
        // 親配列の要素数
        System.out.println(scores.length); // 2
        // 子配列の要素数
        System.out.println(scores[0].length); // 3
    }
}

```