package Day13;
import java.util.Stack;

public class RecursiveStackSorter {
    public static void sort(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int top = stack.pop();
        sort(stack);
        insertSorted(stack, top);
    }

    private static void insertSorted(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }
        int temp = stack.pop();
        insertSorted(stack, element);
        stack.push(temp);
    }

    public static void displayStack(Stack<Integer> stack) {
        for (int num : stack) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        displayStack(stack);
        sort(stack);
        displayStack(stack);
    }
}
