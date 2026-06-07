import javax.swing.*;
import java.awt.*;

public class FastCash {
    public static double selectAmount(Component parent) {
        String[] options = {"500 BDT", "1000 BDT", "2000 BDT", "5000 BDT", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
            parent, 
            "Select a Fast Cash Amount:", 
            "Fast Cash Menu", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]
        );

        switch (choice) {
            case 0: return 500;
            case 1: return 1000;
            case 2: return 2000;
            case 3: return 5000;
            default: return 0; // Cancel or Closed
        }
    }
}