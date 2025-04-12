package Day15;
import java.io.*;

public class FileReadingPerformanceTest {
    public static void main(String[] args) {
        String[] words = {"hello"};
        int iterations = 1_000_000;

        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            for (String word : words) {
                sb.append(word);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            for (String word : words) {
                sbuf.append(word);
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endTime - startTime) + " ms");

        String filePath = "D:\\Capgemini\\Java\\src\\Day15\\Inheritance.txt";

        try {
            startTime = System.currentTimeMillis();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            int wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                wordCount += line.split("\\s+").length;
            }
            reader.close();
            endTime = System.currentTimeMillis();
            System.out.println("FileReader word count: " + wordCount);
            System.out.println("FileReader time: " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            System.out.println("Error using FileReader:");
            e.printStackTrace();
        }

        try {
            startTime = System.currentTimeMillis();
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader inputReader = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(inputReader);

            int wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                wordCount += line.split("\\s+").length;
            }
            reader.close();
            endTime = System.currentTimeMillis();
            System.out.println("InputStreamReader word count: " + wordCount);
            System.out.println("InputStreamReader time: " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            System.out.println("Error using InputStreamReader:");
            e.printStackTrace();
        }
    }
}
