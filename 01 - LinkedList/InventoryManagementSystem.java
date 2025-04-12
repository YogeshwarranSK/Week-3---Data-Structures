package Day12;
import java.util.Scanner;

class ItemNode {
    String name;
    String itemId;
    int quantity;
    double price;
    ItemNode next;

    ItemNode(String name, String itemId, int quantity, double price) {
        this.name = name.trim();
        this.itemId = itemId.trim();
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventoryLinkedList {
    private ItemNode head;

    public void addAtBeginning(String name, String itemId, int quantity, double price) {
        ItemNode newNode = new ItemNode(name, itemId, quantity, price);
        newNode.next = head;
        head = newNode;
    }

    public void addAtEnd(String name, String itemId, int quantity, double price) {
        ItemNode newNode = new ItemNode(name, itemId, quantity, price);
        if (head == null) {
            head = newNode;
            return;
        }
        ItemNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addAtPosition(int position, String name, String itemId, int quantity, double price) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }

        if (position == 1) {
            addAtBeginning(name, itemId, quantity, price);
            return;
        }

        ItemNode newNode = new ItemNode(name, itemId, quantity, price);
        ItemNode temp = head;

        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of range.");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void removeById(String itemId) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.itemId.equalsIgnoreCase(itemId)) {
            head = head.next;
            System.out.println("Item removed.");
            return;
        }

        ItemNode current = head;
        while (current.next != null && !current.next.itemId.equalsIgnoreCase(itemId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Item ID not found.");
        } else {
            current.next = current.next.next;
            System.out.println("Item removed.");
        }
    }

    public void updateQuantity(String itemId, int quantity) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemId.equalsIgnoreCase(itemId)) {
                temp.quantity = quantity;
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void searchById(String itemId) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemId.equalsIgnoreCase(itemId)) {
                displayItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void searchByName(String name) {
        ItemNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name.trim())) {
                displayItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item name not found.");
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Inventory:");
        ItemNode temp = head;
        while (temp != null) {
            displayItem(temp);
            temp = temp.next;
        }
    }

    private void displayItem(ItemNode node) {
        System.out.println("Name: " + node.name + ", ID: " + node.itemId +
                ", Qty: " + node.quantity + ", Price: " + node.price);
    }

    public void calculateTotalValue() {
        double total = 0;
        ItemNode temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.printf("Total Inventory Value: $%.2f\n", total);
    }

    public void sort(String field, boolean ascending) {
        head = mergeSort(head, field, ascending);
        System.out.println("Inventory sorted by " + field + " in " + (ascending ? "ascending" : "descending") + " order.");
    }

    private ItemNode mergeSort(ItemNode head, String field, boolean ascending) {
        if (head == null || head.next == null) return head;

        ItemNode middle = getMiddle(head);
        ItemNode nextToMiddle = middle.next;
        middle.next = null;

        ItemNode left = mergeSort(head, field, ascending);
        ItemNode right = mergeSort(nextToMiddle, field, ascending);

        return sortedMerge(left, right, field, ascending);
    }

    private ItemNode sortedMerge(ItemNode a, ItemNode b, String field, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        int comparison;
        if (field.equalsIgnoreCase("name")) {
            comparison = a.name.compareToIgnoreCase(b.name);
        } else {
            comparison = Double.compare(a.price, b.price);
        }

        if ((ascending && comparison <= 0) || (!ascending && comparison > 0)) {
            a.next = sortedMerge(a.next, b, field, ascending);
            return a;
        } else {
            b.next = sortedMerge(a, b.next, field, ascending);
            return b;
        }
    }

    private ItemNode getMiddle(ItemNode head) {
        if (head == null) return head;
        ItemNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryLinkedList inventory = new InventoryLinkedList();
        int choice;

        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by Item ID");
            System.out.println("5. Update Quantity\n6. Search by ID\n7. Search by Name\n8. Display All");
            System.out.println("9. Total Inventory Value\n10. Sort by Name\n11. Sort by Price\n12. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Name, Item ID, Quantity, Price: ");
                    String name = sc.nextLine();
                    String id = sc.nextLine();
                    int qty = Integer.parseInt(sc.nextLine().trim());
                    double price = Double.parseDouble(sc.nextLine().trim());
                    if (choice == 1) inventory.addAtBeginning(name, id, qty, price);
                    else if (choice == 2) inventory.addAtEnd(name, id, qty, price);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = Integer.parseInt(sc.nextLine().trim());
                        inventory.addAtPosition(pos, name, id, qty, price);
                    }
                    break;
                case 4:
                    System.out.print("Enter Item ID to remove: ");
                    inventory.removeById(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Item ID and new Quantity: ");
                    inventory.updateQuantity(sc.nextLine(), Integer.parseInt(sc.nextLine().trim()));
                    break;
                case 6:
                    System.out.print("Enter Item ID: ");
                    inventory.searchById(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Enter Item Name: ");
                    inventory.searchByName(sc.nextLine());
                    break;
                case 8:
                    inventory.displayAll();
                    break;
                case 9:
                    inventory.calculateTotalValue();
                    break;
                case 10:
                    System.out.print("Ascending? (true/false): ");
                    inventory.sort("name", Boolean.parseBoolean(sc.nextLine().trim()));
                    break;
                case 11:
                    System.out.print("Ascending? (true/false): ");
                    inventory.sort("price", Boolean.parseBoolean(sc.nextLine().trim()));
                    break;
                case 12:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 12);

        sc.close();
    }
}

