import java.util.ArrayList;

public class shellSortArrL {

 public static void ShellSort(ArrayList<Integer> arr) {
        int n = arr.size();

        for (int gap = n / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < n; i++) {
                
                int temp = arr.get(i); 
                int j = i;

                while (j >= gap && arr.get(j - gap) > temp) {
                    arr.set(j, arr.get(j-gap));
                    j -= gap;
                }

                arr.set(j, temp);
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
