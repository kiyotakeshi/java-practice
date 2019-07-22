### 多態性(ポリモーフィズム)

- ざっくりと捉えることでメリットを享受する
    - 厳密に言えばSuperHeroだけどざっくりとはHeroだよね的な
    - 現実においてもレンタカーで初めて乗る車でも問題なく運転できる(ざっくりとは同じ車)

- 多態性に専用の文法はない

```
// SuperHeroのインスタンスを生成
// SuperHero型の変数hにSuperHeroインスタンスが入っている

SuperHero h = new SuperHero();

// ざっくりとCharacterとして捉える書き方
// 左辺の型がCharacterに変わった
// インスタンスはSuperHeroだが、箱の正面にはCharacterと書いているイメージ
// 本当はSuperHeroインスタンスだが、Characterとして捉えることが可能

Character c = new SuperHero();

```

- 多態性の活用には箱の型と中身の型が異なることが関係

- インスタンスをどのように捉えるかはどの型の変数に代入するか(箱の型)できまる
    - SuperHero is-a Character のため代入可能
    - 絵に描いてみて嘘にならないインスタンスの代入は可能(Characterの箱にSuperHero)
    - is-a の関係をJavaで記述するのが、extendsやimplements

---
- 箱の型に抽象クラスやインターフェースを使う
    - **抽象クラスやインターフェースからインスタンスを生み出すことはできないが、型を利用することは可能**

```
public interface Life {
}

```

```
public class Main {
    public static void main(String[] args) {
        // Life型の変数(Lifeが入っていると書かれた箱)に
        // Wizardインスタンスを代入はできる
        // 抽象クラスやインターフェースからインスタンスを生み出すことはできないが、
        // 型を利用することは可能
        Life lf = new Wizard();
    }
}

```

---
- 同一である存在に対して複数の異なる捉え方ができる
    - あいまいな「紙」は用途が限定される
    - 少し具体的な「絵」は紙の用途に加え、見て楽しむことができる
    - 具体的な「紙幣」は紙の用途に加え、絵の用途に加え、物が買える
    - あいまいで抽象的な物ほど用途は限定され、具体的に捉えるほど用途が増えていく

---
- 呼び出せるメソッドの変化
    - 捉え方が変わると利用法も変わる

```
// 抽象クラスとしてCharacterを宣言
public abstract class Character {

    String name;
    int hp;

    public abstract void attack(Matango m);

    public void run(){
    }
}

```

```
public class Wizard extends Character {

    int mp;

    public void attack(Matango m) {
        System.out.println(this.name + " attack");
        System.out.println("3points damage");
        m.hp -= 3;
    }

    public void fireball(Matango m) {
        System.out.println(this.name + " release fire-ball");
        System.out.println("20points damage");
        m.hp -= 20;
        this.mp -= 5;
    }
}

```

```
public class Main {
    public static void main(String[] args) {
        Wizard w = new Wizard();
        Matango m = new Matango();

        // Wizardはメソッドを持っているのでインスタンス化してattack,fireballを使える
        // w.name = "Mike";
        // w.attack(m);
        // w.fireball(m);

        // WizardはCaracterの一種なのでCharacter型の変数(Character型の箱)に代入
        // 箱cの中身は何かのキャラクター程度にざっくりと捉えられる
        // 確実に言えることは箱に入っているのはキャラクターの一種であることだけ
        Character c = w;
        c.name = "Mike";

        // Characterクラスが持っているためどんなキャラクターでも呼び出せる
        c.attack(m);

        // 以下の行ではエラーが発生する
        // Charactor型の箱に入っているが中身は魔法使いなのだが、
        // 呼び出す側が魔法使いと思っていないから呼び出せない
        // Charactor型の変数に代入することは中身のインスタンスを
        // 何かのキャラクターとざっくり捉えているにすぎないから
        // つまりCaracterが持つメソッドしか呼び出せない
        // c.fireball(m);

    }
}

```

---
- メソッドを呼び出せた場合に動く処理

```
public abstract class Monster {
    public void run(){
        System.out.println("Monster escaped");
    }
}

```

```
public class Slime extends Monster {
    public void run(){
        System.out.println("Slime escaped");
    }
}

```

```
public class Main {
    public static void main(String[] args) {
        Slime s = new Slime();
        Monster m = new Slime();

        // 今回は抽象クラスのMonsterにrun()メソッドがあり、それをSlimeでオーバライドしている
        // Monster型のmとSlime型のsは箱の表面の表記の違いはあるものの、中身はあくまでもスライム
        s.run(); // Slime escaped

        m.run(); // Slime escaped
    }
}

```

- **箱の型(変数の型)はどのメソッドを呼べるかを決定する**
- **中身の型(インスタンス)はメソッドが呼ばれたらどう動くかを決定する**

---
- 捉え方を変更する

```
// この処理をすることで、Wizardが持つ固有のメソッドは呼べなくなる
// Charactorも持つメソッドであれば、Wizardでオーバライドしたものが使えるが…
Charactor c = new Wizard();

// インスタンスをWizard型変数にするのはエラーになる
// コンパイラはプログラムを1行ずつ解釈するため
// cにWizard以外のインスタンスが入っている可能性もある
// 例えばHeroが入っていたら、Wizard型の変数にHeroインスタンスが入っている嘘の構図になる
// Wizard w = c;

```

