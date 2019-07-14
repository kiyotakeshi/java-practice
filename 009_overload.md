### オーバーロード

- 例外的に同じ名前のメソッドを定義すること(多重定義)

    - 仮引数の型が異なっている場合、個数が違う場合に可能

    - 基本はエラーになる
        - 似たような処理をするメソッドを複数作るときにメソッドに同じ名前をつけると
        - JVMはどれを実行したらいいかわからなくなるため

- 仮引数の型が異なる場合

```
public class Main {
    public static void main(String[] args) {

        // 呼び出し元の引数(渡す値、実引数:10,20など)をみてその引数の型に一致するメソッドを呼び出す
        System.out.println(add(10, 20));

        System.out.println(add(3.5, 2.7));

        System.out.println(add("hello", "world"));

        // 出力結果
        // 30
        // 6.2
        // helloworld
    }

    public static int add(int x, int y) {
        return x + y;
    }

    // 仮引数の型が違うため同じ名前のメソッドを使用可能
    public static double add(double x, double y) {
        return x + y;
    }

    // 仮引数(受け取る変数:x,yのこと)の型が違うため同じ名前のメソッドを使用可能
    public static String add(String x, String y) {
        return x + y;
    }
}

```

---
- 引数の数が違う場合

```
public class Main {
    public static void main(String[] args) {

        // 仮引数の個数の違い(x,yかx,y,z)で呼び出すメソッドを区別
        System.out.println("10+20=" + add(10, 20));
        System.out.println("10+20+30=" + add(10, 20, 30));
    }

    public static int add(int x, int y) {
        return x + y;
    }

    public static int add(int x, int y, int z) {
        return x + y + z;
    }

}

```

- メソッド宣言で戻り値の型の後に記載する情報はまとめて、「メソッドのシグネチャ」と呼ぶ
    - メソッド名、引数の個数、型、並び順
    - シグネチャが重複しない場合、オーバーロードが可能