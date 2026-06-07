import javax.swing.*;
import java.awt.Component; // Added this import to fix the compilation error

public class TransactionReceipt {
    public static void showReceipt(Component parent, String transactionType, double amount, double balance) {
        String receipt = String.format(
            "======================\n" +
            "       RECEIPT        \n" +
            "======================\n" +
            "Transaction : %s\n" +
            "Amount      : %.2f BDT\n" +
            "New Balance : %.2f BDT\n" +
            "======================", 
            transactionType, amount, balance
        );
        JOptionPane.showMessageDialog(parent, receipt, "Transaction Complete", JOptionPane.INFORMATION_MESSAGE);
    }
}