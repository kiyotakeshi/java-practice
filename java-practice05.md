- オブジェクト指向プログラミング(OOP:Object Oriented Programing)
    - ソフトウェア開発する時に用いる部品化の考え方
    - 人間が把握しきれない複雑さを克服するために生まれたもの
    - 柔軟性、再利用性、保守性が上がる
    - 根底として「人間に把握できるプログラム開発を実現する」のが目的

- そもそもプログラムやシステムは「現実世界における何らかの活動を自動化するためのもの」
    - ex) ATMが両替商の代わりにお金を管理する

- 従来のプログラミング手法は手続き型プログラミング(procedural programming)と呼ばれる
    - プログラマがコンピュータがどのように動くかを考え、プログラムの先頭から順番に命令として記述

- オブジェクト指向ではプログラマはいきなりプログラムを書かない
    - プログラムで実現する部分の現実世界を観察し、設計図に落とし込んでいく

- ITの知識のない一般の人に見せても理解できる現実世界の構図そのものをつくる
    - 設計図の中の人や物を1つの部品と捉え、クラスとして記述していく
    - ハードディスク内のデータのUketukeクラスからJVM内に仮想的な受付係が生み出され現実のようにふるまう

- **コンピュータが何を実行するかの手順を定めるのが手続き型プログラミング**
- **オブジェクトをどう作るか、どのように連携させるかを第一に意識して開発するのがオブジェクト指向**
    - 人間がなれ親しみ把握している世界をプログラムで表現するため、人間に把握できるプログラムになる

---

- プログラマはそれぞれの部品に役割と責任をプログラムとして書き込む
    - サッカーで例えると…
        - 監督(プログラマ)は選手を部品として考え、それぞれの役割と責任を割り当てたクラスオブジェクトとする
        - 選手オブジェクトが作られ、自身の役割を果たしながら他のオブジェクトと連携して動く

- サッカー選手オブジェクトはボールを受けたら前に走る、シュートするなどの「行動責任」を負う
- 口座オブジェクトは行動責任はないが、残高を覚えておくという「情報責任」を持つ

- オブジェクトが情報保持と行動の責任のために持つもの
    - 属性(情報を覚えておく箱)
        - ex) 名前、HP

    - 操作(行動や動作の手順)
        - ex) 戦う、逃げる、眠る

- **現実世界を元に部品を作成する際に、そのオブジェクトがどんな属性や操作を持つかを観察し再現する**

- mainメソッドやその他のオブジェクトからオブジェクトの操作を呼び出す
    - 呼び出されたオブジェクトは行動責任を果たすために指示を実行する
    - オブジェクトは他のオブジェクトの属性を取得したり書き換えることも可能

- 仮想世界内のオブジェクトが互いに属性を読み書きし、操作を呼び出すことで連携してプログラムが動く

---

- オブジェクト指向言語(Object Oriented Programming Language)の3大機能

1. カプセル化
    - 属性や操作を一部の相手からは利用禁止にする機能
        - ex) 「眠る」操作は剣オブジェクトから呼べないようにする

2. 継承
    - 過去に作った部品を流用し新しい部品を簡単につくる
        - ex) 勇者の部品を元に、空を飛べる勇者を作る

3. 多態性
    - 似ている部品を同じものとみなし、いい加減に利用する
        - ex) 地上の敵も空中の敵も同じようにみなし、「戦う」操作で攻撃できる

---

- 復習

- 現実世界の人間の活動をプログラムで機械化、自動化しているものを考える
    - 電子メール
        - 手紙を電子化したもの、配送するインターネットのシステムは郵便配送の仕組みを機械化したもの

    - ネットショッピング
        - 現実世界の商店を電子化、店員が受け持っていた商品の検索、注文依頼、決済を自動化したもの

- プログラムに登場するオブジェクトを考える
    - 航空中の全ての飛行機と空港を管理する航空管制システム
        - 飛行機オブジェクト、空港オブジェクト

    - 映画館を選択すると、上映映画とその主演俳優の一覧を表示するシステム
        - 映画館オブジェクト、映画オブジェクト、俳優オブジェクト

    - 余っている食材を入力すると、その食材を使う料理を検索してくれるプログラム
        - 食材オブジェクト、レシピオブジェクト、料理オブジェクト

- オブジェクトの持つ「行動責任」と「情報保持責任」を考え、操作と属性として書き出す
    - 現実世界の案内係を再現した「案内係」オブジェクト
        - 指定条件に基づいて観光地を検索する操作

    - 現実世界のお店や史跡名勝を再現した「観光地」オブジェクト
        - 名所の名前、住所、電話番号、解説といった属性

---

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

- クラスの定義により可能になること

1. そのクラスに基づいてインスタンスを生成できるようになる

2. そのクラスから生まれたインスタンスを入れる変数の型が利用できるようになる
    - Heroクラスを定義するとHero型の変数が利用できるようになる
    - クラスを定義すると利用可能になる型を「クラス型」と呼ぶ

- クラス型について
    - intなどはJavaが標準で準備している型
    - クラスを定義すると利用可能な型の種類が増える
    - インスタンスは通常、クラス型変数に入れて利用する
        - クラスから生成された同名インスタンスをプログラム的に識別するため

- 神様クラスをつくっていく

```
Main.java

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

- インスタンスのメソッド呼び出し
    - mainメソッドの内容が台本のようにわかりやすい
    - 勇者を登場させ、操作しているだけ

    - 細かい処理は出てこない
        - ex) HPを増やしたり減らしたりする細かい計算処理
        - ex) 戦闘中に画面にどのメッセージを出すか

```
public class Main {
    public static void main(String[] args) {

        Hero h = new Hero();
        h.name = "Mike";
        h.hp = 100;
        System.out.println("Hero " + h.name + " is genetated");

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

- おばけきのこ2匹を生み出す

```
Mantango.java

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
Main.java

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

- 手続き型プログラミングとオブジェクト指向のクラスやメソッドは思想の有無が違う
    - 手続き型のそれは開発者の都合や機能の単位(対応する現実がない)
    - オブジェクト指向だと現実世界と意味がつながった(対応した)クラス

---

- 復習
    - 仮想世界で活動するのはインスタンス
    - インスタンスを生み出すための金型がクラス


