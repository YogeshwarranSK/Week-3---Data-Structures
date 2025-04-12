package Day16;
public class FibonacciComparison {
    public static void main(String[] args) {
        int n = 50;
        long startTime = System.currentTimeMillis();
        System.out.println("Recursive result: " + fibonacciRecursive(n));
        long endTime = System.currentTimeMillis();
        System.out.println("Recursive time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        System.out.println("Iterative result: " + fibonacciIterative(n));
        endTime = System.currentTimeMillis();
        System.out.println("Iterative time: " + (endTime - startTime) + " ms");
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}
