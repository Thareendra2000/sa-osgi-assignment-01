package librarymanagerpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import librarydb.Database;
import librarydb.DatabaseImpl;

public class LibraryManagerImpl implements LibraryManagerInterface {
    private Connection connection = null;
    private Statement statement = null;
    private Database database;
    private ResultSet resultSet;

    public LibraryManagerImpl() {
        database = new DatabaseImpl();
        connection = database.getDatabaseConnection();
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void addBook(Book book) {
        String sqlQueryBook = "INSERT INTO books(isbn, title, author) VALUES('" + book.getIsbn() + "', '" + book.getTitle() + "', '" + book.getAuthor() + "')";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQueryBook);
            System.out.println("Book added successfully...");
        } catch (SQLException exc) {
            System.out.println("Error adding book: " + exc.getMessage());
        }
    }

    @Override
    public Book searchBook(String isbn) {
        Book book = null;
        String sqlQueryBook = "SELECT * FROM books WHERE isbn like '" + isbn + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQueryBook);
            if (resultSet.next()) {
                book = new Book(resultSet.getString("isbn"), resultSet.getString("title"), resultSet.getString("author"));
            }
        } catch (SQLException exc) {
            System.out.println("Error searching book: " + exc.getMessage());
        }

        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sqlQueryBooks = "SELECT * FROM books";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQueryBooks);
            while (resultSet.next()) {
                Book book = new Book(resultSet.getString("isbn"), resultSet.getString("title"), resultSet.getString("author"));
                books.add(book);
            }
        } catch (SQLException exc) {
            System.out.println("Error getting all books: " + exc.getMessage());
        }

        return books;
    }

    @Override
    public void deleteBook(String isbn) {
        String sqlDeleteBook = "DELETE FROM books WHERE isbn = '" + isbn + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlDeleteBook);
            System.out.println("Book deleted successfully...");
        } catch (SQLException exc) {
            System.out.println("Error deleting book: " + exc.getMessage());
        }
    }
}
