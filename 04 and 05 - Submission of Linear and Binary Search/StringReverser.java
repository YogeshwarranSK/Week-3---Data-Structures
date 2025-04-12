package Day15;
public class StringReverser {
    public static String Main(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.reverse();
        return sb.toString();
    }
    public static void main(String[] args) {
        String input = "Java";
        String reversed = Main(input);
        System.out.println("Reversed: " + reversed);
    }
}