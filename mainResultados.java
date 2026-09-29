import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class mainResultados{

    public static void mostrarResultados(int elem,
            ConcurrentHashMap<String, SortResult> results){
        ArrayList<SortResult> orderedResults = new ArrayList<SortResult>(results.values());
        Collections.sort(orderedResults);

        System.out.println("\nRESULTADOS DE ORDENAMIENTO");
        System.out.println("Elementos: " + elem);
        System.out.printf("%-5s %-18s %-12s %-15s %-10s%n",
                "Pos.", "Algoritmo", "Estructura", "Tiempo (ms)", "Ordeno");

        for (int i = 0; i < orderedResults.size(); i++) {
            SortResult result = orderedResults.get(i);
            String sortedText = result.isSorted() ? "Si" : "No";

            System.out.printf("%-5d %-18s %-12s %-15.4f %-10s%n",
                    i + 1,
                    result.getAlgorithm(),
                    result.getStructure(),
                    result.getTime(),
                    sortedText);
        }

        if (!orderedResults.isEmpty()) {
            SortResult fastest = orderedResults.get(0);
            System.out.println("\nImplementacion con menor tiempo registrado: "
                    + fastest.getAlgorithm() + " (" + fastest.getStructure() + ")");
        }
    }

    public static boolean isSorted(int[] arreglo){ //metodo para ver si el arreglo esta ordenado
        for (int i = 0; i < arreglo.length - 1; i++){//recorrer el arreglo
            if (arreglo[i] > arreglo[i + 1]){ //si la posicion 1 es mayorr a la posicion 2 no esta ordenado
                return false;
            }
        } 
        return true;  // esta oredenado
    }

    public static boolean isSorted(ArrayList<Integer> lista){//metodo para ver si el arraylist esta ordenado
        for (int i = 0; i < lista.size() - 1; i++){//recorremos el arraylist
            if(lista.get(i) > lista.get(i + 1)){//si la pos1 es mayor a la pos 2 no esta ordenado
                return false;
            }
        } return true; // esta ordenado
    }
}
