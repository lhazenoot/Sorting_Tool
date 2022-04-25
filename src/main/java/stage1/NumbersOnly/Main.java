package stage1.NumbersOnly;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long Y = 0;
        int X = 0;
        int Z = 0;

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();

            if (number > Y) {
                Z = 0;
                Y = number;
                Z++;
            }
            else if (number == Y) {
                Z++;
            }
            X++;
        }
        System.out.printf("Total numbers: %d.\n", X);
        System.out.printf("The greatest number: %d (%d time(s)).\n", Y, Z);
    }
}

