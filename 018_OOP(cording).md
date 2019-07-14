### オブジェクト指向でプログラムを作ってみる

- オブジェクトを生み出す手順

1. クラス(オブジェクトを生成する際に用いる設計図)を定義
    - プログラマがオブジェクトそのものを定義できない

2. クラスに基づいてオブジェクトを生成
    - 必要な数だけ何個でもオブジェクトを生み出せる
    - 金型を作っておきプラスティックを流し込み大量生産するイメージ

- **仮想世界の中で活躍するのはオブジェクトだけで、金型であるクラスは基本的には活動しない**
    - クラスを指しているのかオブジェクトを指しているのか混合して使われることも
    - 仮想世界で活動する実体(オブジェクト)を厳密に示したい場合は、インスタンスと表現する

- クラスからインスタンス(オブジェクト)を生成する行為を「インスタンス化」と表現する

---
- 冒険プログラムを作ってみる
    - HeroクラスとMatangoクラスだけではインスタンスは指示待ちでやることがない(操作の呼び出しがないため)
    - 神様の指示である天の声はMainクラスのmainメソッドとして記述する
    - Hero,Matangoクラス内にmainメソッドを同居させることも可能だがわかりにくくなるため非推奨
    - イメージ「神様のクラス」と「登場人物のクラス」

- 登場人物クラスの作り方
    - 設計内容をクラス名、属性、操作の一覧として並べる
    - クラス図というUML(Unified Modeling Language)で定められている世界共通の書き方

- Heroクラス

```
Hero.java

// クラスブロック内で宣言された変数をフィールドという
public class Hero {

    // 属性の宣言
    String name;
    int hp;
}

```

```
Mantango.java

public class Matango {

    int hp;
    // 宣言と同時に初期値も設定
    // int level = 10;

    // フィールド宣言の先頭にfinalをつけると定数フィールドになる
    final int LEVEL = 10; // 定数は大文字で記述
}

```

- 操作をプログラミングする時に必要な情報
1. 名前
2. 必要な情報の一覧
3. 結果として指示元に返す情報
4. 処理内容

```
Hero.java

// クラス名は単語の頭を大文字
public class Hero {

    String name;
    int hp;

    void sleep() {

        // 自分自身のhpフィールド
        // thisは特別な変数で自分自身をさす
        // . は「〜の」くらいのイメージ
        // 自分自身のインスタンスのhpフィールドに100を代入
        this.hp = 100;

        // thisはなくてもエラーにはならないが
        // ローカル変数や引数と区別するために、フィールドを用いる時は必ずつける！
        System.out.println(this.name + "is sleeping and healing");
    }
}

```

---
- Heroクラスの定義

```
Hero.java

public class Hero {
    String name;
    int hp;

    // sleepメソッド
    void sleep() {
        this.hp = 100;
        System.out.println(this.name + "is sleeping and healing");
    }

    // 何秒座るかを引数で受け取る
    void sit(int sec) {
        this.hp += sec;
        System.out.println(this.name + " sat " + sec + "seconds");
        System.out.println("HP " + sec + "points heal");
    }

    void slip() {
        this.hp -= 5;
        System.out.println(this.name + "is slipping");
        System.out.println("5 points damage");
    }

    void run() {
        System.out.println(this.name + "is running away");
        System.out.println("GAME OVER");
        System.out.println("Finaly, HP is " + this.hp + "points");
    }
}

```

---
- クラスの定義により可能になること

1. そのクラスに基づいてインスタンスを生成できるようになる

2. そのクラスから生まれたインスタンスを入れる変数の型が利用できるようになる
    - Heroクラスを定義するとHero型の変数が利用できるようになる
    - クラスを定義すると利用可能になる型を「クラス型」と呼ぶ

- クラス型について
    - intなどはJavaが標準で準備している型
    - クラスを定義すると利用可能な型の種類が増える
    - **インスタンスは通常、クラス型変数に入れて利用する**
        - クラスから生成された同名インスタンスをプログラム的に識別するため

---
- 神様クラスをつくっていく