- Wizardとして捉え直す

```
Character c = new Wizard();

// 強制的な型変換に使う、キャスト演算子を使用
Wizard w = (Wizard) c;

// Character c = new Wizard();
// ClassCastException のエラーが出る
// キャストによる強制代入の結果、嘘の構図になったため強制停止
// Hero h = (Hero) c;

```

---
- 安全にキャストできるかを判定する

```
// cの中身がSuperHeroならキャストする
if (c instanceof SuperHero){
    SuperHero h = (SuperHero) c;
    h.fly();
}

```

---
### 多態性のメリット

- ざっくりと捉えることで呼び出せるメソッドは減るが、メリットもある

- 同一視して配列を利用できる
    - 5人のキャラクターが旅をする
    - 宿に泊まり、全員のHPを50ずつ回復するプログラムを書く

```
// 改良前のコード
// コードに重複が多い
// パーティの人数が増えた際に宿泊処理の行を追加する必要がある
// インスタンス名が変更になった際にも修正が必要

public class Main {
    public static void main(String[] args) {

        Hero h1 = new Hero();
        Hero h2 = new Hero();
        Thief t1 = new Thief();
        Wizard w1 = new Wizard();
        Wizard w2 = new Wizard();

        // 宿屋にとまる
        h1.setHP(h1.getHp() + 50);
        h2.setHP(h2.getHp() + 50);
        t1.setHP(t1.getHp() + 50);
        w1.setHP(w1.getHp() + 50);
        w2.setHP(w2.getHp() + 50);

    }
}

```

```
// 改良後
// 5人のインスタンスをキャラクターとしてざっくり扱うことで配列にまとめる

public class Main {
    public static void main(String[] args) {

        Character[] c = new Character[5];
        c[0] = new Hero();
        c[1] = new Hero();
        c[2] = new Thief();
        c[3] = new Wizard();
        c[4] = new Wizard();

        // ループをまわして一括で宿泊の処理をする
        for (Character ch : c) {
            ch.setHp(ch.getHp() + 50)
        }
    }
}

```

- 同一視してざっくりとした引数を受け取る

```
// 従来の書き方だと攻撃対象ごとにメソッドを作る必要があった
public class Hero extends Character {

    public void attack(Matango m) {
        System.out.println(this.name + " attack");
        System.out.println("10points damage");
        m.hp - = 10;
    }

    public void attack(Goblin m) {
        System.out.println(this.name + " attack");
        System.out.println("10points damage");
        g.hp - = 10;
    }
}

```

```
// 攻撃対象をざっくりとモンスターにすることでコードの重複を排除
// Monsterクラスを継承しているSlime,Goblinなどのモンスターを攻撃対象にできる
public class Hero extends Character {

    public void attack(Monster m) {
        System.out.println(this.name + " attack");
        System.out.println("10points damage");
        m.hp -= 10;
    }
}

```

---
- **ざっくりまとめて扱う、メソッドの動作は中身の型に従う、を組み合わせる**
    - これぞ多態性の真価
    - 指示する側はいいかげんにうごく、動く側は自分のやり方で動く

```
public class Main {
    public static void main(String[] args) {

        Monster[] monsters = new Monster[3];
        monsters[0] = new Slime();
        monsters[1] = new Goblin();
        monsters[2] = new DeathBat();

        for (Monster m : monsters){

            // それぞれのrunメソッドが呼び出される
            // 呼び出し側は相手を同一視し、同じように呼び出す
            // 呼び出される側は自分に決められた動きをする
            // 同じ呼び出しで異なる状態を生み出すことがあるため「多態性」と呼ばれる
            m.run();
        }
    }
}

```

---

- 復習

```
// 1

// X obj = new A();でAインスタンスを生成した後、
// 変数objに対して呼ぶことのできるメソッドは
// a();

// 2

// Y y1 = new A();
// Y y2 = new B();
// としてAとBのインスタンを生成した後、
// y1.a();
// y2.a(); を実行した場合に画面に表示される内容は
// AaBa

```


```
public final class A extends Y{

    public void a() {
        System.out.println("Aa");
    }

    public void b(){
        System.out.println("Ab");
    }

    public void c(){
        System.out.println("Ac");
    }
}
```

```
public class B extends Y {
    public void a(){
        System.out.println("Ba");
    }
    public void b(){
        System.out.println("Bb");
    }
    public void c(){
        System.out.println("Bc");
    }
}


```

```
public interface X {
    void a();
}

```

```
public abstract class Y implements X {
    public abstract void a();
    public abstract void b();
}

```

```
// 3

// A,Bクラスのインスタンスを一つずつ生成し、要素数2からなる単一の配列に格納
// 配列の中身をループで順に取り出し、それぞれのインスタンスのb()メソッドで呼ぶ

```

```
public class Main {
    public static void main(String[] args) {

        // bメソッドを使えるのはYのためY型で配列を宣言
        Y[] array = new Y[2];
        array[0] = new A();
        array[1] = new B();

        // 配列の中身を取り出しY型の変数yに格納
        for (Y y : array) {

            // bメソッドを呼び出す、A,Bがオーバライドした内容を出力する
            y.b();
        }
    }
}

```