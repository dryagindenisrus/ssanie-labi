public class Main {
  public static void main(String[] args) throws Exception {
    Matrix a = new Matrix(2);
    Matrix b = new Matrix(2);

    a.setElement(0, 0, 3);
    a.setElement(0, 1, 1);
    a.setElement(1, 0, 5);
    a.setElement(1, 1, 2);
    b.setElement(0, 0, 4);
    b.setElement(0, 1, 6);
    b.setElement(1, 0, 5);
    b.setElement(1, 1, 2);

    // matrix a
    System.out.println("A = " + a);
    a.doSortRows();
    System.out.println("A = " + a);
    // matrix b
    System.out.println("B = " + b);

    // multiply matrix
    System.out.println("A * B = " + a.product(b).toString());

    // sum matrix
    System.out.println("A + B = " + a.sum(b).toString());

    Matrix c = new Matrix(2);
    Matrix d = new Matrix(2);
    c.setElement(0, 0, 1);
    c.setElement(0, 1, 1);
    c.setElement(1, 0, 1);
    c.setElement(1, 1, 0);
    d.setElement(0, 0, 1);
    d.setElement(0, 1, 1);
    d.setElement(1, 0, 1);
    d.setElement(1, 1, 0);

    for (int i = 0; i < 9; i++) {
      d = d.product(c);
    }
    d.doSortRows();

    System.out.println("A ^ 10 = " + d);
  }
}
