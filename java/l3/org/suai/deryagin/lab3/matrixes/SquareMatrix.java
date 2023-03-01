package org.suai.deryagin.lab3.matrixes;

public class SquareMatrix extends Matrix {
    public SquareMatrix(int size) {
        super(size, size);
        for (int i = 0; i < size; i++) {
            this.setElement(i, i, 1);
        }
    }

    public SquareMatrix sum(SquareMatrix b) {
        for (int i = 0; i < super.matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] += b.matrix[i][j];
            }
        }
        return this;
    }
}
