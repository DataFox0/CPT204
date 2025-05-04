import java.util.*;

public class InputData {
    public String startCity;
    public String endCity;
    public List<String> selectedAttractions;
    public Map<String, String> attractions;
    public Graph roadNetwork;

    public InputData(String startCity, String endCity, List<String> selectedAttractions,
                     Map<String, String> attractions, Graph roadNetwork) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.selectedAttractions = selectedAttractions;
        this.attractions = attractions;
        this.roadNetwork = roadNetwork;
    }
}
