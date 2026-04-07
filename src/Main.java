import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // reads the file
        // prints records
        // filters category = Electronics
        // writes a new output file with only member customers

        Scanner scanner = new Scanner(System.in);

        System.out.println("*****************************************");
        System.out.println("************  File Parser  **************");
        System.out.println("*****************************************");

        while (true) {

            System.out.print("\nWhat type of file would you like to parse?\n(csv, html, json, parquet, txt, xml): ");
            String fileType = scanner.nextLine();
            String filePath = "Files/sample_orders." + fileType.strip().toLowerCase();

            try {

                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                Parse parse = new Parse(bufferedReader);

                try {
                    switch (fileType) {
                        case "csv" -> parse.csvFile();
                        case "html" -> parse.htmlFile();
                        case "json" -> parse.jsonFile();
                        case "parquet" -> parse.parquetFile();
                        case "txt" -> parse.textFile();
                        case "xml" -> parse.xmlFile();
                    }
                 }
                catch (IOException e) {
                    System.out.println("Error occurred while reading the file!\n" + e);
                }
                break;
            }

            catch(FileNotFoundException e){
                System.out.println("\n!! Your file was not found. Please try again !!");
            }

        }
    }
}