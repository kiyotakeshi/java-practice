### 戻り値

- 呼び出されたメソッドから呼び出し元のメソッドへ値を返すことを値を戻す(返す)という
    - 戻される値のことを戻り値(返り値)と呼ぶ

- メソッドには戻り値の型を記載する

```
public static returnType methodName(arguments){
    system.out.prinln("");
    return returnData;
}

# 何も戻さない場合はvoid
public static void main(String[] args) {
}

```

---
- 戻り値を使った書き方

```
public class Main {

    public static void main(String[] args) {

    // addメソッドの呼び出し(110が返ってくる)
    int ans = add(100, 10);

    System.out.println("100 + 10 = " + ans);
    // 100 + 10 = 110
    }

    public static int add(int x, int y) {
        int ans = x + y;
        return ans;
    }
}

```

- 戻り値を変数で受けずにそのまま使う

```
public class Main {
    public static void main(String[] args) {

        // メソッドの戻り値を変数で受けずに使用する(極端な例)
        // add(10,20),add(30,40)が実行される
        // 外側のaddメソッドがadd(30,70)になる
        // 画面には100が出力される
        System.out.println(add(add(10, 20), add(30, 40)));
    }

    public static int add(int x, int y) {
        int ans = x + y;

        // returnはメソッドの終了も行う
        return ans;

        // returnの後に処理を書いてもコンパイルエラーになる
        // int x = 10;
    }

}

```