package Day12;
import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    public Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name.trim();
        this.priority = priority;
        this.dueDate = dueDate.trim();
    }
}

class TaskScheduler {
    private Task head = null;
    private Task current = null;

    public void addTaskAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
    }

    public void addTaskAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }

    public void addTaskAtPosition(int id, String name, int priority, String dueDate, int position) {
        if (position <= 1) {
            addTaskAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        int count = 1;
        while (count < position - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }
        newTask.next = temp.next;
        temp.next = newTask;
    }

    public void removeTaskById(int id) {
        if (head == null) return;

        Task temp = head, prev = null;

        if (head.id == id) {
            if (head.next == head) {
                head = null;
                current = null;
                return;
            }
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = head.next;
            if (current == head) current = head.next;
            head = head.next;
            return;
        }

        do {
            prev = temp;
            temp = temp.next;
            if (temp.id == id) {
                prev.next = temp.next;
                if (current == temp) current = current.next;
                return;
            }
        } while (temp != head);
    }

    public void viewCurrentTask() {
        if (current != null) {
            System.out.println("Current Task:");
            printTask(current);
        } else {
            System.out.println("No current task.");
        }
    }

    public void moveToNextTask() {
        if (current != null) {
            current = current.next;
            viewCurrentTask();
        }
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks.");
            return;
        }

        Task temp = head;
        System.out.println("All Tasks:");
        do {
            printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }

        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                printTask(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) System.out.println("No task found with priority " + priority);
    }

    private void printTask(Task task) {
        System.out.println("ID: " + task.id + ", Name: " + task.name +
                ", Priority: " + task.priority + ", Due Date: " + task.dueDate);
    }
}

public class TaskManagerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();

        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position");
            System.out.println("4. Remove by ID\n5. View Current\n6. Next Task");
            System.out.println("7. Display All\n8. Search by Priority\n9. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Task ID: ");
                    int id = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Task Name: ");
                    String name = sc.nextLine().replaceAll("//.*", "").trim();

                    System.out.print("Priority: ");
                    int priority = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Due Date: ");
                    String dueDate = sc.nextLine().replaceAll("//.*", "").trim();

                    if (choice == 1) scheduler.addTaskAtBeginning(id, name, priority, dueDate);
                    else if (choice == 2) scheduler.addTaskAtEnd(id, name, priority, dueDate);
                    else {
                        System.out.print("Position: ");
                        int pos = Integer.parseInt(sc.nextLine().trim());
                        scheduler.addTaskAtPosition(id, name, priority, dueDate, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    int removeId = Integer.parseInt(sc.nextLine().trim());
                    scheduler.removeTaskById(removeId);
                    break;

                case 5:
                    scheduler.viewCurrentTask();
                    break;

                case 6:
                    scheduler.moveToNextTask();
                    break;

                case 7:
                    scheduler.displayAllTasks();
                    break;

                case 8:
                    System.out.print("Enter priority to search: ");
                    int searchPriority = Integer.parseInt(sc.nextLine().trim());
                    scheduler.searchTaskByPriority(searchPriority);
                    break;

                case 9:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}