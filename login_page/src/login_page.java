import javax.swing.*;
import java.awt.*;

public class login_page extends JFrame {
    public login_page()
    {
        JLabel label1 = new JLabel("Username:");
        JTextArea textArea1 = new JTextArea(1, 10);
        JLabel passwordlabel2 = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");

        add(label1);
        add(textArea1);
        add(passwordlabel2);
        add(passwordField);
        add(loginButton);

        setTitle("Login");
        setSize(270, 150);
        setVisible(true);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new login_page());
    }
}