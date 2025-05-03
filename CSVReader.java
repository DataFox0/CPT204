import java.io.*;
import java.util.*;

public class CSVReader {

    // More mapping of attractions and cities should be added here
    public static Map<String, String> readAttractions(String filename) {
        Map<String, String> attractions = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();  // Skip title line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    String name = values[0].trim();
                    String location = values[1].trim();
                    attractions.put(name, location);  // Store attraction names and corresponding cities
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    // Obtain the city location based on the name of the scenic spot
    public static String getAttractionLocation(String attraction, Map<String, String> attractions) {
        return attractions.get(attraction);  // Obtain the city corresponding to the name of the scenic spot
    }

    // Read the roads.csv file and return the graph structure (roads and distances between cities)
    public static Graph readRoads(String filename) {
        Graph graph = new Graph();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();  // Skip title line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    String city1 = values[0].trim();
                    String city2 = values[1].trim();
                    int distance = Integer.parseInt(values[2].trim());
                    graph.addEdge(city1, city2, distance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
