import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Library Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> authenticate());

        setVisible(true);
    }

    private void authenticate() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "system",
                "ZENINTOJI"
            );

            String sql = "SELECT * FROM USERS WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new MainFrame(); // open main window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }

            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }
    }
}
