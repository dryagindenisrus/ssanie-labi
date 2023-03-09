import custom.list.SortedIntegerList;

public class Main {
    public static void main(String[] args) {
        SortedIntegerList a = new SortedIntegerList(false);
        SortedIntegerList b = new SortedIntegerList(true);

        a.add(1);
        a.add(2);
        a.add(1);
        a.add(5);
        a.add(9);
        a.add(8);

        b.add(1);
        b.add(2);
        b.add(1);
        b.add(5);
        b.add(9);
        b.add(8);

        System.out.println(a);
        System.out.println(b);

        System.out.println(a.sum(b));
    }
}