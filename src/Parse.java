import java.io.BufferedReader;
import java.io.IOException;

public class Parse {
    BufferedReader bufferedReader;
    String fileLine;

    Parse(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    void textFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
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

    void csvFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split(",");
            for (String value: values) {
                System.out.println(value);
            }
        }
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
