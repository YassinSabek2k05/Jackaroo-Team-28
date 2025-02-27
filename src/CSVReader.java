import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\pc\\Desktop\\Cs\\cs4\\java\\Jackaroo\\Cards.csv"; // If inside the project folder
        // String filePath = "C:\\path\\to\\your\\file.csv"; // Absolute path for Windows
        ArrayList<String[]> a = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Adjust if using a different delimiter
                for (String value : values) {
                    
                    for (String str : value.split(" ")) {
                        System.out.print(str + " ");
                    }
                    System.out.println();
                }
                System.out.println(); // New line after each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
