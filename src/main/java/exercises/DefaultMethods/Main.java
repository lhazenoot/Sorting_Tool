package exercises.DefaultMethods;

class ConsoleWriter implements Printer, Notifier {
    @Override
    public void greeting() {
        Printer.super.greeting();
    }
}

interface Printer {
    default void greeting() {
        System.out.println("Printer is ready");
    }
}

interface Notifier {
    default void greeting() {
        System.out.println("Notifier is ready");
    }
}

// Main class is niet nodig voor de opdracht, maar kun je gebruiken om de code uit te voeren en te checken.
class Main {
    public static void main(String[] args) {
        ConsoleWriter consoleWriter = new ConsoleWriter();
        consoleWriter.greeting();
    }
}
