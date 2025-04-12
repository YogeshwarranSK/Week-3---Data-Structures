package Day14;

public class BubbleSortDemo {
    public static void bubbleSort(int[] marks) {
        int n = marks.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (marks[i] > marks[i + 1]) {
                    int temp = marks[i];
                    marks[i] = marks[i + 1];
                    marks[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    public static void main(String[] args) {
        int[] marks = {78, 45, 89, 32, 66, 55};
        bubbleSort(marks);
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
    }
}
