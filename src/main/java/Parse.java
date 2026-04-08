import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parse {

    BufferedReader bufferedReader;
    String fileLine;
    ArrayList<Object> rowValues;
    ArrayList<HashMap<String, Object>> dataSet = new ArrayList<>();
    HashMap<String, Object> row;

    public Parse(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    // Complete
    public void textFile () throws IOException {
        ArrayList<String[]> allRows = new ArrayList<>();
        while ((fileLine = bufferedReader.readLine()) != null) {
            String[] values = fileLine.split("\\|");
            allRows.add(values);
        }
        for (int i = 0; i < allRows.size(); i++) {
            row = new HashMap<>();
            if (i > 0) {
                for (int j = 0; j < Arrays.asList(allRows.getFirst()).size(); j++) {
                    row.put(Arrays.asList(allRows.getFirst()).get(j), Arrays.asList(allRows.get(i)).get(j));
                }
                dataSet.add(row);
            }
        }
        System.out.println(dataSet);
    }

    // Json Reader - Completed
    public void jsonFile () throws IOException {
        Gson gson = new Gson();
        JsonObject sampleOrders = gson.fromJson(bufferedReader, JsonObject.class);
        JsonArray orders = sampleOrders.getAsJsonArray("orders");
        ArrayList<HashMap<String, Object>> dataset = new ArrayList<>();
        for (JsonElement order: orders) {
            HashMap<String, Object> rows = new HashMap<>();
            flatten(order, rows);
            dataset.add(rows);
        }
        System.out.println(dataset);
    }
    // Flattener
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
        ArrayList<String[]> allRows = new ArrayList<>();
        while ((fileLine = bufferedReader.readLine()) != null) {
            String[] values = fileLine.split(",");
            allRows.add(values);
        }
        for (int i = 0; i < allRows.size(); i++) {
            row = new HashMap<>();
            if (i > 0) {
                for (int j = 0; j < Arrays.asList(allRows.getFirst()).size(); j++) {
                    row.put(Arrays.asList(allRows.getFirst()).get(j), Arrays.asList(allRows.get(i)).get(j));
                }
                dataSet.add(row);
            }
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
    public void xmlFile (String Filepath) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(new File(Filepath));
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("orders");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println(element.getTextContent());
            }
        }

    }

}
