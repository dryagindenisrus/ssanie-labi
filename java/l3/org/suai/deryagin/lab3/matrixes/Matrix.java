package org.suai.deryagin.lab3.matrixes;

import java.util.Arrays;

public class Matrix {
    final int[][] matrix;

    public Matrix(int rows, int cols) {
        matrix = new int[rows][cols];
    }

    static class MatrixError extends RuntimeException {
        MatrixError(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            System.out.println("\u001B[33m" + super.getMessage() + "\u001B[0m");
            return super.getMessage();
        }
    }

    public Matrix sum(Matrix b) {
        try {
            if (matrix[0].length != b.matrix.length || matrix.length != b.matrix[0].length) {
                throw new MatrixError("Invalid matrix in: sum(custom.matrixes.Matrix b)");
            } else {
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        matrix[i][j] += b.matrix[i][j];
                    }
                }
            }
        } catch (MatrixError e) {
            e.getMessage();
        }
        return this;
    }

    public final Matrix product(Matrix b) {
        Matrix res = new Matrix(matrix.length, b.matrix[0].length);
        try {
            if (matrix[0].length != b.matrix.length) {
                throw new MatrixError("Invalid matrix in: equals(custom.matrixes.Matrix b)");
            } else {
                int lent = res.matrix.length;
                if (res.matrix.length > res.matrix[0].length) {
                    lent = res.matrix[0].length;
                }
                for (int i=0; i < lent; i++) {
                    res.matrix[i][i] = 0;
                }

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < b.matrix[0].length; j++) {
                        for (int k = 0; k < b.matrix.length; k++) {
                            res.matrix[i][j] += matrix[i][k] * b.matrix[k][j];
                        }
                    }
                }
            }
        } catch (MatrixError e) {
            e.getMessage();
        }
        return res;
    }

    public final void setElement(int row, int column, int value) {
        matrix[row][column] = value;
    }

    public final int getElement(int row, int column) {
        return matrix[row][column];
    }

    public final boolean equals(Matrix b) {
        try {
            if (matrix[0].length != b.matrix.length || matrix.length != b.matrix[0].length) {
                throw new MatrixError("Invalid matrix in: equals(custom.matrixes.Matrix b)");
            } else {
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (matrix[i][j] != b.matrix[i][j]) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (MatrixError e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }
}
