package custom.matrix;

public class SquareMatrix extends UsualMatrix {
    public SquareMatrix(int size) {
        super(size, size);
    }

    public SquareMatrix add(SquareMatrix matrix) {
        return (SquareMatrix) super.add(matrix);
    }

    public SquareMatrix product(SquareMatrix matrix) {
        return (SquareMatrix) super.product(matrix);
    }

    public AbstractMatrix createMatrix(int rows, int columns) {
        return new SquareMatrix(rows);
    }
}