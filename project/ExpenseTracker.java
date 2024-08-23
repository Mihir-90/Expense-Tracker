package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ExpenseTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = ExpenseStorage.loadExpenses();
        User currentUser = null;

        while (currentUser == null) {
            System.out.println("\nExpense Tracker Login/Registration:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    UserStorage.saveUser(new User(newUsername, newPassword));
                    System.out.println("Registration successful! You can now log in.");
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    currentUser = UserStorage.authenticate(username, password);
                    if (currentUser != null) {
                        System.out.println("Login successful! Welcome, " + username + ".");
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Sort Expenses by Date");
            System.out.println("4. Sort Expenses by Description");
            System.out.println("5. Calculate Total Expenses");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine();  

            switch (menuChoice) {
                case 1:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    expenses.add(new Expense(date, desc, amount));
                    ExpenseStorage.saveExpenses(expenses);
                    break;
                case 2:
                    for (Expense e : expenses) {
                        System.out.println(e);
                    }
                    break;
                case 3:
                    sortExpensesByDate(expenses);
                    System.out.println("Expenses sorted by date.");
                    break;
                case 4:
                    sortExpensesByDescription(expenses);
                    System.out.println("Expenses sorted by description.");
                    break;
                case 5:
                    double total = calculateTotalExpenses(expenses);
                    System.out.println("Total Expenses: $" + total);
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }

    private static void sortExpensesByDate(ArrayList<Expense> expenses) {
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return e1.getDate().compareTo(e2.getDate());
            }
        });
    }

    private static void sortExpensesByDescription(ArrayList<Expense> expenses) {
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return e1.getDescription().compareTo(e2.getDescription());
            }
        });
    }

    private static double calculateTotalExpenses(ArrayList<Expense> expenses) {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }
}



