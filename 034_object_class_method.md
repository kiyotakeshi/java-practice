### インスタンスの操作

- 全てのクラスはjava.lang.Objectクラスから継承したもの
    - 全クラスはObjectクラスで定義されたメソッドを持っている
    - Object型変数にはあらゆるインスタンスを代入可能

- Objectクラスが持っているメソッド

1. toString()
    - インスタンスの内容を文字列で表現したものとして返す

2. equals()
    - 等価判定を行う

3. hashCode()
    - ハッシュ値を得る

4. compareTo()
    - 大小関係を判定する

5. clone()
    - 複製

---
- toString()
    - インスタンスの中身を情報が知りたくなったら呼び出す
    - System.out.println()命令は引数としてインスタンスを渡すとtoString()メソッドを呼び出して文字列を取り出す

```
public class Main {
    public static void main(String[] args) {

        Hero a = new Hero();
        System.out.println(a);

//      親クラスであるObjectクラスのtoString()が出力される
//      Hero@2d209079
    }
}

```

```
public class Hero {
    private String name;
    private int hp;
}

```

- toString()メソッドをオーバーライドすると文字列を返すようになる

```
public class Hero {
    private String name;
    private int hp;

    public String toString(){
        return "Hero:" + this.name + " HP:"+ this.hp;
    }
}
```

```
public class Main {
    public static void main(String[] args) {

        Hero a = new Hero();
        System.out.println(a);
    }
}

// Hero:null HP:0
```

---
- 等価
    - 何を持ってインスタンス同士を等価とみなすかメソッドをオーバライドして定義する
    - 開発者が適切と考える等価判定アルゴリズムをJVMに伝える

```
public class Account {
    String accountNo;

//  equals()メソッドをオーバーライド
//  引数はObject型
    public boolean equals(Object o) {

//      自分自身が引数として渡されてきたらtrue
        if (o == this) return true;

        if (o == null) return false;

//      型を比較し異なるものだとfalse
//      比較できるようにキャストを使って代入
        if (!(o instanceof Account)) return false;
        Account r = (Account) o;


        if (!this.accountNo.trim().equals(r.accountNo.trim())) {
            return false;
        }
        return true;
    }
}
```

- クラスを作ったらequals()をオーバーライドするほうが想定通りの挙動にできる
    - オーバーライドをサボる

```
import java.util.ArrayList;
import java.util.List;

// equals()をオーバーライドしていない
class Hero {
    public String name;
}


public class Main {
    public static void main(String[] args) {
//      インスタンスをつくってコレクションに格納
        List<Hero> list = new ArrayList<Hero>();
        Hero h1 = new Hero();
        h1.name = "mike";
        list.add(h1);
        System.out.println("elements: " + list.size());


        h1 = new Hero();
        h1.name = "mike";

//      名前がmikeの要素を削除しようとしている
//      JVMはArrayListのなかから同じものを探すためにequals()による等価判定を行う
//      equals()をオーバーライドしていないクラスをコレクションに格納すると要素の検索や削除が行われない
        list.remove(h1);
        System.out.println("elements: " + list.size());

//      消せていない
//      elements: 1
//      elements: 1
    }
}
```

---
- HashSetでremove()できない
    - equals()をオーバライドしているが……

```
// このコードは動かない
import java.util.HashSet;
import java.util.Set;

// equals()をオーバーライドしていない
class Hero {
    public String name;

    public boolean equals(Object o) {
        // equalsをオーバーライド
    }
}


public class Main {
    public static void main(String[] args) {
        Set<Hero> list = new HashSet<Hero>();
        Hero h1 = new Hero();
        h1.name = "minato";
        list.add(h1);
        System.out.println("elemtnts:" + list.size());

        Hero h1 = new Hero();
        h1.name = "minato";
        list.remove(h1);
        System.out.println("elemtnts:" + list.size());
    }
}
```

- HashSetのremove()メソッドは各要素にequals()で同じかを聞いて回らない(計算コストが大きいため)
    - 高速だが曖昧な方法で問い合わせ、だいたい同じ要素にequals()で厳密に同じか問い合わせる
    - インスタンスに対するハッシュ値(hash code)を利用する
    - ObjectクラスがhashCode()メソッドを呼び出す

```
// このコードも単体では動かない

class Hero {
    String name;
    int hp;

    public int hashCode() {
//      ハッシュ値の初期値(0以外ならなんでもいい)
        int result = 37;

//      各要素の影響を加えるための乗数、奇数かつ素数の31が使われる
        result = result * 31 + name.hashCode();
        result = result * 31 + hp;
        return result;
    }
}

```

---
- インスタンスの順序づけ

```
public class Account implements Comparable<Account>{
    int number;

//  自然順序を宣言するためにjava.lang.Comparableインターフェースを実装
//  引数で渡されてきたインスタンスobjと自分自身を比較
    public int compareTo(Account obj){

//      objよりも小さい場合負の数を返す
        if(this.number < obj.number){
            return -1;
        }
        if(this.number > obj.number){
            return 1;
        }
        return 0;
    }
}
```

---
- インスタンスの複製
    - clone()メソッドの活用

```
// 参照がコピーされるだけで実体はコピーされていない
Hero h1 = new Hero("mike");
Hero h2 = h1;

// 複製
Hero h1 = new Hero("mike");
Hero h2 = h1.clone();

```

- cloneによる複製のために必要な作業

1. Cloneableインターフェースの実装
2. cloneメソッドをpublicでオーバーライド

```
// Cloneableインターフェースを実装
// Cloneableはclone()を定義していない
// clone()を実装することで複製に対応することを表明するだけのマーカーインターフェース
public class Hero implements Cloneable{

    String name;
    int hp;
    Sword sword;

//  メソッドをオーバライド
//  Objectクラスで定義されているclone()メソッドはprotectedで宣言されている
//  publicでオーバーライドして外部から呼び出せるようにする
    public Hero clone(){
        Hero result = new Hero();
        result.name = this.name;
        return.hp = this.hp;
        result.sword = this.sword;
    }
}
```

- 浅いコピーと深いコピー(以下のメソッドも動かない)

```
public class Main {
    public static void main(String[] args) {

        Hero h1 = new Hero("mike");
        Sword s = new Sword("fire-sword");

        h1.setSword(s);
        System.out.println("Weapon: " + h1.getSword().getName());
        System.out.println("duplicate by clone()");

//      浅いコピー
//      swordの参照がコピーされるに過ぎない
//      Hero h2 = h1.clone();

//      深いコピーは、各フィールドについても別インスタンスとしてコピーする
        result.sword = this.sword.clone();
        System.out.println("change sword name");
        h1.getSword().setName("freeze-sword");

//      浅いコピーでコピーするとh1の武器名変更がh2にまで影響する
        System.out.println("old: " + h1.getSword().getName() + " new: " + h2.getSword().getName());
    }
}
```
