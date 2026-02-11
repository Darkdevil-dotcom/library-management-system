import java.sql.*;
import java.util.Scanner;

public class ReturnBook {

    public static void returnBook() {

        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Return ID: ");
            int returnId = sc.nextInt();

            System.out.print("Enter Issue ID: ");
            int issueId = sc.nextInt();

            // Check if issue exists
            PreparedStatement check = con.prepareStatement(
                    "SELECT BOOK_ID FROM ISSUE WHERE ISSUE_ID = ?");
            check.setInt(1, issueId);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {

                int bookId = rs.getInt("BOOK_ID");

                // Insert into RETURN_BOOK
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO RETURN_BOOK (RETURN_ID, ISSUE_ID, RETURN_DATE) VALUES (?, ?, SYSDATE)");

                ps.setInt(1, returnId);
                ps.setInt(2, issueId);

                ps.executeUpdate();

                // Increase available copies
                PreparedStatement update = con.prepareStatement(
                        "UPDATE BOOK SET AVAILABLE_COPIES = AVAILABLE_COPIES + 1 WHERE BOOK_ID = ?");
                update.setInt(1, bookId);
                update.executeUpdate();

                System.out.println("Book Returned Successfully!");

            } else {
                System.out.println("Issue ID not found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
