- 変数の利用

```
public class Hello {

    public static void main(String[] args) {

        System.out.println("Hello, kiyota!");
        // Hello, kiyota!

        int myFirstNumber = (5 + 10) + (2 * 10);
        int mySecondNumber = 12;
        int myThirdNumber = myFirstNumber * 2;

        int myTotal = myFirstNumber + mySecondNumber + myThirdNumber;

        int myLastOne = 1000 - myTotal;

        System.out.println(myFirstNumber); // 35
        System.out.println("myFirstNumber"); // myFirstNumber
        System.out.println(myTotal); // 117
        System.out.println(myLastOne); // 883

    }
}

```

- 型の変換

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {

        byte myByteValue = -128;
        System.out.println("myByteValue = " + myByteValue);
//      myByteValue = -128

//      型が違うためエラーになる
//      byte myNewByteValue = (myByteValue/2);
        byte myNewByteValue = (byte)(myByteValue/2);
        System.out.println("myNewByteValue = " + myNewByteValue);
//      myNewByteValue = -64
    }
}

```

- doble,float

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {
        // width of 32(4bytes)
        int myIntValue = 5 / 2;

        // width of 32(4bytes)
        float myFloatValue = 5f / 3;

        // width of 64(8bytes)
        double myDoubleValue = 5d / 3d;

        // doubleを基本的に使用する
        // javaのbuild-in function が使っていて、マシンの性能も上がっているため

        System.out.println("myIntValue = " + myIntValue);
        System.out.println("myFloatValue = " + myFloatValue);
        System.out.println("myDoubleValue= " + myDoubleValue);
//      myIntValue = 2
//      myFloatValue = 1.6666666
//      myDoubleValue= 1.6666666666666667

    }
}

```

- String

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {

        // 一文字を表す
        // width of 16(2bytes)
        char myChar = 'D';
        // unicodeに対応
        char UnicodeChar = '\u00A9';
        System.out.println("Unicode output was: " + UnicodeChar);
        // Unicode output was: ©

        String lastString = "10";
        int myInt = 50;
        lastString = lastString + myInt;
        System.out.println("LastString is equal to " + lastString);
//      文字列として合計される
//      LastString is equal to 1050


    }
}

```

- operator

```
package com.kiyota;

public class Main {

    public static void main(String[] args) {
        int result = 1 + 2;
        System.out.println("1 + 2 =" + result);

        int previousResult = result;

        result = result - 1;
        System.out.println(previousResult + " - 1 = " + result);

        previousResult = result;

        result = result * 10;
        System.out.println(previousResult + " * 10 = " + result);

        previousResult = result;

        result = result / 5;
        System.out.println(previousResult + " / 5 = " + result);

        previousResult = result;
        result = result % 3;
        System.out.println(previousResult + " % 3 = " + result);

        previousResult = result;
        System.out.println("Resoult is now " + result);

        result++;
        System.out.println("Resoult is now " + result);

        result *= 10;
        System.out.println("Resoult is now " + result);

        result -= 10;
        System.out.println("Resoult is now " + result);

        result /= 10;
        System.out.println("Resoult is now " + result);

//        1 + 2 =3
//        3 - 1 = 2
//        2 * 10 = 20
//        20 / 5 = 4
//        4 % 3 = 1
//        Resoult is now 1
//        Resoult is now 2
//        Resoult is now 20
//        Resoult is now 10
//        Resoult is now 1

        boolean isObama = false;
        if (isObama == true)
            System.out.println("It is not Obama");

        int topScore = 80;
        if (topScore < 100)
            System.out.println("You got the hight score");

        int secondTopScore = 80;
        // どちらの条件も満たす必要あり
        if (topScore > secondTopScore && topScore < 100)
            System.out.println("Greater than second top score and less than 100");

        // いずれかが条件を満たせば良い　　
        if (topScore > 90 || secondTopScore <= 90)
            System.out.println("One of these tests is true");

        int newValue = 50;
        // 比較の演算子は = が2つ
        if (newValue == 50)
            System.out.println("This is true");

        boolean isCar = false;
        if (isCar == true)
            // 以下は出力されない
            System.out.println("This is not supposed to happen");

        isCar = true;
        // trueなので代入される
        boolean wasCar = isCar ? true : false;
        if(wasCar)
            // 出力される
            System.out.println("wasCar is true");

        double myFirstValue = 20d;
        double mySecondValue = 80;
        double myTotal = (myFirstValue + mySecondValue) * 25;
        System.out.println("myTotal = " + myTotal);
        double theRemainder = myTotal % 40;
        System.out.println("Reminder is " + theRemainder);
        if(theRemainder <= 20)
            System.out.println("Total was over the limit");

    }
}

```