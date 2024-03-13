package librarymanagerpublisher;

import java.util.List;

public interface LibraryManagerInterface {
    void addBook(Book book);
    Book searchBook(String isbn);
    List<Book> getAllBooks();
    void deleteBook(String isbn);
}
