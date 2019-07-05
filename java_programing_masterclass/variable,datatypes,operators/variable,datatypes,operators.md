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
        // width of int = 32(4bytes)
        int myIntValue = 5 / 2;
        // width of int = 32(4bytes)
        float myFloatValue = 5f / 3;
        // width of int = 64(8bytes)
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