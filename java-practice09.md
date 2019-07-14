- 高度な継承
    - 別の立場について考え直す

        - 現在の目の前のプログラム開発に必要なクラスを作る開発者(従来の継承)
            - 既定クラスを継承し子クラスをつくる

        - 未来に備え、別の開発者が将来利用するであろうクラスを準備しておく(高度な継承)
            - 安心便利に使ってもらえる親となるクラスを作っておく

- 高度な継承の具体例
    - 開発者がHeroやWizardなど似たクラスを一から作っている
    - 各キャラクタのクラスではname,hpなどのフィールドとattack(),run()などのメソッドは共通
    - 今後もキャラクタが増える予定
    - 各クラスに共通するフィールドやメソッドを持つCharacterクラスをつくる
    - 今すぐ必要な実際に利用されるクラスをつくる開発者の立場でなく、継承元となるクラスをつくる立場

---

- 継承の材料となるクラスに関する不都合と心配要素

- メソッドの内容を確定できない(詳細未定メソッドがある)

```
public class Character {
    String name;
    int hp;

    public void run(){
        System.out.println(this.name + "escape");
    }

    public void attack(Matango m){
        System.out.println(this.name +" attacks!");

        // ダメージをどれくらいにしたらいいかわからない
        // 将来的に完成するキャラクタによって与えるダメージが異なるため
        // m.hp -= ??
    }
}

```
- 内容を確定できないメソッドを記述しないでおくと、継承先のクラスで追加し忘れる(攻撃できないキャラになる)
- Hp属性を持ち、攻撃できるという共通前提にもとづきCharacterクラスを作り始めたのに本末転倒
- 現実世界とJavaコードの世界に矛盾が生じる余地があると不具合が生じる

- 完全ではない対策(以下の不都合が起きる)
    - オーバーライドし忘れると内容が空のメソッドが呼び出され何も起きない
    - コンパイル時にエラーも出ないタチの悪いエラー
    - 呼ばれても何もしないメソッドと何かしたくても未定で記述できないものの区別がつかない
    - オーバーライドを設定していても、間違えてCharcterクラス(メソッドを空にしているクラス)を利用してしまうかも

```

    // メソッドの中身を空にする
    public void attack(Matango m){
    }

// 呼び出し先でオーバーライド
public class Hero extends Character {

    // Characterのattackメソッドをオーバーライド
    public void attack(Matango m) {
        System.out.println(this.name + " attacks!");
        m.hp -= 10;
    }
}

```

```
public class Main {
    public static void main(String[] args) {

        // Hero,WizardでなくCharactorをnewしてしまった
        // 継承の材料として使うはずのクラスからインスタンスを作ってしまった
        Character c = new Character();
        Matango m = new Matango('A');

        // オーバーライドされていないので何も動かない
        c.attack(m);
    }
}

```

- 未来の開発者のために準備しておくクラスは未完成の部分が残っている
- 一方で一部でも未完成が残っている設計図(クラス)から実体を生み出してはならない

- クラスの利用方法はそもそも二つ
    - 未来の開発者はどちらの用途でも使えてしまう…(extendsだけにしてほしいが)

1. インスタンスを生み出すために利用(new)
2. 別クラスを開発する際に継承元として利用(extends)

---

- 抽象クラス

- 「何もしないとの区別ができない」への対策
    - 詳細未定メソッドを記述する専用の構文が用意されている

```
// 今の段階ではシンタックスエラー
public class Character {

    // メソッドは宣言するが、内容は確定できないので
    // メソッドの内部の処理は記載しないという表明
    // メソッド宣言の後ろには{}も不要で代わりにセミコロン
    // 抽象メソッドと呼ばれる
    public abstract void attack(Matango m);
}

```

- 「継承専用クラスをnewされる」への対策
    - 未完成部分(抽象メソッド)を含むクラスはそのことを宣言する
        - 宣言しないとコンパイルエラーになる
        - 抽象クラスはnewによるインスタンス化が禁止される

```
// 抽象クラスとしてCharacterを宣言
public abstract class Character {

    String name;
    int hp;

    public void run() {
        System.out.println(this.name + " escape");
    }

    public abstract void attack(Matango m);
}

```

- 「開発者が詳細未定メソッドをオーバーライドし忘れる」への対策
    - 隠れた抽象メソッドによりオーバーライドを忘れるとコンパイルエラーになる
    - 継承先(Dancer)は継承元(Character)の全てのメンバを継承している
    - 継承先のソースコードに抽象メソッドがなくても親クラスから継承して抽象メソッドを持っている

```
// このままだとコンパイラエラー
public class Dancer extends Character{

    public void dance(){
        System.out.println(this.name + " dance");
    }
}
// attack()をオーバーライドし忘れている

```

- コンパイルエラーを解消するために(どちらかを実行)
1. Dancerクラスの宣言にabstractをつけて抽象クラスにする
2. Dancerクラス内部の未完成部分を全て無くす

