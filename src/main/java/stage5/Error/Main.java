package stage5.Error;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Print print = new Print();
        print.print(args);

    }
}

class Print {
    Arguments arguments = new Arguments();
    ByCount byCount = new ByCount();
    Natural natural = new Natural();

    public void print(String[] args) {
        Argument argument = arguments.getArguments(args);
        if (argument.check) {
            runSortingTool(argument.sortingType, argument.dataType);
        }
    }

    public void runSortingTool(String sortingType, String dataType) {
        if (sortingType.equals("byCount")) {
            byCount.byCount(dataType);
        }
        else {
            natural.natural(dataType);
        }
    }
}

class ByCount extends SortingData {
    public int getPercentage(int value, int total) {
        return (int) Math.rint((double) value / total * 100);
    }

    public void byCount(String type) {
        Scanner scanner = new Scanner(System.in);

        switch (type) {
            case "long":
                number(scanner);
                break;
            case "line":
                line(scanner);
                break;
            default:
                word(scanner);
                break;
        }
    }

    public void number(Scanner scanner) {
        Map<Integer, Integer> map = new TreeMap<>();
        int total = 0;

        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.matches("[A-z]+")) {
                System.out.printf("\"%s\" is not a long. It will be skipped.\n", next);
            }
            else {
                int digit = Integer.parseInt(next);
                if (map.containsKey(digit)) {
                    int value = map.get(digit) + 1;
                    map.put(digit, value);
                }
                else {
                    map.put(digit, 1);
                }
                total++;
            }
        }

        map = sortNumberMap(map);

        System.out.printf("Total numbers: %d.\n", total);
        for (int number : map.keySet()) {
            int value = map.get(number);
            int percentage = getPercentage(value, total);
            System.out.printf("%d: %d time(s), %d%%\n", number, value, percentage);
        }
    }

    public void word(Scanner scanner) {
        Map<String, Integer> map = new TreeMap<>();
        int total = 0;

        while (scanner.hasNext()) {
            String word = scanner.next();
            if (map.containsKey(word)) {
                int value = map.get(word) + 1;
                map.put(word, value);
            }
            else {
                map.put(word, 1);
            }
            total++;
        }

        map = sortStringMap(map);

        System.out.printf("Total words: %d.\n", total);
        for (String word : map.keySet()) {
            int value = map.get(word);
            int percentage = getPercentage(value, total);
            System.out.printf("%s: %d time(s), %d%%\n", word, value, percentage);
        }
    }

    public void line(Scanner scanner) {
        Map<String, Integer> map = new TreeMap<>();
        int total = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (map.containsKey(line)) {
                int value = map.get(line) + 1;
                map.put(line, value);
            }
            else {
                map.put(line, 1);
            }
            total++;
        }

        map = sortStringMap(map);
        System.out.printf("Total lines: %d.\n", total);
        for (String line : map.keySet()) {
            int value = map.get(line);
            int percentage = getPercentage(value, total);
            System.out.printf("%s: %d time(s), %d%%\n", line, value, percentage);
        }
    }
}

class Natural extends SortingData {

    public void natural(String type) {
        Scanner scanner = new Scanner(System.in);

        switch (type) {
            case "long":
                number(scanner);
                break;
            case "line":
                line(scanner);
                break;
            default:
                word(scanner);
                break;
        }
    }

    public void number(Scanner scanner) {
        List<Integer> list = new ArrayList<>();
        int total = 0;

        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.matches("[A-z]+")) {
                System.out.printf("\"%s\" is not a long. It will be skipped.\n", next);
            }
            else {
                int digit = Integer.parseInt(next);
                list.add(digit);
                total++;
            }
        }
        Collections.sort(list);

        String print = "";
        System.out.printf("Total numbers: %d.\n", total);
        for (int number : list) {
            print += String.format("%d ", number);
        }
        System.out.printf("Sorted data: %s\n", print);
    }

    public void word(Scanner scanner) {
        List<String> list = new ArrayList<>();
        int total = 0;

        while (scanner.hasNext()) {
            String word = scanner.next();
            list.add(word);
            total++;
        }
        Collections.sort(list);

        String words = "";
        System.out.printf("Total words: %d.\n", total);
        for (String word : list) {
            StringJoiner sj = new StringJoiner(" ");
            sj.add(word);
            words = sj.toString();
        }
        System.out.printf("Sorted data: %s\n", words);
    }

    public void line(Scanner scanner) {
        List<String> list = new ArrayList<>();
        int total = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
            total++;
        }
        Collections.sort(list);

        String lines = "";
        System.out.printf("Total lines: %d.\n", total);
        for (String line : list) {
            StringJoiner sj = new StringJoiner(" ");
            sj.add(line);
            lines = sj.toString();
        }
        System.out.printf("Sorted data: %s\n", lines);
    }
}

class SortingData {
    public Map<Integer, Integer> sortNumberMap(Map<Integer, Integer> unsorted) {
        Map<Integer, Integer> sorted = new LinkedHashMap<>();
        unsorted.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        return sorted;
    }

    public Map<String, Integer> sortStringMap(Map<String, Integer> unsorted) {
        Map<String, Integer> sorted = new LinkedHashMap<>();
        unsorted.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        return sorted;
    }
}

class Arguments {
    public Argument getArguments(String[] args) {
        boolean sort = true;
        boolean data = true;
        boolean check;
        String dataType = "";
        String sortingType = "";

        List<String> arguments = Arrays.asList(args);
        if (arguments.contains("-sortingType")) {
            if (arguments.contains("natural")) {
                sortingType = "natural";
            }
            else if (arguments.contains("byCount")) {
                sortingType = "byCount";
            }
            else {
                System.out.println(" No sorting type defined!");
                sort = false;
            }
        }
        if (arguments.contains("-dataType")) {
            if (arguments.contains("long")) {
                dataType = "long";
            }
            else if (arguments.contains("line")) {
                dataType = "line";
            }
            else if (arguments.contains("word")) {
                dataType = "word";
            }
            else {
                System.out.println(" No data type defined!");
                data = false;
            }
        }

        for (String next : arguments) {
            if (next.matches("[-][A-z]+")) {
                if (!next.equals("-sortingType") && !next.matches("-dataType")) {
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", next);
                }
            }
        }
        check = sort && data;
        return new Argument(check, dataType, sortingType);
    }
}

class Argument {
    String dataType;
    String sortingType;
    boolean check;

    public Argument(boolean check, String dataType, String sortingType) {
        this.check = check;
        this.dataType = dataType;
        this.sortingType = sortingType;
    }
}

