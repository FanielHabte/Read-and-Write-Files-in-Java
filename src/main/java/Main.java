import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // reads the file
        // prints records
        // filters category = Electronics
        // writes a new output file with only member customers

        Scanner scanner = new Scanner(System.in);
        ConsoleUI consoleUI = new ConsoleUI();
        ArrayList<HashMap<String, Object>> dataSet = new ArrayList<>();

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
                        case "csv" -> dataSet = parse.csvFile();
                        case "html" -> dataSet = parse.htmlFile(filePath);
                        case "json" -> dataSet = parse.jsonFile();
                        case "parquet" -> parse.parquetFile();
                        case "txt" -> dataSet = parse.textFile();
                        case "xml" -> dataSet = parse.xmlFile(filePath);
                    }
                 }
                catch (IOException e) {
                    System.out.println("Error occurred while reading the file!\n" + e);
                }
                catch (ParserConfigurationException e) {
                    System.out.println("Error occurred during configuring the xml parsing!\n" + e);
                }
                catch (SAXException e) {
                    System.out.println("Error occurred while parsing the XML file!\n" + e);
                }

                break;

            }

            catch(FileNotFoundException e){
                System.out.println("\n!! Your file was not found. Please try again !!");
            }

        }

        // reads the file
        // prints records
        // filters category = Electronics
        // writes a new output file with only member customers
        System.out.println("\nQuestion #1: Print all records? \n");
        consoleUI.showAllRecords(dataSet);

        System.out.println("\nQuestion #2: Print order of Electronics category type?? \n");
        consoleUI.showFilteredRecords(dataSet, "Electronics", "category");



    }
}