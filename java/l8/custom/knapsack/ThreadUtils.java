package custom.knapsack;

public class ThreadUtils {
    public static final int NUM_THREADS = 4;

    public static void runThreads(Knapsack knapsack, int[] solutions) {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new KnapsackSolver(knapsack, solutions, i));
            threads[i].start();
        }
        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
