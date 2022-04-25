package stage6.Xfiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Print print = new Print();
        print.print(args);
    }
}

class Print {
    Arguments arguments = new Arguments();
    ByCount byCount = new ByCount();
    Natural natural = new Natural();

    public void print(String[] args) throws IOException {
        Argument argument = arguments.getArguments(args);
        if (argument.check) {
            runSortingTool(argument);
        }
    }

    public void runSortingTool(Argument argument) throws IOException {
        if (argument.sortingType.equals("byCount")) {
            byCount.byCount(argument);
        }
        else {
            natural.natural(argument);
        }
    }
}

class ByCount extends SortingData {
    public int getPercentage(int value, int total) {
        return (int) Math.rint((double) value / total * 100);
    }

    public void byCount(Argument argument) throws IOException {
        Scanner scanner = getScanner(argument);

        switch (argument.dataType) {
            case "long":
               number(scanner, argument);
                break;
            case "line":
                line(scanner, argument);
                break;
            default:
                word(scanner, argument);
                break;
        }
    }

    public void number(Scanner scanner, Argument argument) throws IOException {
        Map<Integer, Integer> map = new TreeMap<>();
        List<String> fileList = new ArrayList<>();
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

        String firstLine = String.format("Total numbers: %d.\n", total);
        fileList.add(firstLine);
        System.out.print(firstLine);
        for (int number : map.keySet()) {
            int value = map.get(number);
            int percentage = getPercentage(value, total);
            String output = String.format("%d: %d time(s), %d%%\n", number, value, percentage);
            fileList.add(output);
            System.out.print(output);
        }
        writeFile(fileList, argument);
    }

    public void word(Scanner scanner, Argument argument) throws IOException {
        Map<String, Integer> map = new TreeMap<>();
        List<String> fileList = new ArrayList<>();
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

        String firstLine = String.format("Total words: %d.\n", total);
        fileList.add(firstLine);
        System.out.print(firstLine);
        for (String word : map.keySet()) {
            int value = map.get(word);
            int percentage = getPercentage(value, total);
            String output = String.format("%s: %d time(s), %d%%\n", word, value, percentage);
            fileList.add(output);
            System.out.print(output);
        }
        writeFile(fileList, argument);
    }

    public void line(Scanner scanner, Argument argument) throws IOException {
        Map<String, Integer> map = new TreeMap<>();
        List<String> fileList = new ArrayList<>();
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

        String firstLine = String.format("Total lines: %d.\n", total);
        fileList.add(firstLine);
        System.out.print(firstLine);
        for (String line : map.keySet()) {
            int value = map.get(line);
            int percentage = getPercentage(value, total);
            String output = String.format("%s: %d time(s), %d%%\n", line, value, percentage);
            fileList.add(output);
            System.out.print(output);
        }
        writeFile(fileList, argument);
    }
}

class Natural extends SortingData {

    public void natural(Argument argument) throws IOException {
        Scanner scanner = getScanner(argument);

        switch (argument.dataType) {
            case "long":
                number(scanner, argument);
                break;
            case "line":
                line(scanner, argument);
                break;
            default:
                word(scanner,argument);
                break;
        }
    }

    public void number(Scanner scanner, Argument argument) throws IOException {
        List<Integer> list = new ArrayList<>();
        List<String> fileList = new ArrayList<>();
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

        String numbers = "";
        String firstLine = String.format("Total numbers: %d.\n", total);
        fileList.add(firstLine);
        System.out.print(firstLine);
        for (int number : list) {
            numbers += String.format("%d ", number);
        }
        String output = String.format("Sorted data: %s\n", numbers);
        fileList.add(output);
        System.out.print(output);

        writeFile(fileList, argument);
    }

