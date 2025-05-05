import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputData input = InputHandler.readInput();

        //System.out.println("start: " + startCity);
        //System.out.println("Destination: " + endCity);
        //System.out.println("Attractions: " + Arrays.toString(attractionNames));

        if (input.selectedAttractions.get(0).equals("Null")) {
                List<String> caseNullPath = input.roadNetwork.dijkstra(input.startCity, input.endCity);
                RoutePrinter.printRoute("Shortest route:", caseNullPath, input.roadNetwork);
                return;
        }

        calcOrderedRoute caseOrdered = new calcOrderedRoute();
        List<String> caseOrderedPath = caseOrdered.calculateRoute(
                input.startCity, input.endCity, input.selectedAttractions, input.roadNetwork, input.attractions
        );
        RoutePrinter.printRoute("Shortest route (Ordered Calculation):", caseOrderedPath, input.roadNetwork);

        calcBrute caseBrute = new calcBrute();
        List<String> caseBrutePath = caseBrute.calculateRoute(
                input.startCity, input.endCity, input.selectedAttractions, input.roadNetwork, input.attractions
        );
        RoutePrinter.printRoute("Shortest route (Brute Force):", caseBrutePath, input.roadNetwork);

        calcBitmaskDP caseDP = new calcBitmaskDP();
        List<String> caseDPPath = caseDP.calculateRoute(
                input.startCity, input.endCity, input.selectedAttractions, input.roadNetwork, input.attractions
        );
        RoutePrinter.printRoute("Shortest route (Bitmask DP):", caseDPPath, input.roadNetwork);

        calcMST caseMST = new calcMST();
        List<String> caseMSTPath = caseMST.calculateRoute(
                input.startCity, input.endCity, input.selectedAttractions, input.roadNetwork, input.attractions
        );
        RoutePrinter.printRoute("Shortest route (MST):", caseMSTPath, input.roadNetwork);
    }
}

