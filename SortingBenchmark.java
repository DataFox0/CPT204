import java.util.List;

public class SortingBenchmark { public static void main(String[] args) {
    DataLoader dataLoader = new DataLoader();
    PerformanceTester tester = new PerformanceTester();

    SortingAlgorithm[] algorithms = {
            new InsertionSort(),
            new QuickSort(),
            new MergeSort()
    };

    String[] datasets = {
            "1000places_sorted.csv",
            "1000places_random.csv",
            "10000places_sorted.csv",
            "10000places_random.csv"
    };

    for (String dataset : datasets) {
        System.out.println("Dataset: " + dataset);
        List<String> data = dataLoader.readDataFromCSV(dataset);

        for (SortingAlgorithm algorithm : algorithms) {
            long medianTime = tester.testPerformance(algorithm, data);

            System.out.printf("%s Sorting time: %,d ns (%.3f ms)\n",
                    algorithm.getName(),
                    medianTime,
                    medianTime / 1_000_000.0
            );
        }
        System.out.println();
    }
}
}