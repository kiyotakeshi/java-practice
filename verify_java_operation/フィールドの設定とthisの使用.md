# フィールドの設定とthisの使用

- Mainクラス
    - 仮想世界の中で起こる処理を書く
    - 他のクラスを呼び出す、神のようなクラス

```java
public class Main {

    public static void main(String[] args) {

        // Hero型(クラス型)の変数が利用できるようになる
        Hero h = new Hero();
        
        h.name = "Taro";
        h.hp = 100;

        h.sleep();
        // Taroは回復した
        // HPは100です
    }
}
```

- Heroクラス

```java
public class Hero {

    // クラスブロック内で宣言された変数を「フィールド」と呼ぶ
    String name;
    int hp;

    void sleep(){
        // this.は自分自身のインスタンスを意味する
        System.out.println(this.name + "は回復した");
        System.out.println("HPは" + this.hp + "です");
    }
}
```
