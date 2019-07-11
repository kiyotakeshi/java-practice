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