import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Merge Sort sobre ArrayList<Integer>.
 * Complejidad: mejor, promedio y peor O(n log n). Espacio: O(n). Estable.
 * Trabaja con índices sobre la misma lista y un ArrayList auxiliar (sin subList).
 */
public class MergeSortArrayL {

    // Ordena la lista recibida en el lugar (ascendente).
    public static void ordenar(ArrayList<Integer> lista) {
        int n = lista.size();
        if (n < 2) return;
        ArrayList<Integer> aux = new ArrayList<>(lista);
        dividir(lista, aux, 0, n - 1);
    }

    private static void dividir(ArrayList<Integer> lista, ArrayList<Integer> aux, int izq, int der) {
        if (izq >= der) return;
        int medio = izq + (der - izq) / 2;
        dividir(lista, aux, izq, medio);
        dividir(lista, aux, medio + 1, der);
        mezclar(lista, aux, izq, medio, der);
    }

    private static void mezclar(ArrayList<Integer> lista, ArrayList<Integer> aux,
                                int izq, int medio, int der) {
        for (int k = izq; k <= der; k++) {
            aux.set(k, lista.get(k));
        }
        int i = izq;
        int j = medio + 1;
        int k = izq;

        while (i <= medio && j <= der) {
            if (aux.get(i) <= aux.get(j)) {
                lista.set(k++, aux.get(i++));
            } else {
                lista.set(k++, aux.get(j++));
            }
        }
        while (i <= medio) {
            lista.set(k++, aux.get(i++));
        }
        while (j <= der) {
            lista.set(k++, aux.get(j++));
        }
    }

    public static class SortThread extends Thread {
        private ArrayList<Integer> lista;
        private ConcurrentHashMap<String, SortResult> results;

        public SortThread(ArrayList<Integer> lista,
                ConcurrentHashMap<String, SortResult> results) {
            this.lista = lista;
            this.results = results;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            ordenar(lista);
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1000000.0;
            SortResult result = new SortResult(
                    "Merge Sort", "ArrayList", milliseconds, mainResultados.isSorted(lista)
            );
            results.put("Merge Sort - ArrayList", result);
        }
    }
}
