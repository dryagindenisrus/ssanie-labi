import java.util.Arrays;
import java.util.Comparator;

public class Matrix {
  private final int[][] matrix;

  Matrix(int size) {
    matrix = new int[size][size];
    for (int i = 0; i < size; i++) {
      matrix[i][i] = 1;
    }
  }

  public Matrix sum(Matrix b) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        matrix[i][j] += b.matrix[i][j];
      }
    }
    return this;
  }

  public Matrix product(Matrix b) {
    Matrix res = new Matrix(matrix.length);
    for (int i = 0; i < res.matrix.length; i++) {
      res.matrix[i][i] = 0;
    }
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < b.matrix.length; j++) {
        for (int k = 0; k < b.matrix.length; k++) {
          res.matrix[i][j] += matrix[i][k] * b.matrix[k][j];
        }
      }
    }
    return res;
  }

  public void setElement(int row, int column, int value) {
    matrix[row][column] = value;
  }

  public int getElement(int row, int column) {
    return matrix[row][column];
  }

  public void doSortRows() {
    Arrays.sort(matrix, Comparator.comparingInt((int[] a) -> -Arrays.stream(a).sum()));
  }

  @Override
  public String toString() {
    return Arrays.deepToString(matrix);
  }
}