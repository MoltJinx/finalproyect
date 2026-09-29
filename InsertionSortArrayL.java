import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class InsertionSortArrayL {
  public static void ordenar (ArrayList<Integer> lista){
    for(int i = 1; i < lista.size(); i++){
      int clave = lista.get(i);
      int j = i -1;
      while (j >= 0 && lista.get(j) > clave){
        lista.set(j + 1, lista.get(j));
        j--;
      }
      lista.set(j + 1, clave);
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
              "Insertion Sort", "ArrayList", milliseconds, mainResultados.isSorted(lista)
      );
      results.put("Insertion Sort - ArrayList", result);
    }
  }
}
