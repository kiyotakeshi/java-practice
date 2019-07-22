### インターフェース

- 継承階層を辿っていくとどんどん曖昧なものになっていく
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
// 抽象クラスの親戚であまりにも、あいまいすぎて特別扱いされた抽象クラス
public interface Creature {

    // インターフェースに宣言されたメソッドは自動的にpublicかつabstractになる
    // public abstract void run();

    // 以下のように記載可能
    void run();

    // インターフェース内でフィールドを宣言する場合は
    // public static final がついたフィールド(定数)だけは宣言できる
    // public static final は省略されるため
    // インターフェース内でフィールド宣言すると自動的に定数を宣言したことになる
    // double PI = 3.14;
}

```

---
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

---
- インターフェースを使うと実現できること
    - 同じインターフェースをimplementsする子クラスに共通のメソッド群を実装することを矯正できる
    - インターフェースを実装していれば、そのインターフェースが定めたメソッドを持っていることが保証される

- インターフェースが特別扱いされるのは、内部実装を(メソッドの処理動作)を一切定めていないから

- インターフェースでは特別に多重継承(クラス作成を2つのクラスから行うことができる)が許可されている
    - 例) Creatureクラスから継承されたPrincessクラスとHeroクラスを元にPrincessHeroクラスをつくる
    - インターフェースにはメソッドの処理内容が書かれていないので、複数の継承元のメソッドが競合することがない
    - 勇者かつお嬢様の独自のメソッドとしてオーバライドする

```
// 3つのインターフェースから多重継承
public class PrincessHero implements Hero, Princess, Character {

}

```

---
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
// 同種の継承(クラス同士、インターフェース同士)はextends
// 異種なら(インターフェースとクラス)implements

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
### 抽象クラス、インターフェースのまとめ

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