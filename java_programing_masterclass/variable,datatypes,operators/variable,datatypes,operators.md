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
