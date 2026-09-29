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

    public static void mostrarResultadosTiempo(int elem, int seconds,
            ConcurrentHashMap<String, TimeLimitResult> results){
        ArrayList<TimeLimitResult> orderedResults =
                new ArrayList<TimeLimitResult>(results.values());
        Collections.sort(orderedResults);

        System.out.println("\nRESULTADOS POR TIEMPO LIMITE");
        System.out.println("Elementos por coleccion: " + elem);
        System.out.println("Tiempo limite: " + seconds + " segundos");
        System.out.printf("%-5s %-18s %-12s %-14s %-18s %-10s%n",
                "Pos.", "Algoritmo", "Estructura", "Completadas",
                "Promedio (ms)", "Ordeno");

        for (int i = 0; i < orderedResults.size(); i++) {
            TimeLimitResult result = orderedResults.get(i);
            String sortedText = result.isSorted() ? "Si" : "No";

            System.out.printf("%-5d %-18s %-12s %-14d %-18.4f %-10s%n",
                    i + 1,
                    result.getAlgorithm(),
                    result.getStructure(),
                    result.getCompleted(),
                    result.getAverageTime(),
                    sortedText);
        }

        if (!orderedResults.isEmpty()) {
            TimeLimitResult first = orderedResults.get(0);
            System.out.println("\nImplementacion con mas colecciones completadas: "
                    + first.getAlgorithm() + " (" + first.getStructure() + ")");
        }
    }

    public static void mostrarResultadosMeta(int elem, int target, int seconds,
            ConcurrentHashMap<String, TimeLimitResult> results){
        ArrayList<TimeLimitResult> orderedResults =
                new ArrayList<TimeLimitResult>(results.values());
        Collections.sort(orderedResults);

        System.out.println("\nCOMPROBACION DE META POR TIEMPO");
        System.out.println("Elementos por coleccion: " + elem);
        System.out.println("Meta por implementacion: " + target + " colecciones");
        System.out.println("Tiempo limite: " + seconds + " segundos");
        System.out.printf("%-5s %-18s %-12s %-12s %-12s %-16s %-10s%n",
                "Pos.", "Algoritmo", "Estructura", "Meta", "Completo",
                "Promedio (ms)", "Cumplio");

        for (int i = 0; i < orderedResults.size(); i++) {
            TimeLimitResult result = orderedResults.get(i);
            boolean completedTarget = result.getCompleted() >= target && result.isSorted();
            String completedText = completedTarget ? "Si" : "No";

            System.out.printf("%-5d %-18s %-12s %-12d %-12d %-16.4f %-10s%n",
                    i + 1,
                    result.getAlgorithm(),
                    result.getStructure(),
                    target,
                    result.getCompleted(),
                    result.getAverageTime(),
                    completedText);
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
