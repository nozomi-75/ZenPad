import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryCatchFinally {
    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("sample.txt"));
            String line; 
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println();
            System.out.println("Finished reading the file...");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage() + ". Finished execution.");
        } finally {
            if (br != null) {
                try {
                    br.close();
                    System.out.println("BufferedReader is closed. Finished execution.");
                } catch (IOException e) {
                    System.out.println("Error closing BufferedReader: " + e.getMessage());
                }
            }
        }

        System.out.println("Program execution continues after finally.");
    }
}