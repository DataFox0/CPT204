import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerformanceTester {


    public long testPerformance(SortingAlgorithm algorithm, List<String> originalData) {

        warmUp(algorithm, originalData);


        long[] executionTimes = executeTestRuns(algorithm, originalData);

        return getMedianTime(executionTimes);
    }


    private void warmUp(SortingAlgorithm algorithm, List<String> data) {
        for (int i = 0; i < 20; i++) {
            List<String> copy = createDataCopy(data);
            algorithm.sort(copy);
        }
    }


    private long[] executeTestRuns(SortingAlgorithm algorithm, List<String> originalData) {
        long[] times = new long[5];
        for (int i = 0; i < 5; i++) {
            List<String> testData = createDataCopy(originalData);

            long startTime = System.nanoTime();
            algorithm.sort(testData);
            long endTime = System.nanoTime();

            times[i] = endTime - startTime;

        }
        return times;
    }


    private List<String> createDataCopy(List<String> data) {
        return new ArrayList<>(data);
    }


    private long getMedianTime(long[] times) {
        Arrays.sort(times);
        return times[times.length / 2];
    }
}
