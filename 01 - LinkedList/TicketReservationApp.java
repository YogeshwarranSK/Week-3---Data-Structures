package Day12;
import java.util.Scanner;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName.trim();
        this.movieName = movieName.trim();
        this.seatNumber = seatNumber.trim();
        this.bookingTime = bookingTime.trim();
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null;
    private Ticket tail = null;

    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
        System.out.println("Ticket booked successfully.");
    }

    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket current = head;
        Ticket prev = tail;
        boolean found = false;

        do {
            if (current.ticketId == ticketId) {
                found = true;
                if (current == head && current == tail) {
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                }
                System.out.println("Ticket removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        if (!found) System.out.println("Ticket ID not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        System.out.println("Current Bookings:");
        do {
            printTicket(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTickets(String query) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(query.trim()) || temp.movieName.equalsIgnoreCase(query.trim())) {
                printTicket(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) System.out.println("No matching tickets found.");
    }

    public void countTickets() {
        if (head == null) {
            System.out.println("Total Tickets: 0");
            return;
        }

        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Total Tickets: " + count);
    }

    private void printTicket(Ticket t) {
        System.out.println("Ticket ID: " + t.ticketId + ", Customer: " + t.customerName +
                ", Movie: " + t.movieName + ", Seat: " + t.seatNumber + ", Time: " + t.bookingTime);
    }
}

public class TicketReservationApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketReservationSystem trs = new TicketReservationSystem();

        while (true) {
            System.out.println("\n1. Book Ticket\n2. Cancel Ticket\n3. Display All Tickets");
            System.out.println("4. Search Tickets\n5. Count Tickets\n6. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Ticket ID: ");
                    int id = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Customer Name: ");
                    String name = sc.nextLine().trim().replaceAll("//.*", "");

                    System.out.print("Movie Name: ");
                    String movie = sc.nextLine().trim();

                    System.out.print("Seat Number: ");
                    String seat = sc.nextLine().trim();

                    System.out.print("Booking Time: ");
                    String time = sc.nextLine().trim();

                    trs.addTicket(id, name, movie, seat, time);
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    int rid = Integer.parseInt(sc.nextLine().trim());
                    trs.removeTicket(rid);
                    break;

                case 3:
                    trs.displayTickets();
                    break;

                case 4:
                    System.out.print("Search by Customer or Movie Name: ");
                    String query = sc.nextLine();
                    trs.searchTickets(query);
                    break;

                case 5:
                    trs.countTickets();
                    break;

                case 6:
                    System.out.println("Thank you for using the system!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
