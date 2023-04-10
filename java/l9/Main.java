import custom.people.People;

public class Main {
    static final People a = new People(14, "white", "CJ");
    static final People b = new People(14, "nigger", "Antoha");

    public static void main(String[] args) {
        ThreadDemo1 T1 = new ThreadDemo1();
        ThreadDemo2 T2 = new ThreadDemo2();
        T1.start();
        T2.start();
    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (a) {
                a.yearLater();
                try {
                    ThreadDemo1.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Thread can not work");
                }
                synchronized (b) {
                    a.soitie(b);
                }
            }
        }
    }
    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (b) {
                b.yearLater();
                try {
                    ThreadDemo2.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Thread can not work");
                }
                synchronized (a) {
                    b.soitie(a);
                }
            }
        }
    }
}