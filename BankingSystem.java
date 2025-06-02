import java.util.Scanner;
import java.util.ArrayList;

// Customer class
class Customer {
    int id;
    String name;
    double balance;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("৳%.2f deposited successfully.\n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            balance -= amount;
            System.out.printf("৳%.2f withdrawn successfully.\n", amount);
        }
    }

    public void display() {
        System.out.printf("Customer ID: %d, Name: %s, Balance: ৳%.2f\n", id, name, balance);
    }
}

// ATM class
class ATM {
    String location;

    public ATM(String location) {
        this.location = location;
    }

    public void showLocation() {
        System.out.println("ATM Location: " + location);
    }
}

// Bank class
class Bank {
    String bankName;
    ArrayList<Customer> customers;
    ArrayList<ATM> atms;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
        this.atms = new ArrayList<>();
    }

    public void addCustomer(Customer c) {
        customers.add(c);
        System.out.println("Customer added successfully.");
    }

    public void addATM(ATM atm) {
        atms.add(atm);
        System.out.println("ATM added successfully.");
    }

    public Customer findCustomer(int id) {
        for (Customer c : customers) {
            if (c.id == id) return c;
        }
        return null;
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("\n--- Customers List ---");
            for (Customer c : customers) {
                c.display();
            }
        }
    }

    public void displayATMs() {
        if (atms.isEmpty()) {
            System.out.println("No ATMs found.");
        } else {
            System.out.println("\n--- ATMs List ---");
            for (ATM atm : atms) {
                atm.showLocation();
            }
        }
    }
}

// Main class
public class BankingSystem {
    static Bank bank = new Bank("Bangladesh Bank");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        while (true) {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Customers");
            System.out.println("5. Add ATM");
            System.out.println("6. View ATMs");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // skip invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> bank.displayCustomers();
                case 5 -> addATM();
                case 6 -> bank.displayATMs();
                case 7 -> {
                    System.out.println("Thank you! Exiting the system.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        Customer customer = new Customer(id, name);
        bank.addCustomer(customer);
    }

    static void deposit() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();

        Customer customer = bank.findCustomer(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter amount to deposit (৳): ");
        double amount = scanner.nextDouble();
        customer.deposit(amount);
    }

    static void withdraw() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();

        Customer customer = bank.findCustomer(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.print("Enter amount to withdraw (৳): ");
        double amount = scanner.nextDouble();
        customer.withdraw(amount);
    }

    static void addATM() {
        System.out.print("Enter ATM Location: ");
        String location = scanner.nextLine();
        ATM atm = new ATM(location);
        bank.addATM(atm);
    }
}
