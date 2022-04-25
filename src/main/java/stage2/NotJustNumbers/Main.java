package stage2.NotJustNumbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Print print = new Print();
        String type = args.length == 2 ? args[1] : "word";

        print.print(type);
    }
}

class Print {
    Sorting sorting = new Sorting();

    public void print(String type) {
        Scanner scanner = new Scanner(System.in);

        if (type.equals("long")) {
            sorting.numberSorting(scanner);
        }
        else if (type.equals("word")) {
            sorting.wordSorting(scanner);
        }
        else if (type.equals("line")) {
            sorting.lineSorting(scanner);
        }
    }
}

class Sorting {
    public void numberSorting(Scanner scanner) {
        long max = 0;
        int totalNumbers = 0;
        int maxTotal = 0;

        while (scanner.hasNextLong()) {
            long input = scanner.nextLong();

            if (input > max) {
                maxTotal = 0;
                max = input;
                maxTotal++;
            }
            else if (input == max) {
                maxTotal++;
            }
            totalNumbers++;
        }

        int percentage = (100 * maxTotal) / totalNumbers;

        System.out.printf("Total numbers: %d.\n", totalNumbers);
        System.out.printf("The greatest number: %d (%d time(s), %d%%).\n", max, maxTotal, percentage);
    }

    public void wordSorting(Scanner scanner) {
        String maxWord = "";
        int totalWords = 0;
        int maxWordTotal = 0;

        while (scanner.hasNext()) {
            String input = scanner.next();

            if (input.length() > maxWord.length()) {
                maxWordTotal = 0;
                maxWord = input;
                maxWordTotal++;
            }
            else if (input.matches(maxWord)) {
                maxWordTotal++;
            }
            else if (input.length() == maxWord.length()) {
                if (input.compareTo(maxWord) > 0) {
                    maxWordTotal = 0;
                    maxWord = input;
                    maxWordTotal++;
                }
            }
            totalWords++;
        }

        int percentage = (100 * maxWordTotal) / totalWords;

        System.out.printf("Total words: %d.\n", totalWords);
        System.out.printf("The longest word: %s (%d time(s), %d%%).\n", maxWord, maxWordTotal, percentage);
    }

    public void lineSorting(Scanner scanner) {
        String maxLine = "";
        int totalLines = 0;
        int maxLineTotal = 0;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.length() > maxLine.length()) {
                maxLineTotal = 0;
                maxLine = input;
                maxLineTotal++;
            }
            else if (input.matches(maxLine)) {
                maxLineTotal++;
            }
            else if (input.length() == maxLine.length()) {
                if (input.compareTo(maxLine) > 0) {
                    maxLineTotal = 0;
                    maxLine = input;
                    maxLineTotal++;
                }
            }
            totalLines++;
        }

        int percentage = (100 * maxLineTotal) / totalLines;

        System.out.printf("Total lines: %d.\n", totalLines);
        System.out.println("The longest line:");
        System.out.printf("%s\n", maxLine);
        System.out.printf("The longest word: %s (%d time(s), %d%%).\n", maxLine, maxLineTotal, percentage);
    }
}


