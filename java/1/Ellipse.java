public class Ellipse {
  private int radius1;
  private int radius2;
  private int x0;
  private int y0;

  Ellipse(int radius1, int radius2, int x0, int y0) {
    this.radius1 = radius1;
    this.radius2 = radius2;
    this.x0 = x0;
    this.y0 = y0;
  }

  public boolean isInside(int x, int y) {
    x -= this.x0;
    y -= this.y0;
    return ((x * x) / (radius1 * radius1) + (y * y) / (radius2 * radius2) <= 1.0);
  }

  public void zoom(int fraction) {
    this.radius1 = this.radius1 * fraction;
    this.radius2 = this.radius2 * fraction;
  }
}
