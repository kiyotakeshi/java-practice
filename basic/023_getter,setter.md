### getterメソッド

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

---
### setterメソッド

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

---
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

- **不正なアクセスが外部から値を設定できないように必ずsetterを書く**

---
- クラス全体に対するアクセス制御(package private)
    - 何も書かない(publicをつけない)
    - 自分と同じパッケージに属するクラスのみにアクセスを許可
    - 他のパッケージに属するクラスからそのクラスの存在自体が見えなくなるイメージ
    - その代わりクラスの名前がソースファイル名と異なっても良い
    - その代わり1つのファイルに複数宣言してもよい

- カプセル化でフィールドを保護する理由
    - **メソッドの処理内容はプログラミング段階で決定し、コンパイルされると実行中に変化しない**
    - **フィールドの値はプログラムの動作中に変動する** ため、異常な値になる危険性があるため
    - プログラムの不具合を減らすにはメソッドより、フィールドを保護することが重要

- そもそもバグとは現実世界と仮想世界が食い違ってしまうこと
    - フィールドに不正な値が入ることがない、現実世界と矛盾しないプログラムを支えるのがカプセル化