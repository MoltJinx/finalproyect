import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class mainResultados{
    BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    static int elem = 0;    
    public static void main(String[] args) throws IOException{

        mostrarResultados();
    }

    public static void mostrarResultados(){
        System.out.println("RESULTADOS DE ORDENAMIENTO:");
        System.out.println("Elementos ordenados:" + elem);
        System.out.println("-------------");
        System.out.printf("%-5s %-15s %-15s %-15s %-10s%n",
                  "Pos.", "Algoritmo", "Estructura", "Tiempo (ms)", "¿Ordenó?");

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
