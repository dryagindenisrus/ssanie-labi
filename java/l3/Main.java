import custom.matrixes.Matrix;
import custom.matrixes.SquareMatrix;

public class Main {
    public static void main(String[] args) {
        final Matrix a = new Matrix(2, 3);
        final Matrix b = new Matrix(3, 2);
        final SquareMatrix d = new SquareMatrix(2);
        final SquareMatrix c = new SquareMatrix(2);

        a.setElement(0, 0, 3);
        a.setElement(0, 1, 1);
        a.setElement(0, 2, 5);
        a.setElement(1, 0, 2);
        a.setElement(1, 1, 2);
        a.setElement(1, 2, 2);
        b.setElement(0, 0, 4);
        b.setElement(0, 1, 4);
        b.setElement(1, 0, 4);
        b.setElement(1, 1, 6);
        b.setElement(2, 0, 5);
        b.setElement(2, 1, 2);

        c.setElement(0, 0, 5);
        d.setElement(1, 1, 3);
        c.setElement(1, 1, 1);
        d.setElement(0, 0, 1);

        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("C = " + c);
        System.out.println("D = " + d);

        System.out.println("C + D = " + a.sum(d));

        System.out.println("C * D = " + a.product(b));
        System.out.println("C == D: " + c.equals(d));
    }
}
