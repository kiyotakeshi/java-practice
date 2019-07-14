### カプセル化、getter,setterの復習

- カプセル化する
    - Wizardクラスはコンパイルエラーになるが気にしない

```
public class Wand {

    // フィールドの値は他クラスから直接的に書き換え不可
    private String name; // 杖の名前
    private double power; // 杖の魔力
}

```

```
public class Wizard {

    private int hp;
    private int mp;
    private String name;
    private Wand wand;

    // メソッドは他から呼び出せて良い
    public void heal(Hero h){

        // 基本回復ポイント
        int basePoint = 10;

        // 杖による増幅
        int recovPoint = (int) (basePoint * this.wand.power);

        // Heroのhpを回復
        h.setHp(h.getHp() + recovPoint);

        System.out.println(h.getName() + "HP " + recovPoint + "points +");
    }
}

```

- getter,setterメソッドを作成

```
public class Wand {
    private String name; // 杖の名前
    private double power; // 杖の魔力

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPower(){
        return this.power;
    }

    public void setPower(double power){
        this.power = power;
    }
}

```

```
public class Wizard {

    private int hp;
    private int mp;
    private String name;
    private Wand wand;

    private void heal(Hero h) {

        // 基本回復ポイント
        int basePoint = 10;

        // 杖による増幅
        int recovPoint = (int) (basePoint * this.wand.power);

        // Heroのhpを回復
        h.setHp(h.getHp() + recovPoint);

        System.out.println(h.getName() + "HP " + recovPoint + "points +");
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wand getWand() {
        return this.wand;
    }

    public void setWand(Wand wand) {
        this.wand = wand;
    }
}

```
- 引数の妥当性をチェック

```
public class Wand {
    private String name; // 杖の名前
    private double power; // 杖の魔力

    public String getName() {
        return this.name;
    }

    // 名前は3文字以上
    public void setName(String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalArgumentException("3 charactors at least");
        } else {
            this.name = name;
        }
    }

    public double getPower(){
        return this.power;
    }

    // 魔力による増加率は 0.5~100.0の範囲
    public void setPower(double power) {
        if (power < 0.5 || power > 100.0) {
            throw new IllegalArgumentException("settings is invalid");
        } else {
            this.power = power;
        }
    }
}

```

```
import java.nio.charset.IllegalCharsetNameException;

public class Wizard {

    private int hp;
    private int mp;
    private String name;
    private Wand wand;

    private void heal(Hero h) {

        int basePoint = 10; // 基本回復ポイント
        int recovPoint = (int) (basePoint * this.wand.power); // 杖による増幅
        h.setHp(h.getHp() + recovPoint); // Heroのhpを回復
        System.out.println(h.getName() + "HP " + recovPoint + "points +");
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        if (mp < 0) {
            throw new IllegalArgumentException("value is invalid")
        } else {
            this.mp = mp;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 3) {
            throw new IllegalCharsetNameException("3 charactors at least")
        } else {
            this.name = name;
        }
    }

    public Wand getWand() {
        return this.wand;
    }

    // 杖はnullであってはならない
    public void setWand(Wand wand) {
        if (wand == null) {
            throw new IllegalArgumentException("null cannot use")
        } else {
            this.wand = wand;
        }
    }
}

```