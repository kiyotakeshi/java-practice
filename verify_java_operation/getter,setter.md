# getter,setterの活用

- フィールドはどこからも書き換えられるとバグの原因になるので、privateにする

- メソッドは、クラス内部だけで使うもの以外は、publicにする

- **privateにしたフィールドに外部から読み書きするために必要なものが、setter,getter**

```java
public class Hero {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {

        // フィールドにセットする値を検査できる
        if(name == null){
            throw new IllegalArgumentException("名前がnullです");
        }
        if (name.length() <= 1){
            throw  new IllegalArgumentException("名前が短すぎます");
        }
        this.name = name;
    }
}

public class Main {

    public static void main(String[] args) {

        Hero h = new Hero();

        h.setName("Taro");
        System.out.println("Heroの名前は" + h.getName() + "です");
        // Heroの名前はTaroです

        h.setName("");
        System.out.println("Heroの名前は" + h.getName() + "です");
        // Exception in thread "main" java.lang.IllegalArgumentException: 名前が短すぎます
        // at Hero.setName(Hero.java:16)
        // at Main.main(Main.java:11)
    }
}
```
