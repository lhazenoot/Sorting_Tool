package exercises.MethodReferences;

import java.util.function.Function;

class Customer {

    String firstName;
    String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Paul", "Morris");
        
    }
}
