import java.util.*;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    // Function to get maximum value in knapsack
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value-to-weight ratio (descending)
        Arrays.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        double totalValue = 0.0;
        int remainingCapacity = capacity;

        for (Item item : items) {
            if (item.weight <= remainingCapacity) {
                // Take whole item
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                // Take fraction of item
                totalValue += (double) item.value * ((double) remainingCapacity / item.weight);
                break; // knapsack full
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        // Example items
        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };

        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}
