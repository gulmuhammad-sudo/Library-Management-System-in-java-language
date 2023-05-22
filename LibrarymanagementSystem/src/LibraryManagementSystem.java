import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class Book {
    private String title;
    private int bookID;
    private double price;
    private int periodInDays;
    private Date issuedDate;

    public Book(int bookID, String title, double price, int periodInDays) {
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.periodInDays = periodInDays;
        this.issuedDate = null;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getPeriodInDays() {
        return periodInDays;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getFormattedIssuedDate() {
        if (issuedDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            return dateFormat.format(issuedDate);
        }
        return "";
    }
}

class User {
    private String name;
    private int id;
    private boolean isAdmin;
    private String password;

    public User(String name, int id, boolean isAdmin, String password) {
        this.name = name;
        this.id = id;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }
}

class Library {
    public ArrayList<Book> books;
    public ArrayList<User> users;
    private ArrayList<Book> issuedBooks;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        issuedBooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void issueBook(Book book, User user) {
        book.setIssuedDate(new Date());
        issuedBooks.add(book);
        JOptionPane.showMessageDialog(null, "Book \"" + book.getTitle() + "\" issued to user " + user.getName());
    }

    public void returnBook(Book book, User user) {
        book.setIssuedDate(null);
        issuedBooks.remove(book);
        JOptionPane.showMessageDialog(null, "Book " + book.getTitle() + " returned by user " + user.getName());
    }

    public void viewBooks() {
        StringBuilder bookList = new StringBuilder("Available books:\n");
        for (Book book : books) {
            bookList.append("ID: ").append(book.getBookID())
                    .append(", Title: ").append(book.getTitle())
                    .append(", Price: ").append(book.getPrice())
                    .append(", Period: ").append(book.getPeriodInDays())
                    .append(" days, Issued Date: ").append(book.getFormattedIssuedDate())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, bookList.toString());
    }

    public void viewUsers() {
        StringBuilder userList = new StringBuilder("Registered users:\n");
        for (User user : users) {
            userList.append("User ID: ").append(user.getId())
                    .append(", Name: ").append(user.getName())
                    .append(", Admin: ").append(user.isAdmin())
                    .append(", Password: ").append(user.getPassword())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, userList.toString());
    }

    public void viewIssuedBooks() {
        StringBuilder issuedBookList = new StringBuilder("Issued books:\n");
        for (Book book : issuedBooks) {
            issuedBookList.append("ID: ").append(book.getBookID())
                    .append(", Title: ").append(book.getTitle())
                    .append(", Issued Date: ").append(book.getFormattedIssuedDate())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, issuedBookList.toString());
    }

    public void createResetData() {
        books.clear();
        users.clear();
        issuedBooks.clear();
        JOptionPane.showMessageDialog(null, "Data reset. All books, users, and issued books removed.");
    }
}

public class LibraryManagementSystem extends JFrame implements ActionListener {
    private Library library;
    private JButton viewBooksButton;
    private JButton viewUsersButton;
    private JButton viewIssuedBooksButton;
    private JButton issueBookButton;
    private JButton addUserButton;
    private JButton addBookButton;
    private JButton returnBookButton;
    private JButton resetDataButton;

    public LibraryManagementSystem() {
        library = new Library();
        initializeData();

        setTitle("Admin Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        viewBooksButton = new JButton("View Books");
        viewBooksButton.addActionListener(this);
        add(viewBooksButton);

        viewUsersButton = new JButton("View Users");
        viewUsersButton.addActionListener(this);
        add(viewUsersButton);

        viewIssuedBooksButton = new JButton("View Issued Books");
        viewIssuedBooksButton.addActionListener(this);
        add(viewIssuedBooksButton);

        issueBookButton = new JButton("Issue Book");
        issueBookButton.addActionListener(this);
        add(issueBookButton);

        addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        add(addUserButton);

        addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(this);
        add(addBookButton);

        returnBookButton = new JButton("Return Book");
        returnBookButton.addActionListener(this);
        add(returnBookButton);

        resetDataButton = new JButton("Create/Reset Data");
        resetDataButton.addActionListener(this);
        add(resetDataButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewBooksButton) {
            library.viewBooks();
        } else if (e.getSource() == viewUsersButton) {
            library.viewUsers();
        } else if (e.getSource() == viewIssuedBooksButton) {
            library.viewIssuedBooks();
        } else if (e.getSource() == issueBookButton) {
            issueBook();
        } else if (e.getSource() == addUserButton) {
            addUser();
        } else if (e.getSource() == addBookButton) {
            addBook();
        } else if (e.getSource() == returnBookButton) {
            returnBook();
        } else if (e.getSource() == resetDataButton) {
            library.createResetData();
        }
    }

    private void issueBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to be issued:");
        int bookID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the book:"));
        int userId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the user:"));

        Book book = findBook(bookID, title);
        User user = findUser(userId);

        if (book != null && user != null) {
            library.issueBook(book, user);
        } else {
            JOptionPane.showMessageDialog(null, "Book or user not found. Please try again.");
        }
    }

    private void addUser() {
        String name = JOptionPane.showInputDialog("Enter the name of the user:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the user:"));
        boolean isAdmin = Boolean.parseBoolean(JOptionPane.showInputDialog("Is the user an admin? (true/false)"));
        String password = JOptionPane.showInputDialog("Enter the password of the user:");

        User user = new User(name, id, isAdmin, password);
        library.addUser(user);
        JOptionPane.showMessageDialog(null, "User added successfully.");
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book:");
        int bookID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the book:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the price of the book:"));
        int periodInDays = Integer.parseInt(JOptionPane.showInputDialog("Enter the period (in days) of the book:"));

        Book book = new Book(bookID, title, price, periodInDays);
        library.addBook(book);
        JOptionPane.showMessageDialog(null, "Book added successfully.");
    }

    private void returnBook() {
        String title = JOptionPane.showInputDialog("Enter the title of the book to be returned:");
        int bookID = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the book:"));
        int userId = Integer.parseInt(JOptionPane.showInputDialog("Enter the ID of the user:"));

        Book book = findBook(bookID, title);
        User user = findUser(userId);

        if (book != null && user != null) {
            library.returnBook(book, user);
        } else {
            JOptionPane.showMessageDialog(null, "Book or user not found. Please try again.");
        }
    }

    private Book findBook(int bookID, String title) {
        for (Book book : library.books) {
            if (book.getBookID() == bookID && book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    private User findUser(int userId) {
        for (User user : library.users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    private void initializeData() {
        library.addBook(new Book(1, " ", 0, 7));
        library.addBook(new Book(2, " ", 0, 14));
        library.addBook(new Book(3, " ", 0, 30));

        library.addUser(new User(" ", 1, false, "admin"));
        library.addUser(new User(" ", 2, true, "admin"));
        library.addUser(new User(" ", 3, false, "admin"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem());
    }
}
