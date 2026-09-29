import java.util.concurrent.ConcurrentHashMap;

/**
 * Merge Sort sobre arreglo (int[]).
 * Complejidad: mejor, promedio y peor O(n log n). Espacio: O(n). Estable.
 */
public class MergeSortArray {

    // Ordena el arreglo recibido (ascendente). Usa un arreglo auxiliar único.
    public static void ordenar(int[] arr) {
        if (arr.length < 2) return;
        int[] aux = new int[arr.length];
        dividir(arr, aux, 0, arr.length - 1);
    }

    private static void dividir(int[] arr, int[] aux, int izq, int der) {
        if (izq >= der) return;
        int medio = izq + (der - izq) / 2;
        dividir(arr, aux, izq, medio);
        dividir(arr, aux, medio + 1, der);
        mezclar(arr, aux, izq, medio, der);
    }

    private static void mezclar(int[] arr, int[] aux, int izq, int medio, int der) {
        for (int k = izq; k <= der; k++) {
            aux[k] = arr[k];
        }
        int i = izq;
        int j = medio + 1;
        int k = izq;

        while (i <= medio && j <= der) {
            if (aux[i] <= aux[j]) {
                arr[k++] = aux[i++];
            } else {
                arr[k++] = aux[j++];
            }
        }
        while (i <= medio) {
            arr[k++] = aux[i++];
        }
        while (j <= der) {
            arr[k++] = aux[j++];
        }
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
            ordenar(arr);
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1000000.0;
            SortResult result = new SortResult(
                    "Merge Sort", "Arreglo", milliseconds, mainResultados.isSorted(arr)
            );
            results.put("Merge Sort - Arreglo", result);
        }
    }
}