    public void word(Scanner scanner, Argument argument) throws IOException {
        List<String> list = new ArrayList<>();
        List<String> fileList = new ArrayList<>();
        int total = 0;

        while (scanner.hasNext()) {
            String word = scanner.next();
            list.add(word);
            total++;
        }
        Collections.sort(list);

        String words = "";
        String firstLine = String.format("Total words: %d.\n", total);
        fileList.add(firstLine);
        System.out.print(firstLine);
        for (String word : list) {
            words += String.format("%s ", word);
        }
        String output = String.format("Sorted data: %s\n", words);
        fileList.add(output);
        System.out.print(output);

        writeFile(fileList, argument);
    }

    public void line(Scanner scanner, Argument argument) throws IOException {
        List<String> list = new ArrayList<>();
        List<String> fileList = new ArrayList<>();
        int total = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
            total++;
        }
        Collections.sort(list);

        String lines = "";
        String firstLine = String.format("Total lines: %d.\n", total);
        fileList.add(firstLine);
        System.out.print(firstLine);
        for (String line : list) {
            lines += String.format("%s ", line);
        }
        String output = String.format("Sorted data: %s\n", lines);
        fileList.add(output);
        System.out.print(output);
        writeFile(fileList, argument);
    }
}

class Arguments implements Regex {

    public Argument getArguments(String[] args) {
        List<String> arguments = Arrays.asList(args);
        String sortingType = "";
        String dataType = "";
        String inputFile = "";
        String outputFile = "";
        boolean sort = true;
        boolean data = true;

        ListIterator<String> iterator = arguments.listIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.matches(Regex.args) && !next.matches(Regex.correctArgs)) {
                System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", next);
            }
            else if (next.matches(Regex.fileArgs)) {
                if (next.equals("-inputFile")) {
                    inputFile = iterator.next();
                }
                else {
                    outputFile = iterator.next();
                }
            }
            else if (next.matches(Regex.typeArgs)) {
                if (next.equals("-sortingType")) {
                    if (iterator.hasNext()) {
                        String type = iterator.next();
                        if (type.matches(Regex.sortingType)) {
                            sortingType = type;
                        }
                        else {
                            System.out.println("No sorting type defined!");
                            sort = false;
                        }
                    }
                    else {
                        System.out.println("No sorting type defined!");
                        sort = false;
                    }
                }
                else {
                    if (iterator.hasNext()) {
                        String type = iterator.next();
                        if (type.matches(Regex.dataType)) {
                            dataType = type;
                        }
                        else {
                            System.out.println("No data type defined!");
                            data = false;
                        }
                    }
                    else {
                        System.out.println("No data type defined!");
                        data = false;
                    }
                }
            }
        }
        boolean check = sort && data;
        return new Argument(check, dataType, sortingType, inputFile, outputFile);
    }
}

interface Regex {
    String args = "[-][A-z]+";
    String correctArgs = "-sortingType||-dataType||-inputFile||-outputFile";
    String sortingType = "byCount||natural";
    String dataType = "long||line||word";
    String typeArgs = "-sortingType||-dataType";
    String fileArgs = "-inputFile||-outputFile";
}

class Argument {
    String sortingType;
    String dataType;
    String inputFile;
    String outputFile;
    boolean check;

    public Argument(boolean check, String dataType, String sortingType, String inputFile, String outputFile) {
        this.check = check;
        this.dataType = dataType;
        this.sortingType = sortingType;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
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

    public Scanner getScanner(Argument argument) throws IOException {
        if (argument.inputFile.isEmpty()) {
            return new Scanner(System.in);
        }
        else {
            String fileFormat = String.format("./src/main/java/stage6/Xfiles/%s", argument.inputFile);
            File file = new File(fileFormat);
            return new Scanner(file);
        }
    }

    public void writeFile(List<String> list, Argument argument) throws IOException {
        if (!argument.outputFile.isEmpty()) {
            String fileFormat = String.format("./src/main/java/stage6/Xfiles/%s", argument.outputFile);
            File file = new File(fileFormat);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (String next : list) {
                fileWriter.write(next);
            }
            fileWriter.close();
        }
    }
}
