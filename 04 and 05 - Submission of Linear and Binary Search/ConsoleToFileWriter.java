package Day15;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConsoleToFileWriter {
    public static void main(String[] args) {
        String filePath =  "D:\\Capgemini\\Java\\src\\Day15\\Inheritance.txt";

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            FileWriter fileWriter = new FileWriter(filePath);

            String inputLine;
            while (true) {
                inputLine = bufferedReader.readLine();
                if ("exit".equalsIgnoreCase(inputLine)) {
                    break;
                }
                fileWriter.write(inputLine + System.lineSeparator());
            }

            fileWriter.close();
            System.out.println("Input saved to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    }
}

