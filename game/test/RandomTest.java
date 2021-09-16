package game.test;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
public class RandomTest {
    public static void main(String[] args) {
        int amountForZero = 0;
        int amountForOne = 0;
        int amountForTwo = 0;
        int amountForThree = 0;
        int amountForFour = 0;
        for (int i = 0; i < 1000000; i++) {
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 4);
            switch (randomNumber) {
                case 0:
                    amountForZero++;
                    break;
                case 1:
                    amountForOne++;
                    break;
                case 2:
                    amountForTwo++;
                    break;
                case 3:
                    amountForThree++;
                    break;
                case 4:
                    amountForFour++;
                    break;
            }
        }
        System.out.println("0 =" + amountForZero);
        System.out.println("1 =" + amountForOne);
        System.out.println("2 =" + amountForTwo);
        System.out.println("3 =" + amountForThree);
        System.out.println("4 =" + amountForFour);
    }
}
