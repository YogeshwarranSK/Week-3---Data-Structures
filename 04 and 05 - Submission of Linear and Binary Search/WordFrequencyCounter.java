package Day15;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String filePath = "D:\\Capgemini\\Java\\src\\Day15\\Inheritance.txt";
        String targetWord = "class";
        int wordCount = 0;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split line into words using space and punctuation as delimiters
                String[] words = line.split("[\\s\\p{Punct}]+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        wordCount++;
                    }
                }
            }

            bufferedReader.close();
            System.out.println("The word \"" + targetWord + "\" appears " + wordCount + " times in the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file:");
            e.printStackTrace();
        }
    }
}
