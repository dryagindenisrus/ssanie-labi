package org.suai.deryagin.lab3.matrixes;

public class AllEqualMatrix {
    private int value;
    private final int cols;
    private final int rows;

    public AllEqualMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    static class MatrixSizeError extends Exception {
        MatrixSizeError(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            System.out.println("\u001B[33m" + super.getMessage() + "\u001B[0m");
            return super.getMessage();
        }
    }

    static class MatrixGetError extends Exception {
        MatrixGetError(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            System.out.println("\u001B[31m" + super.getMessage() + "\u001B[0m");
            return super.getMessage();
        }
    }

    public AllEqualMatrix sum(AllEqualMatrix b) {
        try {
            if (rows != b.rows || cols != b.cols) {
                throw new MatrixSizeError("Invalid matrix in: sum(custom.matrixes.Matrix b)");
            } else {
                value += b.value;
            }
        } catch (MatrixSizeError e) {
            e.getMessage();
        }
        return this;
    }

    public final AllEqualMatrix product(AllEqualMatrix b) {
        AllEqualMatrix res = new AllEqualMatrix(rows, b.cols);
        try {
            if (rows != b.cols) {
                throw new MatrixSizeError("Invalid matrix in: equals(custom.matrixes.Matrix b)");
            } else {
                res.value = this.cols * this.value * b.value;
            }
        } catch (MatrixSizeError e) {
            e.getMessage();
        }
        return res;
    }

    public final void setElement(int value) {
        this.value = value;
    }

    public final int getElement(int row, int column) {

        try {
            if (row > rows || column > cols) {
                throw new MatrixGetError("No matrix elem in: getElement(custom.matrixes.Matrix b)");
            } else {
                return value;
            }
        } catch (MatrixGetError e) {
            e.getMessage();
        }
        return 0;
    }

    public final boolean equals(AllEqualMatrix b) {
        return ((value == b.value) && (cols == b.cols) && (rows == b.rows));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i< rows; i++) {
            for (int j=0; j<cols; j++) {
                result.append(value).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}