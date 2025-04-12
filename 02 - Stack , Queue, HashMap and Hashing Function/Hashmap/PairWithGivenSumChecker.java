package Day13.Hashmap;
import java.util.HashSet;

public class PairWithGivenSumChecker {
    public static boolean hasPairWithSum(int[] nums, int target) {
        HashSet<Integer> visited = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(target - num)) {
                return true;
            }
            visited.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 6, 8, 10};
        int target = 14;
        System.out.println(hasPairWithSum(nums, target));
    }
}

