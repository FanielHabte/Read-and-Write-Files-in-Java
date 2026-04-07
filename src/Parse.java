import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Parse {
    BufferedReader bufferedReader;
    String fileLine;
    ArrayList<Object> rowValues;
    HashMap <Integer, ArrayList<Object>> dataSet = new HashMap<>();
    int rowNumber = 0;

    Parse(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    // Complete
    void textFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            rowValues = new ArrayList<>(Arrays.asList(values));
            if (rowNumber > 0) {
                dataSet.put(rowNumber, rowValues);
            }
            rowNumber++;
        }
        System.out.println(dataSet);
    }

    void jsonFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
    }

    void parquetFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
    }

    // Complete
    void csvFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            Object [] stringValues = fileLine.split(",");
            rowValues = new ArrayList<>(Arrays.asList(stringValues));
            if (rowNumber > 0) {
                dataSet.put(rowNumber,rowValues);
            }
            rowNumber++;
        }
        System.out.println(dataSet);
    }

    void htmlFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
    }

    void xmlFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
    }

}
