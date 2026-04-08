import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parse {
    BufferedReader bufferedReader;
    String fileLine;
    ArrayList<Object> rowValues;
    HashMap <Integer, ArrayList<Object>> dataSet = new HashMap<>();
    int rowNumber = 0;

    public Parse(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    // Complete
    public void textFile () throws IOException {
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

    // Completed
    public void jsonFile () throws IOException {
        Gson gson = new Gson();
        JsonObject sampleOrders = gson.fromJson(bufferedReader, JsonObject.class);
        JsonArray orders = sampleOrders.getAsJsonArray("orders");
        HashMap<Integer, HashMap<String, Object>> dataset = new HashMap<>();
        for (JsonElement order: orders) {
            HashMap<String, Object> rows = new HashMap<>();
            rowNumber++;
            flatten(order, rows);
            dataset.put(rowNumber,rows);
        }
        System.out.println(dataset);
    }

    public void flatten (JsonElement order, HashMap<String, Object> rows) {
        for (Map.Entry<String, JsonElement> entry : order.getAsJsonObject().entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (value.isJsonObject()) {
                flatten(value.getAsJsonObject(), rows);
            } else {
                rows.put(key, value.getAsString());
            }
        }
    }

    // Not started
    public void parquetFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
    }

    // Complete
    public void csvFile () throws IOException {
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

    // Not Started
    public void htmlFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
    }

    // Not Started
    public void xmlFile () throws IOException {
        while ((fileLine = bufferedReader.readLine()) != null) {
            String [] values = fileLine.split("\\|");
            for (String value: values) {
                System.out.println(value);
            }
        }
    }

}
