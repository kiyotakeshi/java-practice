### メソッドを使ったコーディングの練習

```
public class Main {
    public static void main(String[] args) {

        introduceOneSelf();
        // kiyota(25) height:150 sex:Man
    }

    public static void introduceOneSelf() {

        String name = "kiyota";
        int age = 25;
        int height = 150;
        String sex = "Man";

        System.out.println(name + "(" + age + ")" + " height:" + height + " sex:" + sex);
    }
}

```

```
public class Main {
    public static void main(String[] args) {

        // 短い書き方
        // email("Test", "test@email.com", "this is test body");

        // 可読性の高い書き方
        String title = "Test";
        String address = "test@email.com";
        String text = "this is test body";

        email(title, address, text);
    }

    public static void email(String title, String address, String text) {
        System.out.println("Send your mail to " + address);
        System.out.println("Title:" + title);
        System.out.println("Body:" + text);
    }
}

```

```
public class Main {
    public static void main(String[] args) {

        // 可読性の高い書き方
        String title = "Test";
        String address = "test@email.com";
        String text = "this is test body";

        email(title, address, text);
        // Send your mail to test@email.com
        // Title:Test
        // Body:this is test bod

        System.out.println("-----------------");

        email(address, text);
        // Send your mail to test@email.com
        // Tittle:No Title
        // Body:this is test body

    }

    public static void email(String title, String address, String text) {
        System.out.println("Send your mail to " + address);
        System.out.println("Title:" + title);
        System.out.println("Body:" + text);
    }

    public static void email(String address, String text) {
        System.out.println("Send your mail to " + address);
        System.out.println("Tittle:No Title");
        System.out.println("Body:" + text);
    }
}

```

```
public class Main {
    public static void main(String[] args) {

        double triangleArea = calcTriangleArea(2, 5);
        System.out.println(triangleArea);

        double circleArea = calcCircleleArea(4);
        System.out.println(circleArea);
    }

    public static double calcTriangleArea(double bottom, double height) {

        double triangleArea = (bottom * height) / 2;
        return triangleArea;
    }

    public static double calcCircleleArea(double radius) {
        double circleleArea = radius * radius * 3.14;
        return circleleArea;
    }
}

```