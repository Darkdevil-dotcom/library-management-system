import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewBooksFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewBooksFrame() {

        setTitle("View Books");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Book ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Total Copies");
        model.addColumn("Available Copies");

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBooks();

        setVisible(true);
    }

    private void loadBooks() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM BOOK";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                });
            }

            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }
    }
}
