package Day12;
import java.util.Scanner;

class StudentNode {
    int rollNo;
    String name;
    int age;
    String grade;
    StudentNode next;

    StudentNode(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name.trim();
        this.age = age;
        this.grade = grade.trim();
        this.next = null;
    }
}

class StudentLinkedList {
    private StudentNode head;

    public void addAtBeginning(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        newNode.next = head;
        head = newNode;
    }

    public void addAtEnd(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        if (head == null) {
            head = newNode;
            return;
        }
        StudentNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addAtPosition(int position, int rollNo, String name, int age, String grade) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }

        if (position == 1) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }

        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        StudentNode temp = head;
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

    public void deleteByRollNo(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Record deleted.");
            return;
        }

        StudentNode current = head;
        while (current.next != null && current.next.rollNo != rollNo) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Roll number not found.");
        } else {
            current.next = current.next.next;
            System.out.println("Record deleted.");
        }
    }

    public void searchByRollNo(int rollNo) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Found: " + temp.rollNo + ", " + temp.name + ", " + temp.age + ", " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Roll number not found.");
    }

    public void updateGrade(int rollNo, String newGrade) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade.trim();
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Roll number not found.");
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No records to display.");
            return;
        }

        StudentNode temp = head;
        System.out.println("Student Records:");
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();
        int choice;

        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Delete by Roll No");
            System.out.println("5. Search by Roll No\n6. Update Grade\n7. Display All\n8. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    list.addAtBeginning(
                            Integer.parseInt(sc.nextLine().trim()),
                            sc.nextLine().trim(),
                            Integer.parseInt(sc.nextLine().trim()),
                            sc.nextLine().trim()
                    );
                    break;
                case 2:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    list.addAtEnd(
                            Integer.parseInt(sc.nextLine().trim()),
                            sc.nextLine().trim(),
                            Integer.parseInt(sc.nextLine().trim()),
                            sc.nextLine().trim()
                    );
                    break;
                case 3:
                    System.out.print("Enter Position: ");
                    int pos = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    list.addAtPosition(
                            pos,
                            Integer.parseInt(sc.nextLine().trim()),
                            sc.nextLine().trim(),
                            Integer.parseInt(sc.nextLine().trim()),
                            sc.nextLine().trim()
                    );
                    break;
                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    list.deleteByRollNo(Integer.parseInt(sc.nextLine().trim()));
                    break;
                case 5:
                    System.out.print("Enter Roll No to search: ");
                    list.searchByRollNo(Integer.parseInt(sc.nextLine().trim()));
                    break;
                case 6:
                    System.out.print("Enter Roll No and New Grade: ");
                    list.updateGrade(
                            Integer.parseInt(sc.nextLine().trim()),
                            sc.nextLine().trim()
                    );
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        sc.close();
    }
}
