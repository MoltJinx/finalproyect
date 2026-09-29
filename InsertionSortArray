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
}
