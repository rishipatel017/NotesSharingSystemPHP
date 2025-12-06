public class BubbleSort{
    // Bubble sort function to sort array in ascending order
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        // Outer loop for each element
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Inner loop for comparing adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if left element is greater than right element
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // A swap occurred
                }
            }
            // If no swaps occurred in this pass, array is already sorted
            if (!swapped) {
                break;
            }
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

        int[] data = {64, 25, 12, 22, 11};
        // Print original array
        System.out.print("Original array: ");
        printArray(data);
        // Record the start time before sorting
        long startTime = System.nanoTime();
        // Perform bubble sort
        bubbleSort(data);
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
