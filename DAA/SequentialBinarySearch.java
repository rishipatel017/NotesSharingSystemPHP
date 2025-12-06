import java.util.Arrays;
import java.util.Scanner;

public class SequentialBinarySearch{

    // Linear/Sequential Search
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    // Binary Search (on sorted array)
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key)
                return mid;
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    // Utility function to print array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample array
        int[] data = {23, 5, 87, 12, 19, 44, 31};
        int[] sortedData = Arrays.copyOf(data, data.length);
        Arrays.sort(sortedData); // Required for binary search

        System.out.print("Array: ");
        printArray(data);

        System.out.print("Enter element to search: ");
        int key = sc.nextInt();

        // Linear Search
        long startLinear = System.nanoTime();
        int linearIndex = linearSearch(data, key);
        long endLinear = System.nanoTime();

        // Binary Search
        long startBinary = System.nanoTime();
        int binaryIndex = binarySearch(sortedData, key);
        long endBinary = System.nanoTime();

        // Results
        System.out.println("\n--- Linear Search ---");
        if (linearIndex != -1)
            System.out.println("Element found at index: " + linearIndex);
        else
            System.out.println("Element not found.");
        System.out.println("Execution Time: " + (endLinear - startLinear) + " ns");

        System.out.println("\n--- Binary Search (Sorted Array) ---");
        if (binaryIndex != -1)
            System.out.println("Element found at index (sorted array): " + binaryIndex);
        else
            System.out.println("Element not found.");
        System.out.println("Execution Time: " + (endBinary - startBinary) + " ns");
        sc.close();
    }
}
