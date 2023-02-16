// Эллипс класс (метод isInside() Zoom(int factor) - фигура увеличивается на
// сколько то)
public class Main {
  public static void main(String[] args) throws Exception {
    Int a = new Int();
    Int b = new Int();
    a.increment();
    a.increment();
    b.add(a);

    for (int i = 0; i < 9; i++) {
      b.add(b);
    }
    for (int i = 0; i < 24; i++) {
      b.decrement();
    }

    System.out.println(b.toString());

    Ellipse ad = new Ellipse(2, 2, 0, 0);
    ad.zoom(2);
    boolean isInside = ad.isInside(2, 2);
    System.out.println(isInside);

  }
}
