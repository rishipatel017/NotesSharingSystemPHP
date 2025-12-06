public class SelectionSort {
    // Function to perform selection sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        // Outer loop for each element
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // Find the minimum element in unsorted array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    // Function to print the array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        // Define a sample array
        int[] data = {29, 10, 14, 37, 13, 38, 20, 30, 29};

        // Print original array
        System.out.print("Original array: ");
        printArray(data);
        // Record the start time before sorting
        long startTime = System.nanoTime();
        // Perform selection sort
        selectionSort(data);
        // Record the end time after sorting
        long endTime = System.nanoTime();
        // Calculate execution time in nanoseconds
        long executionTime = endTime - startTime;
        // Print the sorted array
        System.out.print("Sorted array:   ");
        printArray(data);
        // Print execution time
        System.out.println("Execution Time: " + executionTime + " nanoseconds");
    }
}
