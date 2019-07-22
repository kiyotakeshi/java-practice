### 静的フィールド

- インスタンスの独立性の例外
    - フィールド変数の実体がクラスに準備される(個々のインスタンスごとではない)
    - 各インスタンスで共通したい情報(RPGなら所持金など)に使用する

```
public class Hero {

    String name;
    int hp;
    static int money; // 静的フィールド
}

public class Main {
    public static void main(String[] args) {

        Hero h1 = new Hero();
        Hero h2 = new Hero();
        System.out.println(h1.hp);

        // 静的フィールドを読み書きする
        // Heroクラスの箱moneyを表示
        System.out.println(Hero.money);
    }
}

```

---
- 静的フィールドへのアクセス

```
public class Main {
    public static void main(String[] args) {

        Hero h1 = new Hero();
        Hero h2 = new Hero();

        // 実質的に h1.money,h2.money,Hero.money は同一の箱を指す
        // そのため静的フィールドを用いればインスタンス間でフィールドを共有できる
        System.out.println(Hero.money); // 100
        System.out.println(h1.money); // 100
        h1.money = 300;
        System.out.println(h2.money); // 300
    }
}

```

- **静的フィールドではインスタンスを1つも生み出さなくても箱が利用可能になる**
    - Hero.moneyはクラス(金型)の上に作られる箱のため、実体が生み出されていなくても利用できる
    - **クラスにフィールド(箱)が所属するという特徴から「クラス変数」と呼ばれる**

```
public class Main {
    public static void main(String[] args) {

        // インスタンスを1つも生み出していない
        Hero.money = 100;
        System.out.println(Hero.money);
    }
}

```

---
### 静的メソッド

```
public class Hero {

    String name;
    int hp;
    static int money; // 静的フィールド

    // staticはメソッドにも使用できる
    // 実体がインスタンスではなく、クラスに属するため
    // クラス名.メソッド名();で呼び出せるようになる
    static void setRandomMoney(){
        Hero.money = (int) (Math.random()*100);
    }
}

```

```
public class Main {

    // mainメソッドがstaticなのは
    // 仮想世界にまだインスタンスが存在していないから(最初に呼ばれる)
    public static void main(String[] args) {

        // 静的メソッドはインスタンスを1つも生み出すことなく呼び出せる
        Hero.setRandomMoney();

        // ランダムな金額が表示される
        System.out.println(Hero.money);

        // インスタンスを生成
        Hero h1 = new Hero();

        // 同じ値が表示される
        System.out.println(h1.money);
    }
}

```

---
- 静的メソッドの制約

```
public class Hero {

    String name;
    int hp;
    static int money; // 静的フィールド

    // 静的メソッド内ではstaticがついていないフィールドやメソッドは利用できない
    static void setRandomMoney(){

        // 静的メソッド内では静的メンバ(フィールド)しか利用できない
        Hero.money = (int) (Math.random()*100);

        // nameフィールドにアクセスしようとするが、
        // 静的メソッドはHeroインスタンスが存在しなくても呼び出せるメソッドのためエラー
        // System.out.println(this.name + "money initialized");
    }
}

```

---
- 復習

```
    // 静的フィールドの設定
    // フィールド変数の実態をインスタンスでなくクラスに用意
    // この情報は各インスタンスで共通した情報のため
    // インスタンスごとに情報を持つとメモリの無駄
    static final int MAX_HP = 50;
    static final int MAX_MP = 10;

    // コンストラクタ
    public Cleric(String name, int hp, int mp) {
        // 引数を元にインスタンス化
        this.name = name;
        this.hp = hp;
        this.mp = mp;
    }

    public Cleric(String name, int hp) {
        this(name, hp, Cleric.MAX_MP);
    }

    public Cleric(String name) {
        this(name, MAX_HP, MAX_MP);
    }

```