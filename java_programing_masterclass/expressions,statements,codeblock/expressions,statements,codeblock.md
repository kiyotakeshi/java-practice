- keywords

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {
        // keywords(予約語)の使用によるエラー
        // int int = 5;
    }
}

```

- statement

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {
        int myVariable = 50;

        // + の後にスペースを入れて改行するとIDEが調整してくれる
        System.out.println("This is" +
                "another" +
                "still more");
    }
}

```

- if

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 5000;
        int levelCompleted = 5;
        int bonus = 100;

        // コードブロックがないと可読性が下がる
//        if (score == 4000)
//            System.out.println("Your score was 5000");
//        // スコープ外のため以下は表示される
//        System.out.println("This is out of scope");

        if (score < 5000 && score > 1000) {
            System.out.println("Your score was 5000");
            System.out.println("This is inside scope");
        } else if (score < 1000) {
            System.out.println("Your score was less than 1000");
        } else {
            // 以下が出力
            System.out.println("Got here");
        }

        if (gameOver == true) {
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
            // Your final score was 5500
            // コードブロック終了時に変数は削除される
        }
        // スコープ外のため参照できない
        // finalScoreはローカル変数
        // int savedFinalScore = finalScore;

        // 新たな変数を定義する
        boolean newgameOver = true;
        int newScore = 1000;
        int newLevelCompleted = 8;
        int newBonus = 200;

        if (newgameOver) {
            int finalScore = newScore + (newLevelCompleted * newBonus);
            System.out.println("Your final score was " + finalScore);
            // Your final score was 2600
        }

        // 同じ変数の値を変える
        // duplicate(複製)は多用するとわかりにくいコードになるので注意
        score = 10000;
        levelCompleted = 8;
        bonus = 200;
        if (gameOver == true) {
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score was " + finalScore);
            // Your final score was 11600
        }

    }
}

```
