# has-aの関係

- フィールドにクラス型の変数を宣言することで、別のクラスのフィールドを利用できる
    - Hero has-a Sword の関係

```java
public class Main {

    public static void main(String[] args) {

        Sword s = new Sword();
        s.name = "ノーマルの剣";
        s.damage = 10;

        Hero h = new Hero();
        h.name = "Taro";
        h.hp = 100;

        // swordフィールドに生成済みの剣インスタンスのアドレスを代入
        h.sword = s;

        h.attack();
        System.out.println(h.sword.name + "を使った攻撃！ " + h.sword.damage + "のダメージ！");
        
        // Taroは攻撃した
        // ノーマルの剣を使った攻撃！ 10のダメージ！
    }
}

public class Hero {
    String name;
    int hp;
    Sword sword;

    void attack(){
        System.out.println(this.name + "は攻撃した");
    }
}
public class Sword {
    String name;
    int damage;
}
```
