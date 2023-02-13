import java.util.Arrays;

class Matrix {
  private int[][] matrix;

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

  @Override
  public String toString() {
    return Arrays.deepToString(matrix);
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    Matrix a = new Matrix(2);
    Matrix b = new Matrix(2);

    a.setElement(0, 0, 5);
    a.setElement(0, 1, 2);
    a.setElement(1, 0, 3);
    a.setElement(1, 1, 1);
    b.setElement(0, 0, 4);
    b.setElement(0, 1, 6);
    b.setElement(1, 0, 5);
    b.setElement(1, 1, 2);

    // matrix a
    System.out.println("A = " + a.toString());
    // matrix b
    System.out.println("B = " + b.toString());

    // multyply matrix
    System.out.println("A * B = " + a.product(b).toString());

    // summ matrix
    System.out.println("A + B = " + a.sum(b).toString());
  }
}
