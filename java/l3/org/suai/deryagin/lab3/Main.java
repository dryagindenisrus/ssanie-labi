package org.suai.deryagin.lab3;

import org.suai.deryagin.lab3.matrixes.Matrix;
import org.suai.deryagin.lab3.matrixes.SquareMatrix;
import org.suai.deryagin.lab3.matrixes.AllEqualMatrix;

public class Main {
    public static void main(String[] args) {
        final Matrix a = new Matrix(2, 3);
        final Matrix b = new Matrix(3, 2);
        final SquareMatrix d = new SquareMatrix(2);
        final SquareMatrix c = new SquareMatrix(2);
        final AllEqualMatrix f = new AllEqualMatrix(3, 4);
        final AllEqualMatrix g = new AllEqualMatrix(4, 3);
        g.setElement(2);
        f.setElement(4);
        System.out.println(f.product(g));
        g.getElement(5, 5);

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
