class Int {
  private int value;

  Int() {
    value = 0;
  }

  Int(int value) {
    this.value = value;
  }

  public Int increment() {
    value++;
    return this;
  }

  public Int decrement() {
    value--;
    return this;
  }

  public Int add(Int n) {
    value += n.value;
    return this;
  }

  public Int substract(Int n) {
    value -= n.value;
    return this;
  }

  @Override
  public String toString() {
    return "" + value;
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    Int a = new Int();
    System.out.println(a.increment().toString() + a.decrement() + a + a);
  }
}
