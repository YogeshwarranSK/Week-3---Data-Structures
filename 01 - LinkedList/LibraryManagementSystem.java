package Day12;
import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title.trim();
        this.author = author.trim();
        this.genre = genre.trim();
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}

class Library {
    private Book head = null;
    private Book tail = null;

    public void addBookAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addBookAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addBookAtPosition(String title, String author, String genre, int bookId, boolean isAvailable, int position) {
        if (position <= 1) {
            addBookAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }

        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book temp = head;
        int count = 1;

        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null || temp.next == null) {
            addBookAtEnd(title, author, genre, bookId, isAvailable);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            temp.next.prev = newBook;
            temp.next = newBook;
        }
    }

    public void removeBookById(int bookId) {
        if (head == null) return;

        Book temp = head;

        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp == head) {
                    head = temp.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                } else if (temp == tail) {
                    tail = temp.prev;
                    if (tail != null) tail.next = null;
                    else head = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                System.out.println("Book removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }

    public void searchBook(String query) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(query.trim()) || temp.author.equalsIgnoreCase(query.trim())) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) System.out.println("No match found.");
    }

    public void updateAvailability(int bookId, boolean isAvailable) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book ID not found.");
    }

    public void displayForward() {
        Book temp = head;
        System.out.println("Books in Forward Order:");
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Book temp = tail;
        System.out.println("Books in Reverse Order:");
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total Books: " + count);
    }

    private void printBook(Book book) {
        System.out.println("ID: " + book.bookId + ", Title: " + book.title + ", Author: " + book.author +
                ", Genre: " + book.genre + ", Available: " + (book.isAvailable ? "Yes" : "No"));
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position");
            System.out.println("4. Remove by ID\n5. Search by Title/Author\n6. Update Availability");
            System.out.println("7. Display Forward\n8. Display Reverse\n9. Count Books\n10. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Book Title: ");
                    String title = sc.nextLine().trim().replaceAll("//.*", "");

                    System.out.print("Author: ");
                    String author = sc.nextLine().trim().replaceAll("//.*", "");

                    System.out.print("Genre: ");
                    String genre = sc.nextLine().trim();

                    System.out.print("Book ID: ");
                    int id = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Available (true/false): ");
                    boolean available = Boolean.parseBoolean(sc.nextLine().trim());

                    if (choice == 1) library.addBookAtBeginning(title, author, genre, id, available);
                    else if (choice == 2) library.addBookAtEnd(title, author, genre, id, available);
                    else {
                        System.out.print("Position: ");
                        int pos = Integer.parseInt(sc.nextLine().trim());
                        library.addBookAtPosition(title, author, genre, id, available, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    int removeId = Integer.parseInt(sc.nextLine().trim());
                    library.removeBookById(removeId);
                    break;

                case 5:
                    System.out.print("Enter Title or Author to search: ");
                    String query = sc.nextLine();
                    library.searchBook(query);
                    break;

                case 6:
                    System.out.print("Enter Book ID: ");
                    int bookId = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("New Availability (true/false): ");
                    boolean newStatus = Boolean.parseBoolean(sc.nextLine().trim());
                    library.updateAvailability(bookId, newStatus);
                    break;

                case 7:
                    library.displayForward();
                    break;

                case 8:
                    library.displayReverse();
                    break;

                case 9:
                    library.countBooks();
                    break;

                case 10:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
