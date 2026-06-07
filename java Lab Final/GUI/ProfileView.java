import javax.swing.*;
import java.awt.*;

public class ProfileView extends JPanel {
    private JLabel lblBalance, lblStatus, lblInterest, lblTotal;

    public ProfileView(CardLayout cardLayout, JPanel mainPanel, Color bgDark, Color textLight, Color primaryColor) {
        setLayout(null);
        setBackground(bgDark);

        JLabel title = new JLabel("MEMBERSHIP PROFILE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(textLight);
        title.setBounds(50, 30, 400, 40);
        add(title);

        // Generate profile row placeholders
        lblBalance = createRowLabel(120, textLight);
        lblStatus = createRowLabel(170, textLight);
        lblInterest = createRowLabel(220, textLight);
        lblTotal = createRowLabel(270, textLight);

        JButton btnBack = new JButton("Back to Menu");
        btnBack.setBounds(150, 360, 200, 40);
        btnBack.setBackground(primaryColor);
        btnBack.setForeground(textLight);
        btnBack.setFocusPainted(false);
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));
        add(btnBack);
    }

    private JLabel createRowLabel(int y, Color textLight) {
        JLabel label = new JLabel();
        label.setFont(new Font("Monospaced", Font.BOLD, 16));
        label.setForeground(textLight);
        label.setBounds(60, y, 380, 30);
        add(label);
        return label;
    }

    public void updateProfile(double balance) {
        String status;
        double rate;

        if (balance < 100000) { status = "Bronze Member"; rate = 5; }
        else if (balance < 500000) { status = "Silver Member"; rate = 5; }
        else if (balance < 1000000) { status = "Gold Member"; rate = 10; }
        else { status = "Diamond Member"; rate = 15; }

        double interest = balance * rate / 100;
        double total = balance + interest;

        lblBalance.setText(String.format("Current Balance : %.2f BDT", balance));
        lblStatus.setText("Account Status  : " + status);
        lblInterest.setText(String.format("Est. Interest (%d%%) : %.2f BDT", (int)rate, interest));
        lblTotal.setText(String.format("Total Evaluation : %.2f BDT", total));
    }
}