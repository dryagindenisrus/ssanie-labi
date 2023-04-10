import custom.matrix.UsualMatrix;
import custom.matrix.ParallelMatrixProduct;
import custom.matrix.MeasureTime;
import custom.knapsack.*;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        Knapsack knapsack = new Knapsack(weights, values, capacity);
        int[] solutions = new int[capacity + 1];
        ThreadUtils.runThreads(knapsack, solutions);
        System.out.println("The maximum value that can be put in a knapsack of capacity " + capacity + " is " + solutions[capacity]);
    }

}


//    public static void main(String[] args) {
//        UsualMatrix a = new UsualMatrix(1000, 1000);
//        UsualMatrix b = new UsualMatrix(1000, 1000);
//        UsualMatrix c = new UsualMatrix(1000, 1000);
//        UsualMatrix d = new UsualMatrix(1000, 1000);
//
//        MeasureTime.measureTime("Linear product", () -> {
//            UsualMatrix resLinear = c.product(d);
//        });
//
//        ParallelMatrixProduct cd = new ParallelMatrixProduct(128);
//
//        MeasureTime.measureTime("Thread product", () -> {
//            UsualMatrix resThreads = cd.product(a, b);
//        });
//
//        System.out.println("resThreads == resLinear: " + a.equals(b));
//    }
