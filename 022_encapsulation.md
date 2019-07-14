### カプセル化

- オブジェクト指向の三大要素(カプセル化、継承、多態性)

- フィールドへの読み書きやメソッドの呼び出しを制限する機能
    - 特定のクラスからしか呼び出せないようにする(アクセス制御)

---
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

- 1度も戦っていないのにHPが -100になっている

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

---
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
