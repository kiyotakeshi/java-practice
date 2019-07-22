### 高度な継承

- 別の立場について考え直す

    - 現在の目の前のプログラム開発に必要なクラスを作る開発者(従来の継承)
        - 既定クラスを継承し子クラスをつくる

    - 未来に備え、別の開発者が将来利用するであろうクラスを準備しておく(高度な継承)
        - 安心便利に使ってもらえる親となるクラスを作っておく

- 高度な継承の具体例

    - 開発者がHeroやWizardなど似たクラスを一から作っている
    - 各キャラクタのクラスではname,hpなどのフィールドとattack(),run()などのメソッドは共通
    - 今後もキャラクタが増える予定
    - 各クラスに共通するフィールドやメソッドを持つCharacterクラスをつくる
    - 今すぐ必要な実際に利用されるクラスをつくる開発者の立場でなく、継承元となるクラスをつくる立場

---

- 継承の材料となるクラスに関する不都合と心配要素

- メソッドの内容を確定できない(詳細未定メソッドがある)

```
public class Character {
    String name;
    int hp;

    public void run(){
        System.out.println(this.name + "escape");
    }

    public void attack(Matango m){
        System.out.println(this.name +" attacks!");

        // ダメージをどれくらいにしたらいいかわからない
        // 将来的に完成するキャラクタによって与えるダメージが異なるため
        // m.hp -= ??
    }
}

```
- 内容を確定できないメソッドを記述しないでおくと、継承先のクラスで追加し忘れる(攻撃できないキャラになる)
- Hp属性を持ち、攻撃できるという共通前提にもとづきCharacterクラスを作り始めたのに本末転倒
- 現実世界とJavaコードの世界に矛盾が生じる余地があると不具合が生じる

---
- 完全ではない対策(以下の不都合が起きる)
    - オーバーライドし忘れると内容が空のメソッドが呼び出され何も起きない
    - コンパイル時にエラーも出ないタチの悪いエラー
    - 呼ばれても何もしないメソッドと何かしたくても未定で記述できないものの区別がつかない
    - オーバーライドを設定していても、間違えてCharcterクラス(メソッドを空にしているクラス)を利用してしまうかも

```

    // メソッドの中身を空にする
    public void attack(Matango m){
    }

// 呼び出し先でオーバーライド
public class Hero extends Character {

    // Characterのattackメソッドをオーバーライド
    public void attack(Matango m) {
        System.out.println(this.name + " attacks!");
        m.hp -= 10;
    }
}

```

```
public class Main {
    public static void main(String[] args) {

        // Hero,WizardでなくCharactorをnewしてしまった
        // 継承の材料として使うはずのクラスからインスタンスを作ってしまった
        Character c = new Character();
        Matango m = new Matango('A');

        // オーバーライドされていないので何も動かない
        c.attack(m);
    }
}

```

- 未来の開発者のために準備しておくクラスは未完成の部分が残っている
- 一方で一部でも未完成が残っている設計図(クラス)から実体を生み出してはならない

- クラスの利用方法はそもそも二つ
    - 未来の開発者はどちらの用途でも使えてしまう…(extendsだけにしてほしいが)

1. インスタンスを生み出すために利用(new)
2. 別クラスを開発する際に継承元として利用(extends)

---
### 抽象クラス

- 「何もしないとの区別ができない」への対策
    - 詳細未定メソッドを記述する専用の構文が用意されている

```
// 今の段階ではシンタックスエラー
public class Character {

    // メソッドは宣言するが、内容は確定できないので
    // メソッドの内部の処理は記載しないという表明
    // メソッド宣言の後ろには{}も不要で代わりにセミコロン
    // 抽象メソッドと呼ばれる
    public abstract void attack(Matango m);
}

```

- 「継承専用クラスをnewされる」への対策
    - 未完成部分(抽象メソッド)を含むクラスはそのことを宣言する
        - 宣言しないとコンパイルエラーになる
        - 抽象クラスはnewによるインスタンス化が禁止される

```
// 抽象クラスとしてCharacterを宣言
public abstract class Character {

    String name;
    int hp;

    public void run() {
        System.out.println(this.name + " escape");
    }

    public abstract void attack(Matango m);
}

```

- 「開発者が詳細未定メソッドをオーバーライドし忘れる」への対策
    - 隠れた抽象メソッドによりオーバーライドを忘れるとコンパイルエラーになる
    - 継承先(Dancer)は継承元(Character)の全てのメンバを継承している
    - 継承先のソースコードに抽象メソッドがなくても親クラスから継承して抽象メソッドを持っている

```
// このままだとコンパイラエラー
public class Dancer extends Character{

    public void dance(){
        System.out.println(this.name + " dance");
    }
}
// attack()をオーバーライドし忘れている

```

- コンパイルエラーを解消するために(どちらかを実行)
1. Dancerクラスの宣言にabstractをつけて抽象クラスにする
2. Dancerクラス内部の未完成部分を全て無くす

```
public class Dancer extends Character {

    public void dance() {
        System.out.println(this.name + " dance");
    }

    // 親の詳細未定のattack()をオーバーライド
    // メソッドの内容を確定させることを「実装(implements)する」と表現する
    public void attack(Matango m) {

        System.out.println(this.name + " attack");
        System.out.println("3points damage");
        m.hp -= 3;
    }
}

```

---
- 多階層の抽象継承構造
    - 抽象クラスを継承した子クラスで全ての抽象メソッドをオーバーライドして実装する必要はない
    - ただし、全ての抽象メソッドの処理内容が確定しなければabstractをはずせない(newできない)


```
Master: hp, mp, attack(未定), run(未定)
WalkingMonster: hp(継承されたフィールド), mp(継承されたフィールド), attack(未定), run(走って逃げる)
FlyingMonster: hp(継承されたフィールド), mp(継承されたフィールド), attack(未定), run(飛んで逃げる)

→ 継承した (Walking|Flying)Monster は抽象メソッドを持っているため抽象クラス(abstruct宣言しnewできない)

Goblin: hp(継承されたフィールド), mp(継承されたフィールド), attack(斬りつける), run(走って逃げる)
WarWolf: hp(継承されたフィールド), mp(継承されたフィールド), attack(噛み付く), run(走って逃げる)

→ ここで抽象メソッドがなくなり、newして利用することができる
→ 継承が繰り返されるたびに具体化していきメソッドの処理内容が実装されていく

```