import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Comparar ordenamientos por cantidad de elementos");
        System.out.println("2. Ver cuantas colecciones ordena en un tiempo limite");
        System.out.println("3. Comprobar una meta de colecciones en un tiempo limite");
        System.out.println("4. Ejecutar y guardar las pruebas recomendadas");
        System.out.print("Opcion: ");
        int option = readOption(scanner);

        if (option == 1) {
            normalMode(scanner);
        } else if (option == 2) {
            timeLimitMode(scanner);
        } else if (option == 3) {
            targetMode(scanner);
        } else {
            recommendedTestsMode(scanner);
        }

        scanner.close();
    }

    static void normalMode(Scanner scanner) {
        System.out.print("Cuantos elementos vas a ordenar? ");
        int amount = readPositiveInt(scanner);

        System.out.println("1. Numeros aleatorios entre 1 y 100000");
        System.out.println("2. Numeros restringidos entre 1 y 5");
        System.out.print("Tipo de datos: ");
        int generationOption = readGenerationOption(scanner);

        boolean restricted = generationOption == 2;
        int[] originalNumbers = generateNumbers(amount, restricted);

        try {
            ConcurrentHashMap<String, SortResult> results = executeComparison(originalNumbers);
            mainResultados.mostrarResultados(amount, results);
        } catch (InterruptedException e) {
            System.out.println("Se interrumpio la ejecucion de los hilos.");
            Thread.currentThread().interrupt();
        }
    }

    static ConcurrentHashMap<String, SortResult> executeComparison(int[] originalNumbers)
            throws InterruptedException {
        ArrayList<Integer> originalList = convertToArrayList(originalNumbers);

        int[] bubbleArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] selectionArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] insertionArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] quickArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] shellArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int[] mergeArray = Arrays.copyOf(originalNumbers, originalNumbers.length);
        ArrayList<Integer> bubbleList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> selectionList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> insertionList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> quickList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> shellList = new ArrayList<Integer>(originalList);
        ArrayList<Integer> mergeList = new ArrayList<Integer>(originalList);

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
        Thread mergeArrayThread = new MergeSortArray.SortThread(mergeArray, results);
        Thread mergeListThread = new MergeSortArrayL.SortThread(mergeList, results);

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
        mergeArrayThread.start();
        mergeListThread.start();

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
        mergeArrayThread.join();
        mergeListThread.join();

        return results;
    }

    static void timeLimitMode(Scanner scanner) {
        System.out.print("Cuantos elementos tendra cada intento de ordenamiento? ");
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
                new TimeLimitThread("Shell Sort", "ArrayList", originalNumbers, seconds, results),
                new TimeLimitThread("Merge Sort", "Arreglo", originalNumbers, seconds, results),
                new TimeLimitThread("Merge Sort", "ArrayList", originalNumbers, seconds, results)
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

    static void targetMode(Scanner scanner) {
        System.out.print("Cuantos elementos tendra cada coleccion? ");
        int amount = readPositiveInt(scanner);

        System.out.print("Cuantas colecciones quieres ordenar? ");
        int target = readPositiveInt(scanner);

        System.out.print("Cuantos segundos tendra para completarlas? ");
        int seconds = readPositiveInt(scanner);

        int[] originalNumbers = generateNumbers(amount);
        ConcurrentHashMap<String, TimeLimitResult> results =
                new ConcurrentHashMap<String, TimeLimitResult>();

        Thread[] threads = {
                new TimeLimitThread("Bubble Sort", "Arreglo", originalNumbers, seconds, target, results),
                new TimeLimitThread("Bubble Sort", "ArrayList", originalNumbers, seconds, target, results),
                new TimeLimitThread("Selection Sort", "Arreglo", originalNumbers, seconds, target, results),
                new TimeLimitThread("Selection Sort", "ArrayList", originalNumbers, seconds, target, results),
                new TimeLimitThread("Insertion Sort", "Arreglo", originalNumbers, seconds, target, results),
                new TimeLimitThread("Insertion Sort", "ArrayList", originalNumbers, seconds, target, results),
                new TimeLimitThread("Quick Sort", "Arreglo", originalNumbers, seconds, target, results),
                new TimeLimitThread("Quick Sort", "ArrayList", originalNumbers, seconds, target, results),
                new TimeLimitThread("Shell Sort", "Arreglo", originalNumbers, seconds, target, results),
                new TimeLimitThread("Shell Sort", "ArrayList", originalNumbers, seconds, target, results),
                new TimeLimitThread("Merge Sort", "Arreglo", originalNumbers, seconds, target, results),
                new TimeLimitThread("Merge Sort", "ArrayList", originalNumbers, seconds, target, results)
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

        mainResultados.mostrarResultadosMeta(amount, target, seconds, results);
    }

    static void recommendedTestsMode(Scanner scanner) {
        System.out.println("\nEsta prueba puede tardar bastante tiempo.");
        System.out.print("Deseas continuar? (s/n): ");
        String answer = scanner.nextLine().trim();

        if (!answer.equalsIgnoreCase("s")) {
            System.out.println("Prueba cancelada.");
            return;
        }

        int[] sizes = {100, 50000, 100000, 100000};
        boolean[] restricted = {false, false, false, true};

        try {
            PrintWriter writer = new PrintWriter("resultados_pruebas.txt");

            for (int i = 0; i < sizes.length; i++) {
                String dataType;
                if (restricted[i]) {
                    dataType = "Numeros restringidos entre 1 y 5";
                } else {
                    dataType = "Numeros aleatorios entre 1 y 100000";
                }

                System.out.println("\nEjecutando prueba de " + sizes[i] + " elementos...");
                int[] originalNumbers = generateNumbers(sizes[i], restricted[i]);

                try {
                    ConcurrentHashMap<String, SortResult> results =
                            executeComparison(originalNumbers);
                    mainResultados.mostrarResultados(sizes[i], results);
                    mainResultados.guardarResultados(
                            writer, sizes[i], dataType, results
                    );
                    writer.flush();
                } catch (InterruptedException e) {
                    writer.println("La prueba fue interrumpida.");
                    writer.close();
                    System.out.println("Se interrumpio la ejecucion de los hilos.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            writer.close();
            System.out.println("\nResultados guardados en resultados_pruebas.txt");
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo crear el archivo de resultados.");
        }
    }

    static int readOption(Scanner scanner) {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());

                if (option >= 1 && option <= 4) {
                    return option;
                }

                System.out.print("Elige la opcion 1, 2, 3 o 4: ");
            } catch (NumberFormatException e) {
                System.out.print("Elige la opcion 1, 2, 3 o 4: ");
            }
        }
    }

    static int readGenerationOption(Scanner scanner) {
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
        return generateNumbers(amount, false);
    }

    static int[] generateNumbers(int amount, boolean restricted) {
        Random random = new Random();
        int[] numbers = new int[amount];
        int maximum = restricted ? 5 : 100000;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(maximum) + 1;
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
