import java.sql.*;
import java.util.Scanner;

public class IssueBook {

    public static void issueBook() {

        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Issue ID: ");
            int issueId = sc.nextInt();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            System.out.print("Enter Member ID: ");
            int memberId = sc.nextInt();

            // Check available copies
            PreparedStatement check = con.prepareStatement(
                    "SELECT AVAILABLE_COPIES FROM BOOK WHERE BOOK_ID = ?");
            check.setInt(1, bookId);
            ResultSet rs = check.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("AVAILABLE_COPIES");

                if (available > 0) {

                    // Insert into ISSUE table
                    PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO ISSUE (ISSUE_ID, BOOK_ID, MEMBER_ID, ISSUE_DATE, DUE_DATE) " +
                            "VALUES (?, ?, ?, SYSDATE, SYSDATE + 7)");

                    ps.setInt(1, issueId);
                    ps.setInt(2, bookId);
                    ps.setInt(3, memberId);

                    ps.executeUpdate();

                    // Reduce available copies
                    PreparedStatement update = con.prepareStatement(
                            "UPDATE BOOK SET AVAILABLE_COPIES = AVAILABLE_COPIES - 1 WHERE BOOK_ID = ?");
                    update.setInt(1, bookId);
                    update.executeUpdate();

                    System.out.println("Book Issued Successfully!");

                } else {
                    System.out.println("No copies available!");
                }

            } else {
                System.out.println("Book not found!");
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
