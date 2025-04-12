package Day15;
public class StringConcatenator {
    public static String concatenateString(String[] strings) {
        StringBuilder stringbuffer = new StringBuilder();
        for (String str : strings) {
            stringbuffer.append(str);
        }
        return stringbuffer.toString();
    }
    public static void main(String[] args) {
        String[] input = {"Java", " is"," the ", " most", " used ", "programming ","language" };
        String result = concatenateString(input);
        System.out.println("Concatenated String: " + result);
    }
}
