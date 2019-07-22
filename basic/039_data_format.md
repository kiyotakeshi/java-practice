### データフォーマット

- プロパティファイルを読み取り画面に表示する

```
kiyota-MacBook-Pro:src kiyotatakeshi$ cat properties
tokyo.capital = Tokyo
tokyo.food = sushi
aichi.capital = Nagoya
aichi.food = Misokatsu

```

```
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {

        Reader fr = new FileReader("/Users/kiyotatakeshi/Desktop/Java/Exception/src/properties");

        Properties p = new Properties();
        p.load(fr);

        System.out.println(p.getProperty("aichi.capital") + ":" + p.getProperty("aichi.food"));
//      Nagoya:Misokatsu

        fr.close();
    }
}
```

- インスタンス自体をストリーム経由で保存する

```
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        Employee mike = new Employee();
        mike.name = "Mike Trout";
        mike.age = 41;

        Department soumubu = new Department();
        soumubu.name = "Soumu";
        soumubu.leader = mike;

        FileOutputStream fos = new FileOutputStream("/Users/kiyotatakeshi/Desktop/Java/Exception/src/company.dat");

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(soumubu);
        oos.flush();
        oos.close();
    }
}

class Employee implements java.io.Serializable {
    String name;
    int age;
}

class Department implements java.io.Serializable {
    String name;
    Employee leader;
}

// 実行結果

kiyota-MacBook-Pro:src kiyotatakeshi$ ls company.dat
company.dat
```