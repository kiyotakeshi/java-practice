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