```
public class Dancer extends Character {

    public void dance() {
        System.out.println(this.name + " dance");
    }

    // 親の詳細未定のattack()をオーバーライド
    // メソッドの内容を確定させることを「実装(implements)する」と表現する
    public void attack(Matango m) {
        System.out.println(this.name + " attack");
        System.out.println("3points damage");
        m.hp -= 3;
    }
}

```

- 多階層の抽象継承構造
    - 抽象クラスを継承した子クラスで全ての抽象メソッドをオーバーライドして実装する必要はない
    - ただし、全ての抽象メソッドの処理内容が確定しなければabstractをはずせない(newできない)


```
Master: hp, mp, attack(未定), run(未定)
WalkingMonster: hp(継承されたフィールド), mp(継承されたフィールド), attack(未定), run(走って逃げる)
FlyingMonster: hp(継承されたフィールド), mp(継承されたフィールド), attack(未定), run(飛んで逃げる)

→ 継承した (Walking|Flying)Monster は抽象メソッドを持っているため抽象クラス(abstruct宣言しnewできない)

Goblin: hp(継承されたフィールド), mp(継承されたフィールド), attack(斬りつける), run(走って逃げる)
WarWolf: hp(継承されたフィールド), mp(継承されたフィールド), attack(噛み付く), run(走って逃げる)

→ ここで抽象メソッドがなくなり、newして利用することができる
→ 継承が繰り返されるたびに具体化していきメソッドの処理内容が実装されていく

```

---

- インターフェース
    ー 継承階層を辿っていくとどんどん曖昧なものになっていく
    - どんな内部情報を持っているか(フィールド)、どのような動きをするか(メソッド)はあやふやになる
    - 条件を満たす特に抽象度が高い抽象クラスを「インターフェース」として特別に扱うことができる

1. 全てのメソッドは抽象メソッド
2. 基本的にフィールドを1つも持たない

```
// 抽象クラス
// 抽象メソッドしかなく、フィールドもない
// インターフェースとして定義可能

//public abstract class Creature {
//    public abstract void run();
// }

// インターフェースを定義
// 抽象クラスの親戚であまりにもあいまいすぎて特別扱いされた抽象クラス
public interface Creature {

    // インターフェースに宣言されたメソッドは自動的にpublicかつabstractになる
    // public abstract void run();
    // 以下のように記載可能
    void run();

    // インターフェースないでフィールドを宣言する場合は
    // public static final がついたフィールド(定数)だけは宣言できる
    // public static final は省略されるため
    // インターフェース内でフィールド宣言すると自動的に定数を宣言したことになる
    // double PI = 3.14;
}

```

- なぜインターフェースというのか
    - 店頭メニューのようにできることを表明して、お客さんとの接点(interface)と役割だから
    - 全国チェーンのクリーニング店の店頭メニューをインターフェースでつくる
    - チェーン店の各店舗の設備やメニューは違う部分もあるので
    - 個々の CleaningShopクラスで抽象メソッドをオーバーライドして実装

```
// シャツ、タオル、コートを渡すと洗って返してくれる
// 全てのメソッドは抽象メソッドで処理内容は記述されていない
// どのようにして洗うかなどの、クリーニング店の内部で行われる作業は明かされていない

public interface CleaningService {
    Shirt washShirt(Shirt s);
    Towl washTowl(Towl t);
    Coat washCoat(Coat c);
}

```

```
// CleaningServiceのインターフェース(店頭メニュー)を継承した
// クリーニング店そのもの

// インターフェースを継承しクラス宣言する時は implements(実装)
public class KyotoCleaningShop implements CleaningService {

    private String ownerName; // 店名
    private String address;
    private String phone;

    // シャツを洗う
    // インターフェースも中身は抽象クラスと同じ
    // 抽象メソッドをオーバーライドする
    public Shirt washShirt(Shirt s) {
        // 大型洗濯機15min
        // 業務用乾燥機30min
        // スチームアイロン5min
        return s;
    }

    // タオルを洗う
    // 抽象メソッドをオーバーライドする
    public Towl washTowl(Towl t) {
        return t;
    }

    // コートを洗う
    // 抽象メソッドをオーバーライドする
    public Coat washCoat(Coat c) {
        return c;
    }
}

```

- インターフェースを使うと実現できること
    - 同じインターフェースをimplementsする子クラスに共通のメソッド群を実装することを矯正できる
    - インターフェースを実装していれば、そのインターフェースが定めたメソッドを持っていることが保証される

- インターフェースが特別扱いされるのは、内部実装を(メソッドの処理動作)を一切定めていないから

- インターフェースでは特別に多重継承(クラス作成を2つのクラスから行うことができる)が許可されている
    - 例) Creatureクラスから継承されたPrincessクラスとHeroクラスを元にPrincessHeroクラスをつくる
    - インターフェースにはメソッドの処理内容が書かれていないので、複数の継承元のメソッドが競合することがない
    - 勇者かつお嬢様の独自のメソッドとしてオーバライドする

