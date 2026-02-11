import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ViewBooks {

    public static void viewBooks() {

        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BOOK");

            System.out.println("\n===== Book List =====");

            while (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("BOOK_ID") +
                        " | Title: " + rs.getString("TITLE") +
                        " | Author: " + rs.getString("AUTHOR") +
                        " | Publisher: " + rs.getString("PUBLISHER") +
                        " | Category: " + rs.getString("CATEGORY") +
                        " | Total: " + rs.getInt("TOTAL_COPIES") +
                        " | Available: " + rs.getInt("AVAILABLE_COPIES")
                );
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
