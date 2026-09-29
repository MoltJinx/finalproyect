import java.util.ArrayList;
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
}