```
public class PrincessHero {

    // 3つのインターフェースから多重継承
    implements Hero, Princess, Character {

    }
}

```

- インターフェースの継承

```
// 既存のインターフェースを元にして新たなインターフェースを定義
// HumanはCreatureのメソッドをオーバーライドしているわけではないので
// implementsではなくextendsを使う
public interface Human extends Creature {
    void talk();
    void watch();
    void hear();

}

```

- extends,implementsを一緒に使う

```
public class Fool extends Character implements Human {
    // CharactorからhpやgetName()などのメンバを継承

    // Characterから継承した抽象メソッドattack()を実装
    public void attack(Matango m){
        System.out.println(this.getName() + "doesn't fight");
    }

    // Humanから継承した4つの抽象メソッドを実装
    public void talk(){}
    public void watch(){}
    public void hear(){}
    public void run(){}
}

```

---
- 振り返り

- 他の人が継承の材料として使うであろう親クラスを作る立場の開発もある
    - 抽象クラスやインターフェースを使う
    - 未来の開発者が効率よく安心して利用できる継承の材料を作る

- 抽象クラス
    - 中身を決定できない詳細未定メソッドにはabstractをつけて抽象メソッドとする
    - 抽象メソッドを一つでも含んでいるとabstractをつける
    - 抽象クラスはインスタンス化できない
    - 抽象クラスと抽象メソッドを継承の材料として開発すれば、予期しないインスタンス化やオーバーライド忘れを防げる

- インターフェース
    - 抽象メソッドしか持たないものをインターフェースとして特別扱いできる
    - public abstruct となりフィールドは public static final になる
    - 複数のインターフェースを親とする多重継承が許されている
    - インターフェースを親に持つ子クラスの定義にはimplementsを用いる

- まとめ

- 様々な形ある資産を管理するために有形資産という名前の抽象クラスを作成する

```
public class Book {
    private String name;
    private int price;
    private String color;
    private String isbn;

    // コンストラクタ
    public Book(String name, int price, String color, String isbn) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.isbn = isbn;
    }

    // getterメソッド
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getColor() {
        return this.color;
    }

    public String getIsbn() {
        return this.isbn;
    }

}

```

```
public class Computer {
    private String name;
    private int price;
    private String color;
    private String makerName;

    // コンストラクタ
    public Computer(String name, int price, String color, String makerName) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.makerName = makerName;
    }

    // getterメソッド
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getColor() {
        return this.color;
    }

    public String getMakerName() {
        return this.makerName;
    }
}

```

- 抽象クラスを作成(TangibleAsset)

```
// 抽象クラス
public abstract class TangibleAsset {
    private String name;
    private int price;
    private String color;

    // コンストラクタ
    public TangibleAsset(String name, int price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    // getterメソッド
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }
}

```


```
public class Book extends TangibleAsset {
    private String isbn;

    // コンストラクタ
    public Book(String name, int price, String color, String isbn) {

        // superとは親インスタンスを表す予約語
        // これを利用すると親インスタンス部の
        // メソッドやフィールドに子インスタンスからアクセスできる
        super(name, price, color);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return this.isbn;
    }

}

```

```
public class Computer extends TangibleAsset {
    private String makerName;

    // コンストラクタ
    public Computer(String name, int price, String color, String makerName) {

        // 親クラスのメソッドやフィールドにアクセス
        super(name, price, color);
        this.makerName = makerName;
    }

    // getterメソッド

    public String getMakerName() {
        return this.makerName;
    }
}

```

- 形のない無形資産(IntangibleAsset)も管理する(特許権,Patentなど)
    - 親クラスとなる Assetクラスをつくる

```
// インターフェースは全てのメソッドが抽象メソッドでフィールドを一つも持たない時に使用可能
// 抽象メソッドを持つ抽象クラスを宣言

public abstract class Asset {
    private String name;
    private int price;

    // コンストラクタ
    public Asset(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // getterメソッド
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

```

```
// 抽象クラス
public abstract class TangibleAsset extends Asset {
    private String color;

    // コンストラクタ
    public TangibleAsset(String name, int price, String color) {
        super(name, price);
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}

```

- 資産かは関係なく、形のあるもの(Thing)の重さを定義する
    - getWeight(),setWeight()を持つインターフェースを定義

```
public interface Thing {
    double getWeight();
    void setWeight(double weight);
}

```

- 有形資産(TangibleAsset)は資産(Asset)の一種であり形あるもの(Thing)の一種でもある

```
// 抽象クラス
public abstract class TangibleAsset extends Asset implements Thing {
    private String color;
    private double weight;

    // コンストラクタ
    public TangibleAsset(String name, int price, String color) {
        super(name, price);
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

```