### コレクション

- データをどのようにまとめて扱うかを「データ構造」という
- コレクションフレームワークにより様々なデータ構造を扱える

- ArrayList
    - コレクションクラスでは宣言の際に準備する箱の数を指定しない
    - データを追加する際に自動的に箱を追加

```
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//      ArrayListを確保
//      <>ジェネリックと呼ばれる表現で記載
//      String部分を変えるとHero,Dateなどどんな種類のオブジェクトも格納できる
        ArrayList<String> names = new ArrayList<String>();

        names.add("Mike");
        names.add("Suzuki");
        names.add("Hideki");

        System.out.println(names.get(1)); // Suzuki

    }
}
```

- コレクションクラスはインスタンスでないもの(基本データ型の情報)を格納できない
    - int型の情報はIntegerインスタンスに変換して格納

```
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//      Integer型でArrayListを宣言
        ArrayList<Integer> points = new ArrayList<Integer>();

//      自動的にIntegerに変換、格納される
        points.add(10);
        points.add(80);
        points.add(75);

//      拡張for(enhanced-for)も使用可能
        for (int i : points) {
            System.out.println(i);
        }
    }
}
```

- リストの中身を取り出して処理する

```
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();

        names.add("mike");
        names.add("john");
        names.add("tom");

//      for
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }

        System.out.println("-------------");

//      enhanced-for
        for (String s : names) {
            System.out.println(s);
        }

        System.out.println("-------------");

//      箱を指す矢印であるインスタンスをJVM内の仮想世界に生み出す(newはつかわない)
//      リスト変数自体のiterator()メソッドを呼び出す
//      iteratorの取得
//      矢印はリストの先頭よりも前を指す
        Iterator<String> it = names.iterator();

        while (it.hasNext()) {
            String e = it.next();
            System.out.println(e);
        }
    }
}
```

---
- LinkedList(連結リスト)
    - 要素の挿入や削除が得意
    - リストの途中に要素が挿入や削除される処理はArrayListは苦手(玉突き式で後ろのリストを全て前にずらすため)
    - 削除対象の1つ前の要素に対して次の箱を示す連結リストの情報を書き換えるだけでよい
    - get()メソッドを使ったN番目の要素の取り出しが苦手(先頭から順に辿って行かなければならないため)
    - 末尾付近の要素をadd(),remove()するときもArrayListより時間がかかることが多い

- ArrayList,LinkedListはjava.util.Listインターフェースを実装したもの

---
### さまざまなコレクションクラス

- HashSet
    - 集合を扱う
    - 重複は許されない
    - 順序関係がない

```
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<String> colors = new HashSet<String>();
        colors.add("red");
        colors.add("yellow");
        colors.add("black");
        colors.add("red");

//      重複しているものの個数はカウントされない
        System.out.println("color: " + colors.size());
//      color: 3
    }
}
```

---
- TreeSet
    - 辞書順で取り出せる

```
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Set<String> words = new TreeSet<String>();
        words.add("dog");
        words.add("cat");
        words.add("wolf");
        words.add("panda");

        for (String s : words) {
            System.out.print(s + "->");
//          cat->dog->panda->wolf->
        }
    }
}
```

---
- Map(辞書型)
    - key,valueのペアで格納するデータ構造
    - キーを指定して値を読み書きする
    - 値の重複はできるが、キーは一意のものでないといけない

```
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> prefs = new HashMap<String, Integer>();

//      ペアで値を格納
        prefs.put("kyoto", 255);
        prefs.put("tokyo", 1261);
        prefs.put("kumamoto", 181);

//      キーを指定し値を取得
        int tokyo = prefs.get("tokyo");
        System.out.println("tokyo population :" + tokyo);

        prefs.remove("kyoto");
        prefs.put("kumamoto", 182);
        int kumamoto = prefs.get("kumamoto");
        System.out.println("kumamoto population:" + kumamoto);
    }
}
```

- HashMapの情報を拡張forで取り出す
    - キーの一覧を取得、各キーについて対応する値を取得

```
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> prefs = new HashMap<String, Integer>();
        prefs.put("kyoto", 255);
        prefs.put("tokyo", 1255);
        prefs.put("kumamoto", 182);

//      キーの一覧を取得
        for (String key : prefs.keySet()) {

//          各キーに対応する値を取得
            int value = prefs.get(key);
            System.out.println(key + " population:" + value);

//          格納したペア同士の順序を保証しないコレクション
//          kyoto population:255
//          tokyo population:1255
//          kumamoto population:182
        }
    }
}
```
- HashMapは格納したペア同士の順序を保証しないコレクション

---
- リストに格納されているのは参照

```
import java.util.ArrayList;
import java.util.List;

class Hero {
    public String name;
}

public class Main {
    public static void main(String[] args) {

//      インスタンスの生成、メモリ上の番地にhが代入されている
        Hero h = new Hero();
        h.name = "Mike";

        List<Hero> list = new ArrayList<Hero>();

//      hをリストに格納
//      リストに格納されているのはhのアドレス情報
//      list.get(0)とhは全く同一のインスタンスを指す
        list.add(h);

//      格納後にhを書き換え
//      list.get(0).nameと同一のものを書き換えていることになる
        h.name = "sugawara";
        System.out.println(list.get(0).name); // sugawara
    }
}
```

---
### 確認

- ArrayListを使用して順番に名前を取り出す

```
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Hero h1 = new Hero("mike");
        Hero h2 = new Hero("tom");

        List<Hero> heroes = new ArrayList<Hero>();
        heroes.add(h1);
        heroes.add(h2);

        for (Hero h : heroes) {
            System.out.println(h.getName());
        }
    }

}
```

```
public class Hero {
    private String name;

    public Hero(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
```

-  Heroをインスタンス化し、倒した敵の数を勇者とペアでコレクションに格納

```
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        Hero h1 = new Hero("mike");
        Hero h2 = new Hero("tom");

        Map<Hero, Integer> heroes = new HashMap<Hero, Integer>();

        heroes.put(h1, 3);
        heroes.put(h2, 7);

        for (Hero key : heroes.keySet()) {
            int value = heroes.get(key);
            System.out.println(key.getName() + "knock down " + value);

//          tomknock down 7
//          mikeknock down 3
        }
    }
}
```
