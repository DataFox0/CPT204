import java.util.List;
import java.util.Random;
import java.util.Collections;


public class QuickSort implements SortingAlgorithm {

    private static final Random RANDOM = new Random();

    @Override
    public void sort(List<String> list) {
        quickSort(list, 0, list.size() - 1);
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }

    // The main logic of recursive quick sort
    private void quickSort(List<String> list, int low, int high) {
        if (low < high) {

            int randomIndex = low + RANDOM.nextInt(high - low + 1);
            Collections.swap(list, randomIndex, high);

            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    // Partition operation
    private int partition(List<String> list, int low, int high) {
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
}
