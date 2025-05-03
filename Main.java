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

        List<String> selectedAttractions = new ArrayList<>();
        for (String name : attractionNames) {
            selectedAttractions.add(name.trim());
        }

        // To Calculated Path
        List<String> fullPath = calculateRoute(startCity, endCity, selectedAttractions, roadNetwork, attractions);

        // Output result
        System.out.println("The shortest route: ");
        for (String city : fullPath) {
            System.out.print(city + " -> ");
        }
        System.out.println("Total distance: " + calculateTotalDistance(fullPath, roadNetwork) + " miles");
    }

    // Calculate the path (including passing through scenic spots)
    public static List<String> calculateRoute(String startingCity, String endingCity, List<String> attractions, Graph graph, Map<String, String> attractionLocations) {
        List<String> fullPath = new ArrayList<>();
        String currentCity = startingCity;

        // Calculate the shortest path from the starting point to each attraction
        for (String attraction : attractions) {
            String attractionLocation = attractionLocations.get(attraction);  // Obtain the location of tourist attractions
            List<String> path = graph.dijkstra(currentCity, attractionLocation);
            fullPath.addAll(path.subList(1, path.size())); // Avoid duplicate cities
            currentCity = attractionLocation;
        }

        // Finally, from the last attraction to the finish line
        List<String> lastPath = graph.dijkstra(currentCity, endingCity);
        fullPath.addAll(lastPath.subList(1, lastPath.size()));

        return fullPath;
    }

    // Calculate the total distance of the path
    public static int calculateTotalDistance(List<String> path, Graph graph) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String city1 = path.get(i);
            String city2 = path.get(i + 1);
            totalDistance += graph.adjList.get(city1).get(city2);
        }
        return totalDistance;
    }
}
