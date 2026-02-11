import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddBook {

    public static void addBook() {

        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            System.out.print("Enter Publisher: ");
            String publisher = sc.nextLine();

            System.out.print("Enter Category: ");
            String category = sc.nextLine();

            System.out.print("Enter Total Copies: ");
            int total = sc.nextInt();

            System.out.print("Enter Available Copies: ");
            int available = sc.nextInt();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO BOOK (BOOK_ID, TITLE, AUTHOR, PUBLISHER, CATEGORY, TOTAL_COPIES, AVAILABLE_COPIES) VALUES (?, ?, ?, ?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setString(5, category);
            ps.setInt(6, total);
            ps.setInt(7, available);

            ps.executeUpdate();

            System.out.println("Book Added Successfully!");

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
