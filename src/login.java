import java.sql.*;
import java.util.Scanner;

public class login {

    public static boolean authenticate() {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Library Login =====");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "system",
                "ZENINTOJI"
            );

            String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful!\n");
                con.close();
                return true;
            } else {
                System.out.println("Invalid Credentials!\n");
                con.close();
                return false;
            }

        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
            return false;
        }
    }
}
