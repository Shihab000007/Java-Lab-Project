import javax.swing.*;
import java.awt.*;

public class ChangePassword {
    public static void processChange(Component parent) {
        if (LoginRegistration.loggedInUser == null) {
            JOptionPane.showMessageDialog(parent, "No user logged in!");
            return;
        }

        String current = JOptionPane.showInputDialog(parent, "Enter current password:");
        if (current == null) return;

        String correctPassword = LoginRegistration.users.get(LoginRegistration.loggedInUser);
        if (!correctPassword.equals(current)) {
            JOptionPane.showMessageDialog(parent, "Wrong current password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newPass = JOptionPane.showInputDialog(parent, "Enter new password:");
        if (newPass == null || newPass.trim().isEmpty()) return;

        String confirm = JOptionPane.showInputDialog(parent, "Confirm new password:");
        if (confirm == null) return;

        if (!newPass.equals(confirm)) {
            JOptionPane.showMessageDialog(parent, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LoginRegistration.users.put(LoginRegistration.loggedInUser, newPass);
        JOptionPane.showMessageDialog(parent, "Password changed successfully!");
    }
}