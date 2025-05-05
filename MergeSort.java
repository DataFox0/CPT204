import java.util.List;
import java.util.ArrayList;

public class MergeSort implements SortingAlgorithm {
    @Override
    public void sort(List<String> list) {
        List<String> temp = new ArrayList<>(list); // 辅助数组，避免频繁创建子列表
        mergeSort(list, temp, 0, list.size() - 1);
    }

    private void mergeSort(List<String> list, List<String> temp, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(list, temp, low, mid);      // 递归排序左半部分
            mergeSort(list, temp, mid + 1, high);  // 递归排序右半部分
            merge(list, temp, low, mid, high);     // 合并左右部分
        }
    }

    private void merge(List<String> list, List<String> temp, int low, int mid, int high) {
        // 1. 复制到辅助数组
        for (int i = low; i <= high; i++) {
            temp.set(i, list.get(i));
        }

        // 2. 双指针归并
        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high) {
            if (temp.get(i).compareTo(temp.get(j)) <= 0) {
                list.set(k++, temp.get(i++));
            } else {
                list.set(k++, temp.get(j++));
            }
        }

        // 3. 处理剩余元素（左半部分可能还有剩余）
        while (i <= mid) {
            list.set(k++, temp.get(i++));
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}