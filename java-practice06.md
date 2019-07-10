- Javaの仮想世界は実際はメモリ領域
    - ヒープを数GB使って準備
    - インスタンスを生成するたびにヒープの一部が情報を格納するために確保される
    - 多くの属性を持つクラスをインスタンス化すると、消費するヒープ領域も大きい

- クラス型変数とその内容

```
// Heroクラスをインスタンス化して利用

public class Main {

    public static void main(String[] args) {

        // JVMはHero型の変数hをメモリ内に準備
        // ヒープ領域から利用していないメモリ領域を探し出して確保
        // この時点ではHero型のインスタンスだけ中に入れることができる箱が準備されているだけ
        Hero h;

        // 右辺で new Hero() が実行されるとヒープ領域から新たにメモリを確保(1500番とする)
        // h にnewを実行して確保したメモリの先頭番地(1500)を代入
        // 変数hに入っているのは1500はただの数字にすぎない
        // Heroインスタンスの情報は変数hの中にあるのでなく、
        // 入りきらないから1500番地を参照するようになっている
        // 変数hに入っているアドレス情報を「参照」という
        // 配列型やクラス型の変数に入っているのは
        // 「各データが保存されているメモリ領域の先頭番地」で総称して「参照型」とも呼ばれる
        h = new Hero();

        // 変数hに格納されているHeroのHPを設定
        // hには1500番を参照することが書いてあるので、
        // メモリ内の1500番地のインスタンスの領域にアクセスし、
        // その中のhpフィールドを書き換える
        h.hp = 100;

        // クラス型変数から番地情報を取り出し、
        // その番地にアクセスするJVMの動作を「参照の解決」や「アドレス解決」という

        }
    }
}

```

- 同一インスタンスを指す複数の変数

```

public class Main {

    public static void main(String[] args) {

        // 同じクラスでも異なるインスタンスであれば影響を受けない(インスタンスの独立性)
        // が、今回のケースは生成されるHeroは1人
        Hero h1;
        h1 = new Hero();
        h1.hp = 100;
        Hero h2;
        // インスタンスh1のために確保してあるメモリの先頭番号をコピー
        // 勇者インスタンスそのものをコピーしている訳ではない
        // h2,h1には同じ番地情報が入るため、全く同じ1人のHeroインスタンスを指している
        h2 = h1;
        h2.hp = 200;

        // 出力結果は200
        System.out.println(h1.hp);

        // 基本的には newした回数分インスタンスが生み出される
    }
}

```

---
- has-a の関係

```
public class Hero {
    String name;
    int hp;
    // フィールドにクラス型の変数を宣言
    // あるクラスが別のクラスをフィールドとして利用している関係を
    // has-a の関係(Hero has-a Sword)
    Sword sword;

    void attack(){
        System.out.println(this.name + " is attack");
        System.out.println("5 points affect!");
    }
}

public class Sword {
    String name;
    int damage;
}

public class Main {
    public static void main(String[] args) {
        Sword s = new Sword();
        s.name = "fire sword";
        s.damage = 10;

        Hero h = new Hero();
        h.name = "Mike";
        h.hp = 100;
        // swordフィールドに生成済みの剣インスタンスの番地を代入
        h.sword = s;

        System.out.println(h.sword.name + " use");
    }
}

```

- クラス型をメソッド引数や戻り値に用いる

```
public class Wizard {
    String name;
    int hp;

    // クラス型をメソッドの引数や戻り値に用いる
    // Heroのhpを回復するメソッドを持つ
    // Heroインスタンスが複数生成されている可能性もあるので(2回以上のnew)
    // 引数hとして受け取る必要がある
    void heal(Hero h){
        h.hp += 10;
        System.out.println(h.name + " :HP + 10");
    }
}

public class Main {
    public static void main(String[] args) {

        Hero h = new Hero();
        h.name = "Mike";
        h.hp = 100;

        Hero h2 = new Hero();
        h2.name = "John";
        h2.hp = 100;

        Wizard w = new Wizard();
        w.name = "Witch";
        w.hp = 50;
        w.heal(h);
        w.heal(h2);
        w.heal(h2);


        System.out.println(h.sword.name + " use");
    }
}

```

- 実はString型もクラス型
    - 標準添付されているため定義しなくても使える(java.lang.String)
    - java.lang はパッケージに宣言されている(import文の記述は不要)
    - ""で囲むとStringインスタンスが利用できるという特例
    - String s = new Strint("hello") と厳密に書くことも可能ではあるが使わない

---

- コンストラクタ
    - インスタンスの生成直後にフィールド初期値を自動設定するメソッド
    - newで生み出されたばかりのインスタンスのフィールドには何も入っていない
    - フィールドの初期値の代入でコードが長くなる
    - クラスの開発者が使えるように責任を持つべきなため

```
public class Hero {

    int hp;
    String name;

    void attack(){
        System.out.println(this.name + " is attack");
        System.out.println("5 points affect!");
    }

    // Heroクラスがnewされた直後に自動的に実行される
    // このようなメソッドをコンストラクタと呼ぶ
    Hero(){
        this.hp = 100;
    }
}

public class Main {
    public static void main(String[] args) {

        Hero h = new Hero();
        h.name = "Mike";

        System.out.println(h.hp);
        // 100(コンストラクタにより初期化されているので)
    }
}

```

- コンストラクタはプログラマが直接呼び出すものではない

- コンストラクタとみなされるための条件

1. メソッド名がクラス名と完全に等しい
2. メソッド宣言に戻り値が記述されていない

- コンストラクタに情報を渡す

```
public class Hero {

    int hp;
    String name;

    void attack() {
        System.out.println(this.name + " is attack");
        System.out.println("5 points affect!");
    }

    // 名前は生み出すインスタンスごとに異なるものにする
    Hero(String name) {
        this.hp = 100;
        this.name = name; // 引数の値でnameフィールドを初期化
    }
}

```

- 引数を受け取らないコンストラクタも同時につくる(動作テストで値を用意するのが手間な時)

```
public class Hero {

    int hp;
    String name;

    void attack() {
        System.out.println(this.name + " is attack");
        System.out.println("5 points affect!");
    }

    Hero(String name) {
        this.hp = 100;
        this.name = name; // 引数の値でnameフィールドを初期化
    }

    // オーバロードと同じ原理
    Hero(){
        this.hp = 100;
        this.name = "Dummy";
    }
}

```

- 複数のコンストラクタで同じ処理をかかないために、別コンストラクタを呼び出せるようにする

```
public class Hero {

    // コンストラクタ1
    Hero(String name) {
        this.hp = 100;
        this.name = name; // 引数の値でnameフィールドを初期化
    }

    // コンストラクタ2
    // this.クラス名と記述できない(コンストラクタを直接呼び出せないため)
    // 専用の文法で this(引数); と記述
    Hero() {
        // 1を呼び出す
        this("Dummy");
    }
}

```

---

- 静的フィールド
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

- 静的フィールドではインスタンスを1つも生み出さなくても箱が利用可能になる
    - Hero.moneyはクラス(金型)の上に作られる箱のため、実体が生み出されていなくても利用できる
    - クラスにフィールド(箱)が所属するという特徴から「クラス変数」と呼ばれる

```
public class Main {
    public static void main(String[] args) {

        // インスタンスを1つも生み出していない
        Hero.money = 100;
        System.out.println(Hero.money);
    }
}

```

- 静的メソッド

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

    // mainメソッドがstaticなのは仮想世界にまだインスタンスが存在していないから(最初に呼ばれる)
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