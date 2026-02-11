import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddMember {

    public static void addMember() {

        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Member ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String dept = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO MEMBER (MEMBER_ID, NAME, DEPARTMENT, PHONE, EMAIL) VALUES (?, ?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setString(4, phone);
            ps.setString(5, email);

            ps.executeUpdate();

            System.out.println("Member Added Successfully!");

            con.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
