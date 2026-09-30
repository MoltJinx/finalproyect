import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class TimeLimitThread extends Thread {

    private String algorithm;
    private String structure;
    private int[] originalNumbers;
    private int seconds;
    private int target;
    private ConcurrentHashMap<String, TimeLimitResult> results;

    public TimeLimitThread(String algorithm, String structure, int[] originalNumbers,
            int seconds, ConcurrentHashMap<String, TimeLimitResult> results) {
        this.algorithm = algorithm;
        this.structure = structure;
        this.originalNumbers = originalNumbers;
        this.seconds = seconds;
        this.target = 0;
        this.results = results;
    }

    public TimeLimitThread(String algorithm, String structure, int[] originalNumbers,
            int seconds, int target,
            ConcurrentHashMap<String, TimeLimitResult> results) {
        this.algorithm = algorithm;
        this.structure = structure;
        this.originalNumbers = originalNumbers;
        this.seconds = seconds;
        this.target = target;
        this.results = results;
    }

    @Override
    public void run() {
        long endTime = System.nanoTime() + (long) seconds * 1000000000L;
        long totalSortTime = 0;
        int completed = 0;
        boolean sorted = true;

        while (System.nanoTime() < endTime && sorted
                && (target == 0 || completed < target)) {
            long start;
            long end;

            if (structure.equals("Arreglo")) {
                int[] numbers = Arrays.copyOf(originalNumbers, originalNumbers.length);
                start = System.nanoTime();
                sortArray(numbers);
                end = System.nanoTime();
                sorted = mainResultados.isSorted(numbers);
            } else {
                ArrayList<Integer> numbers = convertToArrayList();
                start = System.nanoTime();
                sortArrayList(numbers);
                end = System.nanoTime();
                sorted = mainResultados.isSorted(numbers);
            }

            if (sorted && end <= endTime) {
                totalSortTime += end - start;
                completed++;
            } else if (end > endTime) {
                break;
            }
        }

        double averageTime = 0;
        if (completed > 0) {
            averageTime = (totalSortTime / 1000000.0) / completed;
        }

        TimeLimitResult result = new TimeLimitResult(
                algorithm, structure, completed, averageTime, sorted
        );
        results.put(algorithm + " - " + structure, result);
    }

    private void sortArray(int[] numbers) {
        if (algorithm.equals("Bubble Sort")) {
            BubbleSort.sortArray(numbers);
        } else if (algorithm.equals("Selection Sort")) {
            SelectionSort.sortArray(numbers);
        } else if (algorithm.equals("Insertion Sort")) {
            InsertionSortArray.ordenar(numbers);
        } else if (algorithm.equals("Quick Sort")) {
            quickSortArr.QuickSort(numbers, 0, numbers.length - 1);
        } else if (algorithm.equals("Shell Sort")) {
            shellSortArr.ShellSort(numbers);
        } else if (algorithm.equals("Merge Sort")) {
            MergeSortArray.ordenar(numbers);
        }
    }

    private void sortArrayList(ArrayList<Integer> numbers) {
        if (algorithm.equals("Bubble Sort")) {
            BubbleSort.sortArrayList(numbers);
        } else if (algorithm.equals("Selection Sort")) {
            SelectionSort.sortArrayList(numbers);
        } else if (algorithm.equals("Insertion Sort")) {
            InsertionSortArrayL.ordenar(numbers);
        } else if (algorithm.equals("Quick Sort")) {
            quickSortArrL.QuickSort(numbers, 0, numbers.size() - 1);
        } else if (algorithm.equals("Shell Sort")) {
            shellSortArrL.ShellSort(numbers);
        } else if (algorithm.equals("Merge Sort")) {
            MergeSortArrayL.ordenar(numbers);
        }
    }

    private ArrayList<Integer> convertToArrayList() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < originalNumbers.length; i++) {
            list.add(originalNumbers[i]);
        }

        return list;
    }
}
