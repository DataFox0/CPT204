import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class calcOrderedRoute {
    public int totalDistance;
    // Calculate the path (including passing through scenic spots)
    public List<String> calculateRoute(String startingCity, String endingCity, List<String> attractions, Graph graph, Map<String, String> attractionLocations) {
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
    public int calculateTotalDistance(List<String> path, Graph graph) {
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String city1 = path.get(i);
            String city2 = path.get(i + 1);
            totalDistance += graph.adjList.get(city1).get(city2);
        }
        return totalDistance;
    }
}