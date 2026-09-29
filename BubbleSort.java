import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class BubbleSort {

    public static void sortArray(int[] numbers) {
        boolean changed;

        for (int i = 0; i < numbers.length - 1; i++) {
            changed = false;

            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }
    }

    public static void sortArrayList(ArrayList<Integer> numbers) {
        boolean changed;

        for (int i = 0; i < numbers.size() - 1; i++) {
            changed = false;

            for (int j = 0; j < numbers.size() - 1 - i; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j + 1));
                    numbers.set(j + 1, temp);
                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }
    }

    public static boolean isSorted(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static class ArrayThread extends Thread {

        private int[] numbers;
        private ConcurrentHashMap<String, SortResult> results;

        public ArrayThread(int[] numbers, ConcurrentHashMap<String, SortResult> results) {
            this.numbers = numbers;
            this.results = results;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            sortArray(numbers);
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1000000.0;
            SortResult result = new SortResult(
                    "Bubble Sort", "Arreglo", milliseconds, isSorted(numbers)
            );
            results.put("Bubble Sort - Arreglo", result);
        }
    }

    public static class ArrayListThread extends Thread {

        private ArrayList<Integer> numbers;
        private ConcurrentHashMap<String, SortResult> results;

        public ArrayListThread(ArrayList<Integer> numbers,
                ConcurrentHashMap<String, SortResult> results) {
            this.numbers = numbers;
            this.results = results;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            sortArrayList(numbers);
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1000000.0;
            SortResult result = new SortResult(
                    "Bubble Sort", "ArrayList", milliseconds, isSorted(numbers)
            );
            results.put("Bubble Sort - ArrayList", result);
        }
    }
}
