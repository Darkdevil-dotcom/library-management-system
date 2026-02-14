import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddBookFrame extends JFrame {

    JTextField idField, titleField, authorField, copiesField;

    public AddBookFrame() {

        setTitle("Add Book");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Book ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Author:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("Copies:"));
        copiesField = new JTextField();
        panel.add(copiesField);

        JButton addBtn = new JButton("Add Book");
        panel.add(addBtn);

        add(panel);

        addBtn.addActionListener(e -> addBook());

        setVisible(true);
    }

    private void addBook() {

        try {

            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "system",
                "ZENINTOJI"
            );

String sql = "INSERT INTO BOOK (book_id, title, author, total_copies, available_copies) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            int copies = Integer.parseInt(copiesField.getText().trim());

            ps.setInt(1, Integer.parseInt(idField.getText().trim()));
            ps.setString(2, titleField.getText().trim());
            ps.setString(3, authorField.getText().trim());
            ps.setInt(4, copies);  // total_copies
            ps.setInt(5, copies);  // available_copies


            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Book Added Successfully!");

            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }
    }
}
