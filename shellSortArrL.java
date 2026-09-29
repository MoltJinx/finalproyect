import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class shellSortArrL {

 public static void ShellSort(ArrayList<Integer> arr) {
        int n = arr.size();

        for (int gap = n / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < n; i++) {
                
                int temp = arr.get(i); 
                int j = i;

                while (j >= gap && arr.get(j - gap) > temp) {
                    arr.set(j, arr.get(j-gap));
                    j -= gap;
                }

                arr.set(j, temp);
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static class SortThread extends Thread {
        private ArrayList<Integer> arr;
        private ConcurrentHashMap<String, SortResult> results;

        public SortThread(ArrayList<Integer> arr,
                ConcurrentHashMap<String, SortResult> results) {
            this.arr = arr;
            this.results = results;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            ShellSort(arr);
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1000000.0;
            SortResult result = new SortResult(
                    "Shell Sort", "ArrayList", milliseconds, mainResultados.isSorted(arr)
            );
            results.put("Shell Sort - ArrayList", result);
        }
    }
}
