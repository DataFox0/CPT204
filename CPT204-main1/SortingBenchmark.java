/**
* File Purpose: Sorting Algorithm Performance Comparator
*
* Program Flow:
* 1. Loads location data from CSV files
* 2. Benchmarks Insertion, Quick and Merge sorts
* 3. Measures execution time (with warm-up runs)
*
* Output Format:
* - Sorting times in ns/ms for each algorithm
* - Performance comparison across datasets
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortingBenchmark {

    //insertion sort
    public static void insertionSort(List<String> list) {
        for (int i = 1; i < list.size(); i++) {
            String key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    //quick sort
    public static void quickSort(List<String> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<String> list, int low, int high) {
        if (low < high) {

            int randomIndex = low + new Random().nextInt(high - low + 1);
            Collections.swap(list, randomIndex, high);

            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int partition(List<String> list, int low, int high) {
        String pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }


    //merge sort
    public static void mergeSort(List<String> list) {
        if (list.size() > 1) {
            int mid = list.size() / 2;
            List<String> left = new ArrayList<>(list.subList(0, mid));
            List<String> right = new ArrayList<>(list.subList(mid, list.size()));

            mergeSort(left);
            mergeSort(right);

            merge(list, left, right);
        }
    }

    private static void merge(List<String> result, List<String> left, List<String> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }

    public static List<String> readDataFromCSV(String filename) {
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static long testSortingAlgorithm(List<String> data, String algorithm) {
        //warm-up
        for (int i = 0; i < 20; i++) {
            List<String> warmupCopy = new ArrayList<>(data);
            switch (algorithm) {
                case "Insertion": insertionSort(warmupCopy); break;
                case "Quick": quickSort(warmupCopy); break;
                case "Merge": mergeSort(warmupCopy); break;
            }
        }

        long[] times = new long[5];
        for (int i = 0; i < 5; i++) {
            List<String> copy = new ArrayList<>(data);
            long start = System.nanoTime();
            switch (algorithm) {
                case "Insertion": insertionSort(copy); break;
                case "Quick": quickSort(copy); break;
                case "Merge": mergeSort(copy); break;
            }
            times[i] = System.nanoTime() - start;

        }
        Arrays.sort(times);
        return times[2];
    }


    public static void main(String[] args) {
        String[] datasets = {
                "CW3_Data_Files/1000places_sorted.csv",
                "CW3_Data_Files/1000places_random.csv",
                "CW3_Data_Files/10000places_sorted.csv",
                "CW3_Data_Files/10000places_random.csv"
        };

        String[] algorithms = {"Insertion", "Quick", "Merge"};

        for (String dataset : datasets) {
            System.out.println("dataset: " + dataset);
            List<String> data = readDataFromCSV(dataset);

            for (String algorithm : algorithms) {
                long medianTime = testSortingAlgorithm(data, algorithm);

                System.out.printf("%s Sorting time: %,d ns (%.3f ms)\n",
                        algorithm,
                        medianTime,
                        medianTime / 1_000_000.0
                );
            }
            System.out.println();
        }

    }
}