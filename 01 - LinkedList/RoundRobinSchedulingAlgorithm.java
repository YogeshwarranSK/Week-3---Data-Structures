package Day12;
import java.util.*;

class ProcessNode {
    String processId;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    ProcessNode next;

    ProcessNode(String processId, int burstTime, int priority) {
        this.processId = processId.trim();
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private ProcessNode tail;

    public void addProcess(String processId, int burstTime, int priority) {
        ProcessNode newNode = new ProcessNode(processId, burstTime, priority);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        System.out.println("Process added: " + processId);
    }

    public void simulate(int timeQuantum) {
        if (tail == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        System.out.println("\n--- Starting Round Robin Scheduling ---");
        ProcessNode current = tail.next;
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int processCount = getCount();

        while (tail != null) {
            if (current.remainingTime > 0) {
                int execTime = Math.min(current.remainingTime, timeQuantum);
                current.remainingTime -= execTime;
                currentTime += execTime;

                System.out.println("Process " + current.processId + " executed for " + execTime + " units.");

                if (current.remainingTime == 0) {
                    current.turnaroundTime = currentTime;
                    current.waitingTime = current.turnaroundTime - current.burstTime;
                    totalWaitingTime += current.waitingTime;
                    totalTurnaroundTime += current.turnaroundTime;

                    System.out.println("Process " + current.processId + " completed. Removed from queue.");
                    removeProcess(current.processId);
                    if (tail == null) break;
                    current = tail.next;
                } else {
                    current = current.next;
                }

                displayQueue();
            } else {
                current = current.next;
            }
        }

        double avgWaitingTime = (double) totalWaitingTime / processCount;
        double avgTurnaroundTime = (double) totalTurnaroundTime / processCount;

        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
    }

    public void removeProcess(String processId) {
        if (tail == null) return;

        ProcessNode current = tail.next;
        ProcessNode prev = tail;

        do {
            if (current.processId.equalsIgnoreCase(processId)) {
                if (current == tail && current == tail.next) {
                    tail = null;
                } else {
                    if (current == tail) {
                        tail = prev;
                    }
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != tail.next);
    }

    public void displayQueue() {
        if (tail == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Current Process Queue:");
        ProcessNode current = tail.next;
        do {
            System.out.println("Process ID: " + current.processId +
                    ", Remaining Time: " + current.remainingTime +
                    ", Priority: " + current.priority);
            current = current.next;
        } while (current != tail.next);
    }

    private int getCount() {
        if (tail == null) return 0;
        int count = 0;
        ProcessNode current = tail.next;
        do {
            count++;
            current = current.next;
        } while (current != tail.next);
        return count;
    }
}

public class RoundRobinSchedulingAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        int choice;
        do {
            System.out.println("\n--- Round Robin Scheduler ---");
            System.out.println("1. Add Process");
            System.out.println("2. Simulate Scheduling");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Enter Burst Time: ");
                    int bt = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Enter Priority: ");
                    int pr = Integer.parseInt(sc.nextLine().trim());
                    scheduler.addProcess(id, bt, pr);
                    break;
                case 2:
                    System.out.print("Enter Time Quantum: ");
                    int tq = Integer.parseInt(sc.nextLine().trim());
                    scheduler.simulate(tq);
                    break;
                case 3:
                    System.out.println("Exiting Scheduler...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
        sc.close();
    }
}
