import java.util.HashMap;
import java.util.Scanner;

class LoginRegistration {
    static HashMap<String, String> users = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
     static String loggedInUser = null;

    public static boolean loginSystem() {
        int choice;
        do {
            System.out.println("\n=== WELCOME TO THE BANK ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    if (login()) return true; 
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return false;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);

        return false;
    }

    static void register() {
        System.out.print("Enter username: ");
        String username = sc.next();
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        System.out.print("Enter password: ");
        String password = sc.next();
        users.put(username, password);
        System.out.println("Registration successful!");
    }

    static boolean login() {
        System.out.print("Enter username: ");
        String username = sc.next(); 
        System.out.print("Enter password: ");
        String password = sc.next();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
             loggedInUser = username;
            return true;
        } else {
            System.out.println("Wrong username or password!");
            return false;
        }
    }
}