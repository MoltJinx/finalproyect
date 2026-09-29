import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cuantos elementos vas a ordenar? ");
        int amount = readPositiveInt(scanner);

        int[] originalNumbers = generateNumbers(amount);
        ArrayList<Integer> originalList = convertToArrayList(originalNumbers);

        int[] bubbleArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] selectionArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        ArrayList<Integer> bubbleList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> selectionList = new ArrayList<Integer>(originalList);

        ConcurrentHashMap<String, SortResult> results =
                new ConcurrentHashMap<String, SortResult>();

        Thread bubbleArrayThread = new BubbleSort.ArrayThread(bubbleArray, results);
        Thread bubbleListThread = new BubbleSort.ArrayListThread(bubbleList, results);
        Thread selectionArrayThread = new SelectionSort.ArrayThread(selectionArray, results);
        Thread selectionListThread = new SelectionSort.ArrayListThread(selectionList, results);

        bubbleArrayThread.start();
        bubbleListThread.start();
        selectionArrayThread.start();
        selectionListThread.start();

        try {
            bubbleArrayThread.join();
            bubbleListThread.join();
            selectionArrayThread.join();
            selectionListThread.join();
        } catch (InterruptedException e) {
            System.out.println("Se interrumpio la ejecucion de los hilos.");
            Thread.currentThread().interrupt();
            scanner.close();
            return;
        }

        showResults(amount, results);
        scanner.close();
    }

    static int readPositiveInt(Scanner scanner) {
        while (true) {
            try {
                int number = Integer.parseInt(scanner.nextLine().trim());

                if (number > 0) {
                    return number;
                }

                System.out.print("Escribe un numero entero positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Escribe un numero entero positivo: ");
            }
        }
    }

    static int[] generateNumbers(int amount) {
        Random random = new Random();
        int[] numbers = new int[amount];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100000) + 1;
        }

        return numbers;
    }

    static ArrayList<Integer> convertToArrayList(int[] numbers) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }

        return list;
    }

    static void showResults(int amount, ConcurrentHashMap<String, SortResult> results) {
        ArrayList<SortResult> orderedResults = new ArrayList<SortResult>(results.values());
        Collections.sort(orderedResults);

        System.out.println("\nRESULTADOS DE ORDENAMIENTO");
        System.out.println("Elementos: " + amount);
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
}
