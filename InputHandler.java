import java.util.*;

public class InputHandler {
    public static InputData readInput() {
        // 读取景点和道路数据
        Map<String, String> attractions = CSVReader.readAttractions("CW3_Data_Files/attractions.csv");
        Graph roadNetwork = CSVReader.readRoads("CW3_Data_Files/roads.csv");

        // 打印景点信息
        System.out.println("Attractions:");
        for (Map.Entry<String, String> entry : attractions.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 读取用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the starting city: ");
        String startCity = scanner.nextLine();
        System.out.print("Enter the destination city: ");
        String endCity = scanner.nextLine();
        System.out.print("Enter the attractions (comma-separated): ");
        String[] attractionNames = scanner.nextLine().split(",");
        scanner.close();

        List<String> selectedAttractions = new ArrayList<>();
        for (String name : attractionNames) {
            selectedAttractions.add(name.trim());
        }

        return new InputData(startCity, endCity, selectedAttractions, attractions, roadNetwork);
    }
}
