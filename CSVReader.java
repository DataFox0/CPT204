/**
 * File Purpose: Loads tourism data from CSV files
 *
 * Static Methods:
 * - readAttractions(): Parses attraction locations
 * - readRoads(): Builds city road network
 *
 * Note: Throws IOException for file operations
 */
import java.io.*;
import java.util.*;

public class CSVReader {

    public static Map<String, Attraction> readAttractions(String filename) {
        Map<String, Attraction> attractions = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    String name = values[0].trim();
                    String location = values[1].trim();
                    attractions.put(name, new Attraction(name, location));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attractions;
    }


    // Read the roads.csv file and return the graph structure (roads and distances between cities)
    public static Graph readRoads(String filename) {
        Graph graph = new Graph();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();
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
