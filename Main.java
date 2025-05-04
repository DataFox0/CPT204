import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Read scenic spot and road data
        Map<String, String> attractions = CSVReader.readAttractions("CW3_Data_Files/attractions.csv");
        Graph roadNetwork = CSVReader.readRoads("CW3_Data_Files/roads.csv");

        // Print attraction information
        System.out.println("Attractions:");
        for (Map.Entry<String, String> entry : attractions.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Enter the starting point, ending point, and scenic spot
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

        // To Calculated Ordered Path
        calcOrderedRoute caseOrdered = new calcOrderedRoute();
        List<String> caseOrderedPath = caseOrdered.calculateRoute(startCity, endCity, selectedAttractions, roadNetwork, attractions);
        // List<String> fullPath = calculateRoute(startCity, endCity, selectedAttractions, roadNetwork, attractions);

        // Output result
        System.out.println("The shortest route: ");
        for (String city : caseOrderedPath) {
            System.out.print(city + " -> ");
        }
        System.out.println("Total distance: " + roadNetwork.calculateTotalDistance(caseOrderedPath, roadNetwork) + " miles");
        

        // To Calculated Ordered Path
        calcBitmaskDP caseDP = new calcBitmaskDP();
        List<String> caseDPPath = caseDP.calculateRoute(startCity, endCity, selectedAttractions, roadNetwork, attractions);
        // List<String> fullPath = calculateRoute(startCity, endCity, selectedAttractions, roadNetwork, attractions);

        // Output result
        System.out.println("The shortest route: ");
        for (String city : caseDPPath) {
            System.out.print(city + " -> ");
        }
        System.out.println("Total distance: " + roadNetwork.calculateTotalDistance(caseDPPath, roadNetwork) + " miles");
    }
}
