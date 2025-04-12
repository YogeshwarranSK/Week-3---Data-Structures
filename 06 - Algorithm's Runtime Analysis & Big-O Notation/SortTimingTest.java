package Day16;
import java.util.Random;
import java.util.Arrays;

public class SortTimingTest {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);
            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(size * 10);
        return arr;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};

        for (int size : sizes) {
            System.out.printf("Dataset Size: %,d%n", size);

            int[] original = generateArray(size);

            if (size <= 10000) {
                int[] bubble = Arrays.copyOf(original, original.length);
                long start = System.nanoTime();
                bubbleSort(bubble);
                long bubbleTime = System.nanoTime() - start;
                System.out.printf("Bubble Sort Time: %.3f ms%n", bubbleTime / 1_000_000.0);
            } else {
                System.out.println("Bubble Sort Time: Skipped (Too Slow)");
            }

            int[] merge = Arrays.copyOf(original, original.length);
            long start = System.nanoTime();
            mergeSort(merge);
            long mergeTime = System.nanoTime() - start;
            System.out.printf("Merge Sort Time: %.3f ms%n", mergeTime / 1_000_000.0);

            int[] quick = Arrays.copyOf(original, original.length);
            start = System.nanoTime();
            quickSort(quick, 0, quick.length - 1);
            long quickTime = System.nanoTime() - start;
            System.out.printf("Quick Sort Time: %.3f ms%n%n", quickTime / 1_000_000.0);
        }
    }
}

