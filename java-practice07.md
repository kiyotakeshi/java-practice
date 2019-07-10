- カプセル化
    - オブジェクト指向の三大要素(カプセル化、継承、多態性)
    - フィールドへの読み書きやメソッドの呼び出しを制限する機能
    - 特定のクラスからしか呼び出せないようにする(アクセス制御)

- アクセス制御されていないプログラム

```
public class Hero {

    String name;
    int hp;
    Sword sword;
    static int money;

    void bye(){
        System.out.println("Hero said goodbye");
    }

    void die(){
        System.out.println(this.name + " is dead");
        System.out.println("GAMEOVER");
    }

    void sleep(){
        this.hp = 100;
        System.out.println(this.name + "slept and heal");
    }

    void attack(Matango m){
        System.out.println(this.name + " is attacking");
        System.out.println("Matango" + m.suffix + "is attacking(-2pt)");

        // 反撃を受けてhpが2減る
        this.hp -+ 2;
        if (this.hp <= 0){
            this.die();
        }
    }

}

```

- 一度も戦っていないのにHPが -100になっている

```
public class Inn{
    void checkIn(Hero h){
        // typoでマイナスがついていた
        // コンパイルエラーにならないし、代入可能
        h.hp = -100;
    }
}

```

- 城で会話すると急死する

```
public class King {

    void talk(Hero h) {
        System.out.println("King:Welcome to my country,Hero" + h.name);
        // 不具合のせいで勇者が死ぬ
        h.die();
    }
}

```

- メンバに対するアクセス制御

1. private
    - 自分自身のクラスのみアクセス可

2. package private
    - 自分と同じパッケージに属するクラスのみアクセス可

3. protected
    - 自分と同じパッケージと自分を継承した子クラスのみアクセス可

4. public
    - 自分自身のクラスのみアクセス可


```
public class Hero {

    String name;
    // 他のクラスから変更できる必要はない
    // 宿屋クラスではhpフィールドに直接値を代入する代わりに
    // sleep()メソッドを呼び出すようにすれば良い
    private int hp;
    Sword sword;
    static int money;

    // 何もつけていないと package private を指定したとみなされる
    // 同じパッケージに属するクラスから呼び出し可能になる
    void sleep(){
        // 自身のクラスからthis.での読み書きは可能
        this.hp = 100;
        System.out.println(this.name + "slept and heal");
    }

    // 危険なメソッドも呼び出せないようにする
    // 外部のクラスからは呼べないが、同じクラスのメソッドからは呼べる
    private void die(){
        System.out.println(this.name + " is dead");
        System.out.println("GAMEOVER");
    }

    // 色々なクラスからattack()メソッドは呼ばれても問題ない
    public void attack(Matango m){
        System.out.println(this.name + " is attacking");
        System.out.println("Matango" + m.suffix + "is attacking(-2pt)");

        // 反撃を受けてhpが2減る
        this.hp -+ 2;
        if (this.hp <= 0){
            this.die();
        }
    }
・・・

```

- ひとまず参考にするアクセス修飾の定石
    - クラスは特に理由がない限りpublic

    - フィールドは全てprivate
        - 外部のクラスからはメソッドを呼ぶことでフィールドの値を変更できる
        - これまでの例だと、attack()やsleep()など
        - フィールドにはメソッド経由でアクセスすることでバグを特定しやすくなる

    - メソッドは全てpublic
        - die()などクラス内部だけで使うメソッドをprivateに指定し直す

---

- getterメソッド
    - フィールドの中身を呼び出し元に返すだけの単純なメソッド

```
public class Hero {

    // フィールドの値はprivateにする
    private String name;
    private int hp;
    Sword sword;
    static int money;

    void bye(){
        System.out.println("Hero said goodbye");
    }

    // 外部から呼び出せるようにする
    // public 値を取り出すフィールドの型 getフィールド名()
    public String getName(){
        return this.name
    }

・・・

}

```

```
public class King {
    void talk(Hero h){
        System.out.println("King: Welcome to my country,Hero " + h.getName());
    }
}

```

- setterメソッド
    - ある特定のフィールドに指定された値を代入するだけのメソッド

```
public class Hero {

・・・

    // setter
    // public void setフィールド名(フィールドの型 任意の変数名)
    public void setName(String name){
        this.name = name;
    }

・・・

}

```

- カプセル化の効果
    - readonlyのフィールドを定義できる
    - フィールド名を書き換える時にgetter,setterの this. を書き換えるだけでいい

```
// ない時

public class Hero {

・・・

    String name;

・・・

}

// ある時

public class Hero {

・・・
    private String name;

    // あまり使わないが、getterを消してwrite onlyにすることもできる
    public String getName(){
        return this.name;
    }

    // setterを削除するとread onlyのフィールドにできる
    public void setName(String name){
        this.name = name;
    }

・・・
}

```

- フィールドへのアクセスを制限できる
    - setterで設定しようとしている値の妥当性をチェック

```
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("null is invalid name")
        }
        if (name.length() <= 1) {
            throw new IllegalArgumentException("too short name")
        }
        if (name.length() >= 8) {
            throw new IllegalCharsetNameException("too long name")
        }

        // 妥当な値のため代入して問題なし
        this.name = name;
    }

```

```
public class Main {
    public static void main(String[] args) {

        Hero h = new Hero();
        // 長さ0の名前のためエラーになる
        // h.setName("");
}

```

- 不正なアクセスが外部から値を設定できないように必ずsetterを書く

---