```
// Main.java

public class Main {
    public static void main(String[] args) {

        // インスタンスの生成
        // new Hero() でHeroクラスからインスタンスを生成し
        // Hero型変数hに代入
        Hero h = new Hero();

        // わけてかくなら
        // Hero h;
        // h = new Hero();
    }
}

```

- 生成したインスタンスのフィールドに値を代入

```
public class Main {
    public static void main(String[] args) {

        Hero h = new Hero();
        // フィールド値を取得し初期値をセット
        h.name = "Mike";
        h.hp = 100;
        System.out.println("Hero " + h.name + "is genetated");
    }
}

```

---
- インスタンスのメソッドを呼び出す

- mainメソッドの内容が台本のようにわかりやすくなる
    - 勇者を登場させ、操作しているだけ
    - 細かい処理は出てこない
        - ex) HPを増やしたり減らしたりする細かい計算処理
        - ex) 戦闘中に画面にどのメッセージを出すか

```
public class Main {
    public static void main(String[] args) {

        // インスタンス化しフィールドに値を代入
        Hero h = new Hero();
        h.name = "Mike";
        h.hp = 100;
        System.out.println("Hero " + h.name + " is genetated");

        // メソッドを呼び出す
        h.sit(5);
        h.slip();
        h.sit(25);
        h.run();

        // Hero Mike is genetated
        // Mike sat 5 seconds
        // HP 5 points heal!
        // Mike is slipping
        // 5 points damage!
        // Mike sat 25 seconds
        // HP 25 points heal!
        // Mike is running away
        // GAME OVER
        // Finaly, HP is 125points
    }
}

```

---
- おばけきのこ2匹を生み出す

```
// Mantango.java

package work;

public class Matango {
    int hp;
    final int LEVEL = 10;
    char suffix;
    void run(){
        System.out.println("Matango "+ this.suffix + " is running away");
    }
}

```

```
// Main.java

public class Main {
    public static void main(String[] args) {

        Hero h = new Hero();
        h.name = "Mike";
        h.hp = 100;

        // おばけきのこ1匹目を生成し初期化
        Matango m1 = new Matango();
        m1.hp = 50;
        m1.suffix = 'A';

        Matango m2 = new Matango();
        m2.hp = 48;
        m2.suffix = 'B';

        // 冒険の始まり
        h.slip();
        m1.run();
        m2.run();
        h.run();

        // Mike is slipping
        // 5 points damage!
        // Matango A is running away
        // Matango B is running away
        // Mike is running away
        // GAME OVER
        // Finaly, HP is 95points
    }
}

```

---
- 手続き型プログラミングとオブジェクト指向のクラスやメソッドは思想の有無が違う
    - 手続き型のそれは開発者の都合や機能の単位(対応する現実がない)
    - オブジェクト指向だと現実世界と意味がつながった(対応した)クラス

- 仮想世界で活動するのはインスタンス
- インスタンスを生み出すための金型がクラス

---
### クラスの追加

- 聖職者を表現するクラス

```
// Cleric.java

import java.util.*

public class Cleric {

    // 属性を定義
    String name;
    int hp = 50;
    int mp = 10;
    final int MAX_HP = 50;
    final int MAX_MP = 10;

    // MPを5消費しHPを全回復
    public void selfAid() {
        System.out.println("selfAid: mp -5 / hp is full..");
        this.hp = this.MAX_HP;
        this.mp -= 5;
    }

    // 祈った秒数 + (0~2) の値MPを回復
    // 戻り値はint(回復するmp)
    public int pray(int sec) {
        System.out.println(this.name + " is praying " + sec + "seconds");

        // 論理上の回復量を乱数を用いて決定
        int recover = new Random().nextInt(3) + sec;

        // 実際の回復量を決定(mpの上限を超えない)
        // Math.min(a, b)は小さい方の値を返す
        // ex) 10(MAX_MP)-8(mp), 5(recover) の場合は2が返ってくる
        int recoverActual = Math.min(this.MAX_MP - this.mp, recover);

        this.mp += recoverActual;
        System.out.println("MP: +" + recoverActual);
        return recoverActual;
    }
}

```
