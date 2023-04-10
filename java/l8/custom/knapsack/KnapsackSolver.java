package custom.knapsack;

public class KnapsackSolver implements Runnable {
    private final Knapsack knapsack;
    private final int[] solutions;
    private final int threadNum;

    public KnapsackSolver(Knapsack knapsack, int[] solutions, int threadNum) {
        this.knapsack = knapsack;
        this.solutions = solutions;
        this.threadNum = threadNum;
    }

    public void run() {
        for (int i = threadNum; i < knapsack.size(); i += ThreadUtils.NUM_THREADS) {
            int weight = knapsack.getWeights()[i];
            int value = knapsack.getValues()[i];
            for (int j = knapsack.getCapacity(); j >= weight; j--) {
                int prevSolution = (i == 0) ? 0 : solutions[j];
                int newSolution = solutions[j - weight] + value;
                solutions[j] = Math.max(prevSolution, newSolution);
            }
        }
    }
}
