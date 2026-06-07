
import java.util.Scanner;

public class ProfileView {

    public static void showProfile(Scanner scanner, double balance) {

        System.out.print("Enter your username: ");
        String username = scanner.next();

        String status;
        double interestRate;

        if (balance < 100000) {
            status = "Bronze Member";
            interestRate = 5;   
        } else if (balance < 500000) {
            status = "Silver Member";
            interestRate = 5;
        } else if (balance < 1000000) {
            status = "Gold Member";
            interestRate = 10;
        } else {
            status = "Diamond Member";
            interestRate = 15;
        }

        double interest = balance * interestRate / 100;
        double newBalance = balance + interest;

        System.out.println("\n==============================");
        System.out.println("        PROFILE VIEW          ");
        System.out.println("==============================");
        System.out.println(" Username      : " + username);
        System.out.printf(" Balance       : %.2f BDT\n", balance);
        System.out.println(" Status        : " + status);
        System.out.println(" Interest Rate : " + interestRate + "%");
        System.out.printf(" Interest      : %.2f BDT\n", interest);
        System.out.printf(" New Balance   : %.2f BDT\n", newBalance);
        System.out.println("==============================");

    }
}