import javax.swing.*;
import java.awt.*;

public class BankingApp extends JFrame {
    private double balance = 0.0;

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private final Color PRIMARY_COLOR = new Color(41, 128, 185);   
    private final Color BG_DARK = new Color(44, 62, 80);          
    private final Color TEXT_LIGHT = Color.WHITE;
    private final Color ACCENT_RED = new Color(192, 57, 43);     

    private JLabel lblWelcome;
    private ProfileView profileScreen;

    public BankingApp() {
        setTitle("Secure Banking System");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        profileScreen = new ProfileView(cardLayout, mainPanel, BG_DARK, TEXT_LIGHT, PRIMARY_COLOR);

        mainPanel.add(createAuthPanel(), "AUTH");
        mainPanel.add(createMenuPanel(), "MENU");
        mainPanel.add(profileScreen, "PROFILE");

        add(mainPanel);
        cardLayout.show(mainPanel, "AUTH");
    }

    private JPanel createAuthPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(BG_DARK);

        JLabel title = new JLabel("WELCOME TO THE BANK", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(TEXT_LIGHT);
        title.setBounds(50, 40, 400, 40);
        panel.add(title);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setForeground(TEXT_LIGHT);
        lblUser.setBounds(80, 130, 100, 30);
        panel.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(180, 130, 220, 30);
        panel.add(txtUser);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setForeground(TEXT_LIGHT);
        lblPass.setBounds(80, 190, 100, 30);
        panel.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(180, 190, 220, 30);
        panel.add(txtPass);

        JButton btnLogin = createButton("Login", PRIMARY_COLOR, 80, 270, 150, 40);
        JButton btnRegister = createButton("Register", new Color(39, 174, 96), 250, 270, 150, 40);
        JButton btnExit = createButton("Exit System", ACCENT_RED, 80, 340, 320, 40);

        panel.add(btnLogin); panel.add(btnRegister); panel.add(btnExit);

        btnRegister.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();
            if (LoginRegistration.register(user, pass)) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Error: Username exists or fields empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();
            if (LoginRegistration.login(user, pass)) {
                lblWelcome.setText("Welcome, " + LoginRegistration.loggedInUser + "!");
                txtUser.setText("");
                txtPass.setText("");
                cardLayout.show(mainPanel, "MENU");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExit.addActionListener(e -> System.exit(0));
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(BG_DARK);

        lblWelcome = new JLabel("Welcome!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setForeground(TEXT_LIGHT);
        lblWelcome.setBounds(50, 20, 400, 30);
        panel.add(lblWelcome);

        JButton btnBalance = createButton("Show Balance", PRIMARY_COLOR, 80, 80, 320, 40);
        JButton btnDeposit = createButton("Deposit Funds", PRIMARY_COLOR, 80, 140, 320, 40);
        JButton btnWithdraw = createButton("Withdraw Funds", PRIMARY_COLOR, 80, 200, 320, 40);
        JButton btnFastCash = createButton("Fast Cash Options", PRIMARY_COLOR, 80, 260, 320, 40);
        JButton btnProfile = createButton("View Membership Profile", PRIMARY_COLOR, 80, 320, 320, 40);
        JButton btnPassword = createButton("Change Password", PRIMARY_COLOR, 80, 380, 320, 40);
        JButton btnLogout = createButton("Logout & Exit", ACCENT_RED, 80, 440, 320, 40);

        panel.add(btnBalance); panel.add(btnDeposit); panel.add(btnWithdraw);
        panel.add(btnFastCash); panel.add(btnProfile); panel.add(btnPassword); panel.add(btnLogout);

        // Action Handlers
        btnBalance.addActionListener(e -> JOptionPane.showMessageDialog(this, String.format("Current Balance: %.2f BDT", balance)));

        btnDeposit.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter deposit amount (BDT):");
            if (input != null) {
                try {
                    double amt = Double.parseDouble(input);
                    if (amt <= 0) throw new NumberFormatException();
                    balance += amt;
                    JOptionPane.showMessageDialog(this, String.format("Deposited: %.2f BDT successfully!", amt));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnWithdraw.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount (BDT):");
            if (input != null) {
                try {
                    double amt = Double.parseDouble(input);
                    if (amt <= 0) throw new NumberFormatException();
                    if (amt > balance) {
                        JOptionPane.showMessageDialog(this, "INSUFFICIENT FUNDS!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        balance -= amt;
                        TransactionReceipt.showReceipt(this, "CASH WITHDRAWAL", amt, balance);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFastCash.addActionListener(e -> {
            double amt = FastCash.selectAmount(this);
            if (amt > 0) {
                if (amt > balance) {
                    JOptionPane.showMessageDialog(this, "INSUFFICIENT FUNDS!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    balance -= amt;
                    TransactionReceipt.showReceipt(this, "FAST CASH", amt, balance);
                }
            }
        });

        btnProfile.addActionListener(e -> {
            profileScreen.updateProfile(balance);
            cardLayout.show(mainPanel, "PROFILE");
        });

        btnPassword.addActionListener(e -> ChangePassword.processChange(this));

        btnLogout.addActionListener(e -> {
            LoginRegistration.logout();
            balance = 0; // resets session balance on exit
            cardLayout.show(mainPanel, "AUTH");
        });

        return panel;
    }

    private JButton createButton(String text, Color bg, int x, int y, int w, int h) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setBackground(bg);
        btn.setForeground(TEXT_LIGHT);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingApp().setVisible(true));
    }
}