
import java.util.Scanner;

public class FastCash {

    static Scanner sc = new Scanner(System.in);

    public static double fastCashMenu(double balance) {

        System.out.println("\n******** FAST CASH ********");
        System.out.println("1. 500 BDT");
        System.out.println("2. 1000 BDT");
        System.out.println("3. 2000 BDT");
        System.out.println("4. 5000 BDT");
        System.out.println("5. Cancel");
        System.out.print("Choose: ");

        int choice = sc.nextInt();
        double amount = 0;

        switch (choice) {
            case 1:
                amount = 500;
                break;
            case 2:
                amount = 1000;
                break;
            case 3:
                amount = 2000;
                break;
            case 4:
                amount = 5000;
                break;
            case 5:
                System.out.println("Fast Cash Cancelled.");
                return 0;
            default:
                System.out.println("INVALID CHOICE");
                return 0;
        }

        if (amount > balance) {
            System.out.println("INSUFFICIENT FUNDS");
            return 0;
        }

        return amount;
    }
}