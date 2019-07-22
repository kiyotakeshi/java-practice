### 抽象クラス、インターフェースの練習

- 様々な形ある資産を管理するために有形資産という名前の抽象クラスを作成する

```
public class Book {
    private String name;
    private int price;
    private String color;
    private String isbn;

    // コンストラクタ
    public Book(String name, int price, String color, String isbn) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.isbn = isbn;
    }

    // getterメソッド
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getColor() {
        return this.color;
    }

    public String getIsbn() {
        return this.isbn;
    }

}

```

```
public class Computer {
    private String name;
    private int price;
    private String color;
    private String makerName;

    // コンストラクタ
    public Computer(String name, int price, String color, String makerName) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.makerName = makerName;
    }

    // getterメソッド
    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getColor() {
        return this.color;
    }

    public String getMakerName() {
        return this.makerName;
    }
}

```

- 抽象クラスを作成(TangibleAsset)

```
// 抽象クラス
public abstract class TangibleAsset {

    private String name;
    private int price;
    private String color;

    // コンストラクタ
    public TangibleAsset(String name, int price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    // getterメソッド
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }
}

```

- 抽象クラスを継承

```
public class Book extends TangibleAsset {

    private String isbn;

    // コンストラクタ
    public Book(String name, int price, String color, String isbn) {

        // superとは親インスタンスを表す予約語
        // これを利用すると親インスタンス部の
        // メソッドやフィールドに子インスタンスからアクセスできる
        super(name, price, color);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return this.isbn;
    }

}

```

```
public class Computer extends TangibleAsset {

    private String makerName;

    // コンストラクタ
    public Computer(String name, int price, String color, String makerName) {

        // 親クラスのメソッドやフィールドにアクセス
        super(name, price, color);
        this.makerName = makerName;
    }

    // getterメソッド
    public String getMakerName() {
        return this.makerName;
    }
}

```

---
- 形のない無形資産(IntangibleAsset)も管理する(特許権,Patentなど)
    - 親クラスとなる Assetクラスをつくる

```
// インターフェースは全てのメソッドが抽象メソッドでフィールドを一つも持たない時に使用可能(今回は違う)
// 抽象メソッドを持つ抽象クラスを宣言
public abstract class Asset {
    private String name;
    private int price;

    // コンストラクタ
    public Asset(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // getterメソッド
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

```

```
// 抽象クラス
public abstract class TangibleAsset extends Asset {

    private String color;

    // コンストラクタ
    public TangibleAsset(String name, int price, String color) {
        super(name, price);
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}

```

---
- 資産かは関係なく、形のあるもの(Thing)の重さを定義する
    - getWeight(),setWeight()を持つインターフェースを定義

```
public interface Thing {
    double getWeight();
    void setWeight(double weight);
}

```

- 有形資産(TangibleAsset)は資産(Asset)の一種であり形あるもの(Thing)の一種でもある

```
// 抽象クラス
public abstract class TangibleAsset extends Asset implements Thing {
    
    private String color;
    private double weight;

    // コンストラクタ
    public TangibleAsset(String name, int price, String color) {
        super(name, price);
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

```