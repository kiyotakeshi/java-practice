- 多態性(ポリモーフィズム)
    - ざっくりと捉えることでメリットを享受する
    - 厳密に言えばSuperHeroだけどざっくりとはHeroだよね的な
    - 現実においてもレンタカーで初めて乗る車でも問題なく運転できる(ざっくりとは同じ車)
    - 多態性に専用の文法はない


- SuperHeroのインスタンスを生成
    - SuperHero型の変数hにSuperHeroインスタンスが入っている

```
SuperHero h = new SuperHero();

```

- ざっくりとCharacterとして捉える書き方
    - 左辺の型がCharacterに変わった
    - インスタンスはSuperHeroだが、箱の正面にはCharacterと書いているイメージ
    - 本当はSuperHeroインスタンスだが、Characterとして捉えることが可能
    - 多態性の活用には箱の型と中身の型が異なることが関係
    - インスタンスをどのように捉えるかはどの型の変数に代入するか(箱の型)できまる

```
// SuperHero is-a Character のため代入可能
// 絵に描いてみて嘘にならないインスタンスの代入は可能(Characterの箱にSuperHero)
// is-a の関係をJavaで記述するのが、extendsやimplements

Character c = new SuperHero();

```

- 箱の型に抽象クラスやインターフェースを使う

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

- 同一である存在に対して複数の異なる捉え方ができる
    - あいまいな「紙」は用途が限定される
    - 少し具体的な「絵」は紙の用途に加え、見て楽しむことができる
    - 具体的な「紙幣」は紙の用途に加え、絵の用途に加え、物が買える
    - あいまいで抽象的な物ほど用途は限定され、具体的に捉えるほど用途が増えていく

- 捉え方が変わると利用法も変わる
    - 呼び出せるメソッドの変化

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

- メソッドを呼び出せた場合う動く処理

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

        // Monster型のmとSlime型のsは箱の表面の表記の違いはあるものの、
        // 中身はあくまでもスライム
        s.run(); // Slime escaped
        m.run(); // Slime escaped
    }
}

```