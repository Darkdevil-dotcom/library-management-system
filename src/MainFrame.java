import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Library Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton addBookBtn = new JButton("Add Book");
        JButton viewBookBtn = new JButton("View Books");
        JButton addMemberBtn = new JButton("Add Member");
        JButton issueBookBtn = new JButton("Issue Book");
        JButton returnBookBtn = new JButton("Return Book");

        panel.add(addBookBtn);
        panel.add(viewBookBtn);
        panel.add(addMemberBtn);
        panel.add(issueBookBtn);
        panel.add(returnBookBtn);

        add(panel);

        // 🔥 CONNECT BUTTONS TO LOGIC

        addBookBtn.addActionListener(e -> new AddBookFrame());
        viewBookBtn.addActionListener(e -> new ViewBooksFrame());
        addMemberBtn.addActionListener(e -> AddMember.addMember());
        issueBookBtn.addActionListener(e -> IssueBook.issueBook());
        returnBookBtn.addActionListener(e -> ReturnBook.returnBook());

        setVisible(true);
    }
}
