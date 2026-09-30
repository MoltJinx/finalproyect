
import java.util.concurrent.ConcurrentHashMap;

public class quickSortArr {

    public static void QuickSort(int[] array, int inicio, int fin){
        if (inicio >= fin) {
            return;
        }

        int i = inicio;
        int j = fin;
        int pivote = array[inicio + (fin - inicio) / 2];

        while (i <= j) {
            while (array[i] < pivote) {
                i++;
            }

            while (array[j] > pivote) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (inicio < j) {
            QuickSort(array, inicio, j);
        }

        if (i < fin) {
            QuickSort(array, i, fin);
        }
    }

    public static class SortThread extends Thread {
        private int[] array;
        private ConcurrentHashMap<String, SortResult> results;

        public SortThread(int[] array, ConcurrentHashMap<String, SortResult> results) {
            this.array = array;
            this.results = results;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            QuickSort(array, 0, array.length - 1);
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1000000.0;
            SortResult result = new SortResult(
                    "Quick Sort", "Arreglo", milliseconds, mainResultados.isSorted(array)
            );
            results.put("Quick Sort - Arreglo", result);
        }
    }
}
