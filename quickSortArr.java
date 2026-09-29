
public class quickSortArr {

    public static int particion(int[] array, int inicio, int fin){
        int pivote = array[fin];
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {

        if (array[j] <= pivote) {
            i++;

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    int temp = array[i + 1];
    array[i + 1] = array[fin];
    array[fin] = temp;

    return i + 1;
    }


    public static void QuickSort(int[] array, int inicio, int fin){
        if (inicio < fin){
            int pivot = particion(array, inicio, fin);
            QuickSort(array, inicio, pivot - 1); //ordenar izquierda de pivote
            QuickSort(array, pivot + 1, fin);// ordenar derecha de pivote
        }
    }
}
