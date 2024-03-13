package librarymanagerconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import librarymanagerpublisher.LibraryManagerInterface;
import librarymanagerpublisher.Book; // Import the Book class from the publisher package

public class Activator implements BundleActivator {

    ServiceReference serviceReference;

    public void start(BundleContext context) throws Exception {
        System.out.println("Start Library Management Service");
        serviceReference = context.getServiceReference(LibraryManagerInterface.class.getName());
        if (serviceReference != null) {
            LibraryManagerInterface libraryInterface = (LibraryManagerInterface) context.getService(serviceReference);
            displayMainMenu(libraryInterface);
        } else {  
            System.out.println("Error: LibraryManagerInterface service reference not found!");
        }
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Consumer Stopped!");
        context.ungetService(serviceReference);
    }

    public void displayMainMenu(LibraryManagerInterface libraryInterface) {

        int option;
        String suboption = "y";
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("---------- Library Management System ----------");
        System.out.println("1  - Add Book");
        System.out.println("2  - List All Books");
        System.out.println("3  - Search Book by ISBN");
        System.out.println("4  - Delete Book by ISBN");
        System.out.print("Select an option : ");

        try {
            option = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            displayMainMenu(libraryInterface);
            return;
        }

        switch (option) {
            case 1:
                addBook(libraryInterface, scanner);
                break;
            case 2:
            	 listAllBooks(libraryInterface); 
                displayMainMenu(libraryInterface);
                break;
            case 3:
                searchBook(libraryInterface, scanner);
                break;
            case 4:
                deleteBook(libraryInterface, scanner);
                break;
            default:
                System.out.println("Incorrect Input... Try Again");
                displayMainMenu(libraryInterface);
        }

        scanner.close(); // Close the scanner to prevent resource leak
    }

    private void addBook(LibraryManagerInterface libraryInterface, Scanner scanner) {
        Book book = new Book();
        System.out.println("Enter Book ISBN: ");
        String isbn = scanner.nextLine().trim();
        book.setIsbn(isbn);

        System.out.println("Enter Book Title: ");
        String title = scanner.nextLine().trim();
        book.setTitle(title);

        System.out.println("Enter Book Author: ");
        String author = scanner.nextLine().trim();
        book.setAuthor(author);

        

        libraryInterface.addBook(book);
        displayMainMenu(libraryInterface);
    }

    @SuppressWarnings("unused")
	private void listAllBooks(LibraryManagerInterface libraryInterface) {
        List<Book> books = libraryInterface.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            System.out.println("List of Books:");
            for (Book book : books) {
                System.out.println("ISBN: " + book.getIsbn() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
        displayMainMenu(libraryInterface);
    }

    
    private void searchBook(LibraryManagerInterface libraryInterface, Scanner scanner) {
        System.out.println("Enter Book ISBN to search: ");
        String isbn = scanner.nextLine().trim();
        Book foundBook = libraryInterface.searchBook(isbn);
        if (foundBook != null) {
            System.out.println("Book found: " + foundBook.getTitle() + " by " + foundBook.getAuthor());
        } else {
            System.out.println("Book not found!");
        }
        displayMainMenu(libraryInterface);
    }

    private void deleteBook(LibraryManagerInterface libraryInterface, Scanner scanner) {
        System.out.println("Enter Book ISBN to delete: ");
        String isbn = scanner.nextLine().trim();
        libraryInterface.deleteBook(isbn);
        System.out.println("Book deleted successfully!");
        displayMainMenu(libraryInterface);
    }
}
