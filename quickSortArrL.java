import java.util.ArrayList;
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
}
