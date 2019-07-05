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
        // この後、methodを使う
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

- method

```
// methodを使った書き換え
// 下記のコードよりさらに改善をしていく

package com.kiyota;

public class Main {

    // そもそもこれはmainメソッド
    public static void main(String[] args) {

        // メソッドの呼び出し
        calculateScore(true,800,5,100);
        calculateScore(true, 10000, 8, 200);

    }

    public static void calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        if (gameOver == true) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 2000;
            System.out.println("Your final score was " + finalScore);
        }
    }
}


```

- メソッドを活用した書き方

```
package com.kiyota;

public class Main {

    // mainメソッド
    public static void main(String[] args) {

        // メンテナンスしやさを考えて変数の代入は分ける
        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        // メソッドの呼び出し
        calculateScore(gameOver, score, levelCompleted, bonus);

        score = 1000;
        levelCompleted = 8;
        bonus = 200;

        calculateScore(gameOver, score, levelCompleted, bonus);

    }

    // int を返す
    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        if (gameOver == true) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 2000;
            System.out.println("Your final score was " + finalScore);
            return finalScore;
        }

        return -1;
    }
}

```

- さらにみやすく書き直す

```
package com.kiyota;

public class Main {

    // そもそもこれはmainメソッド
    public static void main(String[] args) {

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int hightScore =  calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was " + hightScore);

        score = 1000;
        levelCompleted = 8;
        bonus = 200;

        hightScore =  calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was " + hightScore);



    }

    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        if (gameOver == true) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 2000;
            return finalScore;
        }
        return -1;
    }
}

```

- 複数のメソッドの活用

```
package com.kiyota;

public class Main {

    // そもそもこれはmainメソッド
    public static void main(String[] args) {

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        int hightScore = calculateScore(gameOver, score, levelCompleted, bonus);
        System.out.println("Your final score was " + hightScore);
//        Your final score was 3300

        int highScorePosition = calculateHighScorePosition(1500);
        displayHightScorePosition("Tim", highScorePosition);

        highScorePosition = calculateHighScorePosition(900);
        displayHightScorePosition("Bob", highScorePosition);

        highScorePosition = calculateHighScorePosition(400);
        displayHightScorePosition("Percy", highScorePosition);

        highScorePosition = calculateHighScorePosition(50);
        displayHightScorePosition("Gilbert", highScorePosition);

//        Tim manageed to get into position1 on the high score table
//        Bob manageed to get into position2 on the high score table
//        Percy manageed to get into position3 on the high score table
//        Gilbert manageed to get into position4 on the high score table

    }

    public static void displayHightScorePosition(String playerName, int highScorePosition) {
        System.out.println(playerName + " manageed to get into position"
                + highScorePosition + " on the high score table");
    }

    public static int calculateHighScorePosition(int playerScore) {
//        if (playerScore >= 1000) {
//            return 1;
//        } else if (playerScore >= 500) {
//            return 2;
//        } else if (playerScore >= 100) {
//            return 3;
//        }
//        return 4;

        int position = 4; // assuming position 4 will be returned

        if (playerScore >= 1000) {
            position = 1;
        } else if (playerScore >= 500) {
            position = 2;
        } else if (playerScore >= 100) {
            position = 3;
        }

        return position;
    }

    public static int calculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {

        if (gameOver == true) {
            int finalScore = score + (levelCompleted * bonus);
            finalScore += 2000;
            return finalScore;
        }
        return -1;
    }
}

```
