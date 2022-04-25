package stage4.EverythingCounts;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Print print = new Print();
        print.Print(args);
    }
}

class Print {
    ByCount byCount = new ByCount();
    Natural natural = new Natural();

    public void Print(String[] args) {
        Arguments arguments = new Arguments(args);

        if (arguments.sortingType.equals("byCount")) {
            byCount.byCount(arguments.dataType);
        }
        else {
            natural.natural(arguments.dataType);
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

        while (scanner.hasNextInt()) {
            int digit = scanner.nextInt();
            if (map.containsKey(digit)) {
                int value = map.get(digit) + 1;
                map.put(digit, value);
            }
            else {
                map.put(digit, 1);
            }
            total++;
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

        while (scanner.hasNextInt()) {
            int digit = scanner.nextInt();
            list.add(digit);
            total++;
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
    String dataType;
    String sortingType;

    public Arguments(String[] args) {
        List<String> arguments = Arrays.asList(args);
        this.dataType = getDataType(arguments);
        this.sortingType = arguments.contains("byCount") ? "byCount" : "natural";
    }

    public String getDataType(List<String> arguments) {
        if (arguments.contains("long")) {
            return "long";
        }
        else if (arguments.contains("line")) {
            return "line";
        }
        else {
            return "word";
        }
    }
}





