package squarematrix;

import java.util.Arrays;

public class SquareMatrix {
  private int[][] matrix;

  SquareMatrix(int size) {
    matrix = new int[size][size];
    for (int i = 0; i < size; i++) {
      matrix[i][i] = 1;
    }
  }

  public SquareMatrix sum(SquareMatrix b) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        matrix[i][j] += b.matrix[i][j];
      }
    }
    return this;
  }

  public SquareMatrix product(SquareMatrix b) {
    SquareMatrix res = new SquareMatrix(matrix.length);
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

  @Override
  public String toString() {
    return Arrays.deepToString(matrix);
  }
}
