import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class quickSortArrL {
    public static int particion(ArrayList<Integer> array, int inicio, int fin){
        int pivote = array.get(fin);
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {

        if (array.get(j) <= pivote) {
            i++;

            int temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
    }

    int temp = array.get(i + 1);
    array.set(i + 1, array.get(fin));
    array.set(fin, temp);

    return i + 1;
    }


    public static void QuickSort(ArrayList<Integer> array, int inicio, int fin){
        if (inicio < fin){
            int pivot = particion(array, inicio, fin);
            QuickSort(array, inicio, pivot - 1); //ordenar izquierda de pivote
            QuickSort(array, pivot + 1, fin);// ordenar derecha de pivote
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
