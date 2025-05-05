import java.util.List;

public class InsertionSort implements SortingAlgorithm {
    @Override
    public void sort(List<String> list) {
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

    @Override
    public String getName() {
        return "Insertion Sort";
    }
}