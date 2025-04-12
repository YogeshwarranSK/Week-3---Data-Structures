package Day16;
import java.util.*;

public class CollectionSearchComparison {
    public static void main(String[] args) {
        int n = 1_000_000;
        int[] array = new int[n];
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            array[i] = i;
            hashSet.add(i);
            treeSet.add(i);
        }

        long startTime = System.currentTimeMillis();
        System.out.println("Array search: " + searchArray(array, n-1));
        long endTime = System.currentTimeMillis();
        System.out.println("Array search time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        System.out.println("HashSet search: " + searchHashSet(hashSet, n-1));
        endTime = System.currentTimeMillis();
        System.out.println("HashSet search time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        System.out.println("TreeSet search: " + searchTreeSet(treeSet, n-1));
        endTime = System.currentTimeMillis();
        System.out.println("TreeSet search time: " + (endTime - startTime) + " ms");
    }

    public static boolean searchArray(int[] array, int key) {
        for (int num : array) {
            if (num == key) return true;
        }
        return false;
    }

    public static boolean searchHashSet(HashSet<Integer> set, int key) {
        return set.contains(key);
    }

    public static boolean searchTreeSet(TreeSet<Integer> set, int key) {
        return set.contains(key);
    }
}

