public class QuickSort{
    // Function to perform partition
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose last element as pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i + 1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    // Function to perform quick sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partitioning index
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    // Utility function to print an array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }
    // Main method
    public static void main(String[] args) {
        int[] data = {34, 7, 23, 32, 5, 62, 10, 30, 50, 86};
        System.out.print("Original array: ");
        printArray(data);
        long startTime = System.nanoTime();
        quickSort(data, 0, data.length - 1);
        long endTime = System.nanoTime();
        System.out.print("Sorted array:   ");
        printArray(data);
        System.out.println("Execution Time: " + (endTime - startTime) + " nanoseconds");
    }
}
