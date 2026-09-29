import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class SelectionSort {

    public static void sortArray(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int smallestPosition = i;

            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[smallestPosition]) {
                    smallestPosition = j;
                }
            }

            if (smallestPosition != i) {
                int temp = numbers[i];
                numbers[i] = numbers[smallestPosition];
                numbers[smallestPosition] = temp;
            }
        }
    }

    public static void sortArrayList(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            int smallestPosition = i;

            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(j) < numbers.get(smallestPosition)) {
                    smallestPosition = j;
                }
            }

            if (smallestPosition != i) {
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(smallestPosition));
                numbers.set(smallestPosition, temp);
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
                    "Selection Sort", "Arreglo", milliseconds, isSorted(numbers)
            );
            results.put("Selection Sort - Arreglo", result);
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
                    "Selection Sort", "ArrayList", milliseconds, isSorted(numbers)
            );
            results.put("Selection Sort - ArrayList", result);
        }
    }
}
