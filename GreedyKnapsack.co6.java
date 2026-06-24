import java.util.*;

class Item {
    int weight;
    int value;
    double ratio;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.ratio = (double) value / weight;
    }
}

public class GreedyKnapsack {

    public static void main(String[] args) {

        int[] weights = {4, 6, 8, 5, 3};
        int[] values = {20, 30, 40, 25, 15};

        int capacity = 24;

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < weights.length; i++) {
            items.add(new Item(weights[i], values[i]));
        }

        items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        int totalWeight = 0;
        int totalValue = 0;

        System.out.println("Selected Items:");

        for (Item item : items) {

            if (totalWeight + item.weight <= capacity) {
                totalWeight += item.weight;
                totalValue += item.value;

                System.out.println(
                        "Weight = " + item.weight +
                        ", Value = " + item.value);
            }
        }

        System.out.println("\nTotal Weight = " + totalWeight);
        System.out.println("Total Value = " + totalValue);
    }
}