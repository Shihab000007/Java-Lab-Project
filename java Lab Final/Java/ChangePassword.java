import java.util.Scanner;

public class ChangePassword {
    static Scanner sc = new Scanner(System.in);

    static void changePassword() {
        if (LoginRegistration.loggedInUser == null) { 
            System.out.println("No user logged in!");
            return;
        }
        System.out.print("Enter current password: ");
        String current = sc.next();

        if (!LoginRegistration.users.get(LoginRegistration.loggedInUser).equals(current)) {
            System.out.println("Wrong current password!");
            return;
        }

        System.out.print("Enter new password: ");
        String newPass = sc.next();

        System.out.print("Confirm new password: ");
        String confirm = sc.next();

        if (!newPass.equals(confirm)) {
            System.out.println("Passwords do not match!");
            return;
        }

        LoginRegistration.users.put(LoginRegistration.loggedInUser, newPass);
        System.out.println("Password changed successfully!");
    }
}