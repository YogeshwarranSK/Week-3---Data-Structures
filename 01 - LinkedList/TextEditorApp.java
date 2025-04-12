package Day12;
import java.util.Scanner;

class TextStateNode {
    String content;
    TextStateNode prev, next;

    TextStateNode(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditorHistory {
    private TextStateNode head;
    private TextStateNode tail;
    private TextStateNode current;
    private int size = 0;
    private final int MAX_HISTORY;

    public TextEditorHistory(int maxHistory) {
        this.MAX_HISTORY = maxHistory;
    }

    public void addState(String content) {
        TextStateNode newState = new TextStateNode(content);

        // Discard all redo history
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
        }

        if (head == null) {
            head = tail = current = newState;
            size = 1;
        } else {
            tail.next = newState;
            newState.prev = tail;
            tail = newState;
            current = tail;
            size++;
            if (size > MAX_HISTORY) {
                // Remove oldest state
                head = head.next;
                head.prev = null;
                size--;
            }
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo successful.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo successful.");
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State:\n" + current.content);
        } else {
            System.out.println("Editor is empty.");
        }
    }
}

public class TextEditorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditorHistory editor = new TextEditorHistory(10);
        int choice;

        do {
            System.out.println("\n--- Text Editor ---");
            System.out.println("1. Type/Add Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Show Current State");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Enter new text state: ");
                    String content = sc.nextLine();
                    editor.addState(content);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrentState();
                    break;
                case 5:
                    System.out.println("Exiting editor...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
