package Day15;
public class FirstNegativeFinder {
    public static int findFirstNegative(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {5, 12, 3, 0, -7, 4, -2};
        int index = findFirstNegative(numbers);
        System.out.println("Index of first negative number: " + index);
    }
}

