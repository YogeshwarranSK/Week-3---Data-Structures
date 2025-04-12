package Day13;
import java.util.Stack;

class QueueUsingStacks {
    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public QueueUsingStacks() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void enqueue(int x) {
        stackIn.push(x);
        System.out.println(x + " added to the queue.");
    }

    public int dequeue() {
        if (stackOut.isEmpty()) {
            if (stackIn.isEmpty()) {
                throw new IllegalStateException("Queue is empty!");
            }
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    public int peek() {
        if (stackOut.isEmpty()) {
            if (stackIn.isEmpty()) {
                throw new IllegalStateException("Queue is empty!");
            }
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public int size() {
        return stackIn.size() + stackOut.size();
    }

    public void displayQueue() {
        System.out.println("Queue elements: ");
        if (!stackOut.isEmpty()) {
            System.out.println(stackOut);
        }
        if (!stackIn.isEmpty()) {
            System.out.println(stackIn);
        }
    }
}

public class QueueUsingStack {
    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.displayQueue();
        System.out.println("Dequeued: " + queue.dequeue());
        queue.displayQueue();
        System.out.println("Peek: " + queue.peek());
        queue.displayQueue();
    }
}
