import java.util.concurrent.ConcurrentHashMap;

public class shellSortArr {
    public static void ShellSort(int[] arr) {
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < n; i++) {
                
                int temp = arr[i]; 
                int j = i;

                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = temp;
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
        private int[] arr;
        private ConcurrentHashMap<String, SortResult> results;

        public SortThread(int[] arr, ConcurrentHashMap<String, SortResult> results) {
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
                    "Shell Sort", "Arreglo", milliseconds, mainResultados.isSorted(arr)
            );
            results.put("Shell Sort - Arreglo", result);
        }
    }
}
