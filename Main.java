import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputData input = InputHandler.readInput();

        calcOrderedRoute caseOrdered = new calcOrderedRoute();
        List<String> caseOrderedPath = caseOrdered.calculateRoute(
                input.startCity, input.endCity, input.selectedAttractions, input.roadNetwork, input.attractions
        );
        RoutePrinter.printRoute("Shortest route (Ordered Calculation):", caseOrderedPath, input.roadNetwork);

        calcBitmaskDP caseDP = new calcBitmaskDP();
        List<String> caseDPPath = caseDP.calculateRoute(
                input.startCity, input.endCity, input.selectedAttractions, input.roadNetwork, input.attractions
        );
        RoutePrinter.printRoute("Shortest route (Bitmask DP):", caseDPPath, input.roadNetwork);

        calcBrute caseBrute = new calcBrute();
        List<String> caseBrutePath = caseBrute.calculateRoute(
                input.startCity, input.endCity, input.selectedAttractions, input.roadNetwork, input.attractions
        );
        RoutePrinter.printRoute("Shortest route (Brute Force):", caseBrutePath, input.roadNetwork);
    }
}