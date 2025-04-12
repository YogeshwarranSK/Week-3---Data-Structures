package Day15;
import java.util.HashSet;
public class RemoveDuplicateCharacters {
    public static String removeDuplicate(String input) {
        StringBuilder stringB = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();
        for (char ch : input.toCharArray()) {
            if (!seen.contains(ch)) {
                seen.add(ch);
                stringB.append(ch);
            }
        }
        return stringB.toString();
    }
    public static void main(String[] args) {
        String input = "Bitter-Butter";
        String result = removeDuplicate(input);
        System.out.println("Without duplicates: " + result);
    }
}
