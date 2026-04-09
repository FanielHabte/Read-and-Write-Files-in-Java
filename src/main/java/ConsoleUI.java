import java.util.ArrayList;
import java.util.HashMap;

public class ConsoleUI {

    void showAllRecords (ArrayList<HashMap<String, Object>> dataSet) {
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %n");
        System.out.printf("|                                                                                    Parsed Data Output                                                                                 | %n");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-8s | %-12s | %-15s | %-8s | %-5s | %-9s | %-15s | %-12s | %-8s | %-10s | %-10s | %-11s | %-22s |",
                "Order Id" ,"Customer Id", "Customer Name",
                "City","State", "Is Member", "Product" ,"Category",
                "Quantity", "Unit Price", "Discount %", "Order Date", "Notes");
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (HashMap<String, Object> row : dataSet) {
            System.out.printf(
                    "| %-8s | %-12s | %-15s | %-8s | %-5s | %-9s | %-15s | %-12s | %-8s | %-10s | %-10s | %-11s | %-22s |",
                    row.get("order_id"), row.get("customer_id"), row.get("customer_name"),
                    row.get("city"), row.get("state"), row.get("is_member"), row.get("product"),
                    row.get("category"), row.get("quantity"), row.get("unit_price"), row.get("discount_pct"),
                    row.get("order_date"), row.get("notes")
            );
            System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    void showFilteredRecords (ArrayList<HashMap<String, Object>> dataSet, String filterValue, String filterColumn) {
        System.out.printf("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- %n");
        System.out.printf("|                                                                                    Parsed Data Output (Filtered)                                                                      | %n");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-8s | %-12s | %-15s | %-8s | %-5s | %-9s | %-15s | %-12s | %-8s | %-10s | %-10s | %-11s | %-22s |",
                "Order Id" ,"Customer Id", "Customer Name",
                "City","State", "Is Member", "Product" ,"Category",
                "Quantity", "Unit Price", "Discount %", "Order Date", "Notes");
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (HashMap<String, Object> row : dataSet) {
            if (row.get(filterColumn).toString().equals(filterValue)) {
                System.out.printf(
                        "| %-8s | %-12s | %-15s | %-8s | %-5s | %-9s | %-15s | %-12s | %-8s | %-10s | %-10s | %-11s | %-22s |",
                        row.get("order_id"), row.get("customer_id"), row.get("customer_name"),
                        row.get("city"), row.get("state"), row.get("is_member"), row.get("product"),
                        row.get("category"), row.get("quantity"), row.get("unit_price"), row.get("discount_pct"),
                        row.get("order_date"), row.get("notes")
                );
                System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

        }
    }


}
