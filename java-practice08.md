- 継承
    - 以前と似たクラスを効率よく作る
    - 元クラスの差分だけを記述して新たにクラス宣言できる

- 継承しないと発生する問題
    - 元クラスの変更点を全ての同じように作ったクラスに反映させる必要がある
    - ソースコードが重複し、把握や管理が難しくなる

```
// ベースとなるクラス

public class Hero {
    private String name = "Mike";
    private int hp = 100;

    public void attack(Matango m) {
        System.out.println(this.name + "attack");
        m.hp -= 5;
        System.out.println("5points damage!");
    }

    public void run() {
        System.out.println(this.name + "escape");
    }
}

```

```
// 継承を使い
// 空をとび、着地できるSuperHeroを作成

public class SuperHero extends Hero {
    private boolean flying;

    public void fly() {
        this.flying = true;
        System.out.println("Take off");
    }

    public void land() {
        this.flying = false;
        System.out.println("Land on");
    }
}

```

```
// 継承元のHeroクラスのメソッドもちゃんと使える

public class Main {
    public static void main(String[] args) {
        SuperHero sh = new SuperHero();
        sh.run();
    }
}

```

- 複数のクラスを親として定義する多重継承はJavaでは使えない

- オーバーライド
    - 子クラスでメソッドを再定義(上書き)する

```
public class SuperHero extends Hero {
    private boolean flying;

・・・

    // SuperHeroクラスのメソッドの動きだけかえる
    public void run() {
        // 親クラスでは以下のように記述されている
        // System.out.println(this.name + "escape");
        System.out.println("escape");
    }
}

```

```
public class Main {
    public static void main(String[] args) {
        Hero h = new Hero();
        h.run();

        SuperHero sh = new SuperHero();
        sh.run();
    }
}

```

- finalが付いているクラスは継承ができない
    - stringクラスは継承できない

- finalが付いているメソッドは子クラスでオーバーライドできない

```
    // finalが付いているメソッドは子クラスでオーバーライドできない
    public final void slip(){
        this.hp -= 5;
        System.out.println(this.getName() + "is slipping");
        System.out.println("-5points");
    }

    // オーバーライドできる
    public void run() {
        System.out.println(this.name + "escape");
    }

```

- SuperHeroインスタンスは内部にHeroクラスから生まれたHeroインスタンスを含んでいる二重構造になっている
    - 外側を「子インスタンス部分」、内側を「親インスタンス部分」
    - インスタンスの外部からメソッドの呼び出しがあると、極力外側にある子インスタンス部分で対応
    - オーバライドしている場合は親クラスの呼び出しに届かないから上書きされたように見える

- 内側の親クラスに属するインスタンスが活躍する場合

```

    // 空を飛んでいる状態でattackすると2回攻撃できる
    // この書き方だとHeroクラスのattack()メソッドのダメージが
    //一回で10に修正されても5ポイントダメージを二回できるだけ
    public void attack(Matango m) {

        // 1回目の攻撃
        System.out.println(this.name + "attack");
        m.hp -= 5;
        System.out.println("5points damage");

        // 2回目の攻撃
        if (this.flying) {
            System.out.println(this.name + "attack");
            m.hp -= 5;
            System.out.println("5points damage");
        }
    }

```

```
    public void attack(Matango m) {

        // 親インスタンス部のattack()の呼び出し
        // superとは親インスタンスを表す予約語
        // これを利用すると親インスタンス部のメソッドやフィールドに子インスタンスからアクセスできる
        super.attack(m);
        if (this.flying) {

            // 親インスタンス部のattack()の呼び出し
            super.attack(m);

            // superをつかつけないとthis.attack()と同じ意味になってしまう
            // そうするとattack()を呼びだし続ける無限ループになる
            // attack(m);
        }
    }

```

- 継承を利用したクラスの作られ方

```
public class Hero {
    public Hero(){
        System.out.println("Hero class constructor function");
    }
}

public class SuperHero extends Hero {
    public SuperHero(){
        // 明示的に親コンストラクタを呼び出す
        // super();
        // コンパイラが自動的に super(); を挿入する
        // つまりコンストラクタは内側のインスタンス部分のものから順に呼ばれていく
        System.out.println("SuperHero constructor function");
    }
}

public class Main {
    public static void main(String[] args) {
        SuperHero sh = new SuperHero();

        // 実行結果
        // Hero class constructor function
        //SuperHero constructor function
        // 全てのコンストラクタは先頭で必ず内部インスタンス部(親クラス)のコンストラクタを呼び出す
    }
}
```

