package custom.matrix;

public class MeasureTime {
    public static void measureTime(String message, Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        long time = (end - start);

        System.out.println(message + " time: " + time + " ms");
    }
}