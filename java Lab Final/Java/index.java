import java.util.Scanner;

public class index {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isAuthenticated = LoginRegistration.loginSystem();

        if (isAuthenticated) {
            runBankingMenu();
        } else {
            System.out.println("System Shutdown.");
        }

        scanner.close();
    }

    static void runBankingMenu() {
        double balance = 0;
        boolean isRunning = true;
        int choice;

        while (isRunning) {
            System.out.println("\n*************");
            System.out.println("BANKING PROGRAM");
            System.out.println("*************");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Profile");
            System.out.println("5. Logout & Exit");
            System.out.println("6. Change Password");
            System.out.println("7. Fast Cash");
            System.out.println("*************");

            System.out.print("Enter Your Choice (1-7): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showBalance(balance);
                    break;
                case 2:
                    balance += deposit();
                    break;
                case 3:
                    balance -= withdraw(balance);
                    break;
                case 4:
                    ProfileView.showProfile(scanner, balance);
                    break;
                case 5:
                    isRunning = false;
                    break;
                case 6:
                    ChangePassword.changePassword();
                    break;
                case 7:
                    double fastAmount = FastCash.fastCashMenu(balance);
                    if (fastAmount > 0) {
                        balance -= fastAmount;
                        TransactionReceipt.printReceipt("FAST CASH WITHDRAW", fastAmount, balance);
                    }
                    break;

                default:
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }
        System.out.println("*************");
        System.out.println("Thank You! Have a nice day!");
        System.out.println("*************");
    }

    static void showBalance(double balance) {
        System.out.println("*************");
        System.out.printf("Current Balance: %.2f BDT\n", balance);
    }

    static double deposit() {
        System.out.print("Enter an amount to be deposited: ");
        double amount = scanner.nextDouble();
        if (amount < 0) {
            System.out.println("Amount cannot be negative");
            return 0;
        }
        return amount;
    }

    static double withdraw(double balance) {
        System.out.print("Enter amount to be withdrawn: ");
        double amount = scanner.nextDouble();
        if (amount > balance) {
            System.out.println("INSUFFICIENT FUNDS");
            return 0;
        } else if (amount < 0) {
            System.out.println("Amount cannot be negative");
            return 0;
        }
        return amount;
    }
}