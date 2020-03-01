# OOPでつくると見通しがよくなる

- Mainクラスのmainメソッドが台本のようにわかりやすい
    - mainメソッドには細かい処理は記載されていない

```java
public class Main {

    public static void main(String[] args) {

        // 勇者をインスタンス化(生成)
        Hero h = new Hero();

        // フィールドに初期値を設定
        h.name = "Taro";
        h.hp = 100;

        System.out.println("勇者" + h.name + "が誕生しました！");

        h.sit(5);
        h.slip();
        h.sit(25);
        h.run();

//      勇者Taroが誕生しました！
//      Taroは5秒座った
//      HPが5ポイント回復した
//      Taroは転んだ
//      5のダメージ
//      Taroは25秒座った
//      HPが25ポイント回復した
//      Taroは逃げ出した
//      GAMEOVER
//      最終HPは125でした！
    }
}

public class Hero {

    String name;
    int hp;

    void sit(int sec) {
        this.hp += sec;

        System.out.println(this.name + "は" + sec + "秒座った");
        System.out.println("HPが" + sec + "ポイント回復した");
    }

    void slip() {
        int damage = 5;
        this.hp -= damage;
        System.out.println(this.name + "は転んだ");
        System.out.println(damage + "のダメージ");
    }

    void run() {
        System.out.println(this.name + "は逃げ出した");
        System.out.println("GAMEOVER");
        System.out.println("最終HPは" + this.hp + "でした！");
    }
}
```
