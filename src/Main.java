import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if(!login.authenticate()){
            return;
        }

        while (true) {

            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Member");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AddBook.addBook();
                    break;

                case 2:
                    ViewBooks.viewBooks();
                    break;

                case 3:
                    AddMember.addMember();
                    break;

                case 4:
                    IssueBook.issueBook();
                    break;

                case 5:
                    ReturnBook.returnBook();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
