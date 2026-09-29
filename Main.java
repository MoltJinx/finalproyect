import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Comparar ordenamientos por cantidad de elementos");
        System.out.println("2. Probar ordenamientos por tiempo limite");
        System.out.print("Opcion: ");
        int option = readOption(scanner);

        if (option == 1) {
            normalMode(scanner);
        } else {
            timeLimitMode(scanner);
        }

        scanner.close();
    }

    static void normalMode(Scanner scanner) {
        System.out.print("Cuantos elementos vas a ordenar? ");
        int amount = readPositiveInt(scanner);

        int[] originalNumbers = generateNumbers(amount);
        ArrayList<Integer> originalList = convertToArrayList(originalNumbers);

        int[] bubbleArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] selectionArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] insertionArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] quickArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] shellArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        ArrayList<Integer> bubbleList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> selectionList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> insertionList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> quickList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> shellList = new ArrayList<Integer>(originalList);

        ConcurrentHashMap<String, SortResult> results =
                new ConcurrentHashMap<String, SortResult>();

        Thread bubbleArrayThread = new BubbleSort.ArrayThread(bubbleArray, results);
        Thread bubbleListThread = new BubbleSort.ArrayListThread(bubbleList, results);
        Thread selectionArrayThread = new SelectionSort.ArrayThread(selectionArray, results);
        Thread selectionListThread = new SelectionSort.ArrayListThread(selectionList, results);
        Thread insertionArrayThread = new InsertionSortArray.SortThread(insertionArray, results);
        Thread insertionListThread = new InsertionSortArrayL.SortThread(insertionList, results);
        Thread quickArrayThread = new quickSortArr.SortThread(quickArray, results);
        Thread quickListThread = new quickSortArrL.SortThread(quickList, results);
        Thread shellArrayThread = new shellSortArr.SortThread(shellArray, results);
        Thread shellListThread = new shellSortArrL.SortThread(shellList, results);

        bubbleArrayThread.start();
        bubbleListThread.start();
        selectionArrayThread.start();
        selectionListThread.start();
        insertionArrayThread.start();
        insertionListThread.start();
        quickArrayThread.start();
        quickListThread.start();
        shellArrayThread.start();
        shellListThread.start();

        try {
            bubbleArrayThread.join();
            bubbleListThread.join();
            selectionArrayThread.join();
            selectionListThread.join();
            insertionArrayThread.join();
            insertionListThread.join();
            quickArrayThread.join();
            quickListThread.join();
            shellArrayThread.join();
            shellListThread.join();
        } catch (InterruptedException e) {
            System.out.println("Se interrumpio la ejecucion de los hilos.");
            Thread.currentThread().interrupt();
            return;
        }

        mainResultados.mostrarResultados(amount, results);
    }

    static void timeLimitMode(Scanner scanner) {
        System.out.print("Cuantos elementos tendra cada coleccion? ");
        int amount = readPositiveInt(scanner);

        System.out.print("Cuantos segundos durara la prueba? ");
        int seconds = readPositiveInt(scanner);

        int[] originalNumbers = generateNumbers(amount);
        ConcurrentHashMap<String, TimeLimitResult> results =
                new ConcurrentHashMap<String, TimeLimitResult>();

        Thread[] threads = {
                new TimeLimitThread("Bubble Sort", "Arreglo", originalNumbers, seconds, results),
                new TimeLimitThread("Bubble Sort", "ArrayList", originalNumbers, seconds, results),
                new TimeLimitThread("Selection Sort", "Arreglo", originalNumbers, seconds, results),
                new TimeLimitThread("Selection Sort", "ArrayList", originalNumbers, seconds, results),
                new TimeLimitThread("Insertion Sort", "Arreglo", originalNumbers, seconds, results),
                new TimeLimitThread("Insertion Sort", "ArrayList", originalNumbers, seconds, results),
                new TimeLimitThread("Quick Sort", "Arreglo", originalNumbers, seconds, results),
                new TimeLimitThread("Quick Sort", "ArrayList", originalNumbers, seconds, results),
                new TimeLimitThread("Shell Sort", "Arreglo", originalNumbers, seconds, results),
                new TimeLimitThread("Shell Sort", "ArrayList", originalNumbers, seconds, results)
        };

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("Se interrumpio la ejecucion de los hilos.");
            Thread.currentThread().interrupt();
            return;
        }

        mainResultados.mostrarResultadosTiempo(amount, seconds, results);
    }

    static int readOption(Scanner scanner) {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                if (option == 1 || option == 2) {
                    return option;
                }

                System.out.print("Elige la opcion 1 o 2: ");
            } catch (NumberFormatException e) {
                System.out.print("Elige la opcion 1 o 2: ");
            }
        }
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

}
