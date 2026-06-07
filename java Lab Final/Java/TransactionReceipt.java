public class TransactionReceipt {

    public static void printReceipt(String transactionType, double amount, double balance) {
        System.out.println("\n*************");
        System.out.println("   RECEIPT   ");
        System.out.println("*************");
        System.out.println("Type    : " + transactionType);
        System.out.printf("Amount  : %.2f BDT\n", amount);
        System.out.printf("Balance : %.2f BDT\n", balance);
        System.out.println("*************");
    }
}