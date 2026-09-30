import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class quickSortArrL {
    public static void QuickSort(ArrayList<Integer> array, int inicio, int fin){
        if (inicio >= fin) {
            return;
        }

        int i = inicio;
        int j = fin;
        int pivote = array.get(inicio + (fin - inicio) / 2);

        while (i <= j) {
            while (array.get(i) < pivote) {
                i++;
            }

            while (array.get(j) > pivote) {
                j--;
            }

            if (i <= j) {
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
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
        private ArrayList<Integer> array;
        private ConcurrentHashMap<String, SortResult> results;

        public SortThread(ArrayList<Integer> array,
                ConcurrentHashMap<String, SortResult> results) {
            this.array = array;
            this.results = results;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            QuickSort(array, 0, array.size() - 1);
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1000000.0;
            SortResult result = new SortResult(
                    "Quick Sort", "ArrayList", milliseconds, mainResultados.isSorted(array)
            );
            results.put("Quick Sort - ArrayList", result);
        }
    }
}
