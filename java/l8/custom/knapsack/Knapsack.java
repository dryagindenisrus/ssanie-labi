package custom.knapsack;

public class Knapsack {
    private int[] weights;
    private int[] values;
    private int capacity;

    public Knapsack(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
    }

    public int[] getWeights() {
        return weights;
    }

    public int[] getValues() {
        return values;
    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return weights.length;
    }
}
