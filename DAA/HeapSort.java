public class HeapSort{
    // Function to sort an array using heap sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        // Step 1: Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // Step 2: Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (largest) to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    // Function to maintain heap property (max-heap)
    public static void heapify(int[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1; // Left child
        int right = 2 * rootIndex + 2; // Right child
        // If left child is larger than root
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than current largest
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        // If largest is not root, swap and heapify again
        if (largest != rootIndex) {
            int swap = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(arr, heapSize, largest);
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
        int[] data = {20, 50, 30, 10, 60, 90, 70, 40, 80, 100};
        System.out.print("Original array: ");
        printArray(data);
        long startTime = System.nanoTime();
        heapSort(data);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.print("Sorted array:   ");
        printArray(data);
        System.out.println("Execution Time: " + executionTime + " nanoseconds");
    }
}
