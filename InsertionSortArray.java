import java.util.concurrent.ConcurrentHashMap;

public class InsertionSortArray {
  public static void ordenar(int[] arr) {
    for(int i = 1; i < arr.length; i++) {
      int clave = arr[i];
      int j = i -1;

      while( j >= 0 && arr[j] > clave){
        arr[j + 1] = arr[ j ];
        j--;
      }
      arr[j + 1] = clave;
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
              "Insertion Sort", "Arreglo", milliseconds, mainResultados.isSorted(arr)
      );
      results.put("Insertion Sort - Arreglo", result);
    }
  }
}
