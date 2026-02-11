import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {
            // Load Oracle Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle Database
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "ZENINTOJI"
            );

            System.out.println("Connected Successfully!");
            return con;

        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return null;
        }
    }
}
