class Int {
  private int value;

  Int() {
    value = 0;
  }

  Int(int value) {
    this.value = value;
  }

  public void increment() {
    value++;
  }

  public void decrement() {
    value--;
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
    return Integer.toString(value);
  }
}
