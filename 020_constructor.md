### コンストラクタ

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

---
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