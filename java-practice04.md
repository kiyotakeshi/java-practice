- JDKを用いた開発の流れ

1. ソースコードを作成(クラス名がMainなら Main.java)

2. javacコマンドでJavaコンパイラを起動

3. コンパイラがソースファイルを実行可能なクラスファイル(class file)に変換
    - クラスファイルはバイトコードなどからなる

4. javaコマンドでインタプリタを起動

5. クラスファイルの中身がJVMに読み込まれ実行

- javacコマンドで出力されるバイトコードは汎用的なマシン語
    - 特定のCPUに依存しないためパソコンでもスパコンでも動く

- それぞれのインタプリタによってバイトコードを実行環境に合わせたマシン語に変換して実行される

- 汎用的なマシン語(バイトコード)を理解するコンピュータが実行しているように見えるためJVM(Java Virtual Machine)と呼ばれる

- Javaプログラムを実行してみる

- ソースコードの確認
```
kiyota-MacBook-Pro:java kiyotatakeshi$ ls
Main.java               SpeedConverter.java

kiyota-MacBook-Pro:java kiyotatakeshi$ cat Main.java
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {

        // 別クラスのメソッドを呼び出している
        long miles = SpeedConverter.toMilesPerHour(10.5);
        System.out.println("Miles = " + miles);

        // 別クラスのメソッドを呼び出している
        SpeedConverter.printConversion(10.5);
    }
}

kiyota-MacBook-Pro:java kiyotatakeshi$ cat SpeedConverter.java
/**
 * SpeedConverter
 */
public class SpeedConverter {

    public static long toMilesPerHour(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            return -1;
        }

        return Math.round(kilometersPerHour / 1.609);
    }

    public static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            System.out.println("Invalid Value");
        } else {
            long milesPerHour = toMilesPerHour(kilometersPerHour);
            System.out.println(kilometersPerHour +
            " km/h = " + milesPerHour +
            "mi/h");
        }
    }
}

```

- コンパイル、確認

```
kiyota-MacBook-Pro:java kiyotatakeshi$ javac Main.java

kiyota-MacBook-Pro:java kiyotatakeshi$ ls
Main.class              SpeedConverter.class
Main.java               SpeedConverter.java

```

- プログラムの実行(.classを取り除いたものを指定)

```
kiyota-MacBook-Pro:java kiyotatakeshi$ java Main
Miles = 7
10.5 km/h = 7mi/h

```