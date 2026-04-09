import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    // Completed
    public void htmlFile (String filePath) throws IOException {
        org.jsoup.nodes.Document document = Jsoup.parse(new File(filePath));
        ArrayList<String> columnNameList = new ArrayList<>();
        Elements elements = document.select("tr");
        row = new HashMap<>();

        for (org.jsoup.nodes.Element columnName: elements.select("th")) {
            String colName = columnName.text()
                    .replace(" ", "_")
                    .toLowerCase().strip();
            columnNameList.add(colName);
        }

        for (int i = 0; i < elements.select("td").size(); i++) {
            String rowValue = elements.select("td").get(i).text();
            String columnName = columnNameList.get(i%columnNameList.size());
            row.put(columnName, rowValue);
            if (i%columnNameList.size() == 6) {
                dataSet.add(row);
                row = new HashMap<>();
            }
        }
        System.out.println(dataSet);
    }

    // Completed
    public void xmlFile (String Filepath) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(new File(Filepath));
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("order");
        for(int i = 0; i < nodeList.getLength(); i++) {
            row = new HashMap<>();
            Element element = (Element) nodeList.item(i);
            Element customerElement = (Element) element.getElementsByTagName("customer").item(0);

            row.put(customerElement.getNodeName() + "_id", customerElement.getAttribute("id"));
            row.put(customerElement.getNodeName() + "_" + customerElement.getElementsByTagName("name").item(0).getNodeName(), customerElement.getElementsByTagName("name").item(0).getTextContent());
            row.put(customerElement.getNodeName() + "_" + customerElement.getElementsByTagName("email").item(0).getNodeName(), customerElement.getElementsByTagName("email").item(0).getTextContent());
            row.put(customerElement.getNodeName() + "_" + customerElement.getElementsByTagName("city").item(0).getNodeName(), customerElement.getElementsByTagName("city").item(0).getTextContent());
            row.put(customerElement.getNodeName() + "_" + customerElement.getElementsByTagName("state").item(0).getNodeName(), customerElement.getElementsByTagName("state").item(0).getTextContent());
            row.put(customerElement.getNodeName() + "_" + customerElement.getElementsByTagName("isMember").item(0).getNodeName(), customerElement.getElementsByTagName("isMember").item(0).getTextContent());

            row.put(element.getNodeName() + "_id", element.getAttribute("id"));
            row.put(element.getElementsByTagName("orderDate").item(0).getNodeName(), element.getElementsByTagName("orderDate").item(0).getTextContent());
            row.put(element.getElementsByTagName("product").item(0).getNodeName(), element.getElementsByTagName("product").item(0).getTextContent());
            row.put(element.getElementsByTagName("category").item(0).getNodeName(), element.getElementsByTagName("category").item(0).getTextContent());
            row.put(element.getElementsByTagName("quantity").item(0).getNodeName(), element.getElementsByTagName("quantity").item(0).getTextContent());
            row.put(element.getElementsByTagName("unitPrice").item(0).getNodeName(), element.getElementsByTagName("unitPrice").item(0).getTextContent());
            row.put(element.getElementsByTagName("discountPct").item(0).getNodeName(), element.getElementsByTagName("discountPct").item(0).getTextContent());
            row.put(element.getElementsByTagName("notes").item(0).getNodeName(), element.getElementsByTagName("notes").item(0).getTextContent());

            dataSet.add(row);
        }
        System.out.println(dataSet);
    }
}
