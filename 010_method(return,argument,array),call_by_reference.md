### メソッドと配列

- 引数に配列を使う

```
public class Main {
    public static void main(String[] args) {

        int[] array = {1, 2, 3};

        // メソッドの実引数として配列を渡す
        // 渡しているのは配列まるごとではなく、アドレスの情報のみ(参照渡し)
        printArray(array);
    }

    // メソッドの仮引数(受け取る変数)で配列を指定
    // int[]のような配列型変数には配列の実体を指し示すメモリ番号が入っている
    public static void printArray(int[] array) {
        for (int element : array) {

            // 配列の要素を出力
            System.out.println(element);
        }
    }
}

```

---
- 参照渡し(厳密には「参照の値渡し」と呼ばれる)の確認

```
public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};

        for (int i : array) {
            System.out.println(i);
            // 1
            // 2
            // 3
        }

        // メソッドが呼ばれて値が1増える
        // 呼び出し先のメソッドでの変更が呼び出し元に影響を与える
        incArray(array);

        // arrayの全要素を出力
        for (int i : array) {
            System.out.println(i);
            // 2
            // 3
            // 4
        }
    }

    // 配列の要素に1を加えるメソッド
    public static void incArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i]++;
        }
    }
}

```

---
- 戻り値に配列を使う

```
public class Main {
    public static void main(String[] args) {

        // makeArrayメソッドを実引数(渡す値)3で呼び出して配列arrayに入れる
        int[] array = makeArray(3);

        // 配列の中身を出力
        for (int i : array) {
            System.out.println(i);
            // 0
            // 1
            // 2
        }
    }

    // int型配列を作成して戻すメソッド
    // 配列を戻すからstaticの後は int[]
    public static int[] makeArray(int size) {
        int[] newArray = new int[size];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = i;
        }

        // 配列を戻す
        return newArray;
    }
}

```

---
- コマンドライン引数
    - 最初に呼び出されるmainメソッドには呼び出し元のメソッドがない
    - プログラム起動に追加情報をしてして起動する
    - 追加情報のことを「コマンドライン引数」と呼ぶ