package Day16;
import java.io.*;

public class FileReadPerformanceTest {
    public static void main(String[] args) throws Exception {
        String filePath = "largefile.txt";
        benchmarkFileReader(filePath);
        benchmarkInputStreamReader(filePath);
    }

    private static void benchmarkFileReader(String filePath) throws Exception {
        long start = System.currentTimeMillis();
        FileReader reader = new FileReader(filePath);
        while (reader.read() != -1) {}
        reader.close();
        long end = System.currentTimeMillis();
        System.out.println("FileReader time: " + (end - start) + " ms");
    }

    private static void benchmarkInputStreamReader(String filePath) throws Exception {
        long start = System.currentTimeMillis();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath));
        while (reader.read() != -1) {}
        reader.close();
        long end = System.currentTimeMillis();
        System.out.println("InputStreamReader time: " + (end - start) + " ms");
    }
}
