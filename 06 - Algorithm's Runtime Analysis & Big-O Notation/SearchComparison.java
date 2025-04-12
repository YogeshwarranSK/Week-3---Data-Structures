package Day16;
import java.util.Arrays;
import java.util.Random;

public class SearchComparison {

    public static int linearSearch(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] data, int target) {
        int left = 0, right = data.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] == target) return mid;
            if (data[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};

        for (int size : sizes) {
            int[] data = generateArray(size);
            int target = data[size / 2];

            long start = System.nanoTime();
            linearSearch(data, target);
            long linearTime = System.nanoTime() - start;

            Arrays.sort(data);
            start = System.nanoTime();
            binarySearch(data, target);
            long binaryTime = System.nanoTime() - start;

            System.out.printf("Dataset Size: %,d%n", size);
            System.out.printf("Linear Search Time: %.3f ms%n", linearTime / 1_000_000.0);
            System.out.printf("Binary Search Time: %.3f ms%n%n", binaryTime / 1_000_000.0);
        }
    }
}
