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