- 親インスタンスが作れない状況
    - 以下のコードはエラーになる

```
public class Item {
    private String name;
    private int price;

    public Item(String name){
        this.name = name;
        this.price = 0;
    }

    // 引数2つのコンストラクタ
    public Item(String name, int price){
        this.name = name;
        this.price = price;
    }
}

public class Weapon extends Item {
    // 見えないけどこれがついている
    // 親コンストラクタを呼び出すを呼び出そうとする
    // super();
}


public class Main {
    public static void main(String[] args) {

        // Weaponインスタンスを生成しようとする
        // Itemクラスを継承しているため
        // 内部にItemインスタンスを含む多重構造になるはず
        // コンストラクタの定義がないのでデフォルトコンストラクタが定義され動作
        // super(); により親クラスのItemのコンストラクタを引数なしで呼ぼうとする
        // Itemクラスのコンストラクタには引数0のコンストラクタは存在しないため呼び出しに失敗
        Weapon w = new Weapon();
    }
}

```

- 以下のように修正すればエラーにならない

```
public class Weapon extends Item {
   public Weapon(){
       // 内部インスタンスの初期化を行うコンストラクタ Item()に
       // 引数を明示的に与える
       super("normal sword")
    }
}

```

---

- 正しい継承、間違った継承
    - (子クラス) is-a (親クラス) の原則に乗っ取って継承する
    - 子クラスは親クラスの一種である状態
    - SuperHeroはHeroの一種

- Itemクラス(勇者の持ち物)を継承してHouseクラスを作れるが Houser is-a Item にはならない(持ち歩かない)

- 間違った継承は将来的にクラスを拡張する時に現実世界との矛盾が生じるから
- 多態性を利用できなくなるから

```
public class Item {

    // 敵に投げつけた時のダメージ
    // これをHouseメソッドに継承すると
    // 家を投げることになり現実に即していない
    // HouseクラスにあるgetDamage()は無視して使わないようにするのように
    // 存在するけど使ってはいけないメンバがあるクラスは作らない！
    public int getDamage(){
        return 10;
    }
}

```

- 親クラスになるほど一般的で抽象的なもの(汎化),子クラスになるほど特殊で具体的なもの(特化)になる
- 特化するほど詳細にフィールドやメソッドを定めることができ、メンバは増えていく
- 汎化するほどフィールドやメソッドを多く定めることは難しくなる
- 継承はある2つのクラスに特化、汎化の関係があることを示すための道具でもある

---

- 復習


```
// 親クラスと子クラスの例

Device -> Phone -> SmartPhone
Vehicle -> Car -> TeslaCar
Book -> Dictionary -> EnglishDictionary

```

```
// 以下のクラスを元にPoisonMatangoクラスを作成
public class Matango {
    int hp = 50;
    private char suffix;

    public Matango(char suffix) {
        this.suffix = suffix;
    }

    public void attack(Hero h) {
        System.out.println("Matango" + this.suffix + " attack");
        System.out.println("10points damage");
        h.setHP(h.getHp() - 10);
    }
}

```

```
public class PoisonMatango extends Matango {

    // 毒攻撃が可能な回数
    private int poisonCount = 5;

    // PoisonMatango pm = new PoisonMatango('A'); でインスタンス化できるように記述
    public PoisonMatango(char suffix) {
        super(suffix);
    }

    //
    public void attack(Hero h) {

        // 親インスタンス部のattack()の呼び出し
        super.attack(h);

        // 追加での毒攻撃
        if (this.poisonCount > 0) {
            System.out.println("extra poison attack! ");

            // Heroのhpの 1/5 を引く
            int dmg = h.getHp() / 5;
            h.setHp(h.getHp() - dmg);
            System.out.println(dmg + "points extra damage!");

            // 毒攻撃の回数が減る
            this.poisonCount--;
        }
    }

}

```
