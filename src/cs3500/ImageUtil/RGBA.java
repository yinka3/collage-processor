package cs3500.ImageUtil;

public class RGBA {

  private final int r;
  private final int g;
  private final int b;
  private double a;



  public RGBA(int r, int g, int b, double a) {
    if (r > 255 || g > 255 || b > 255
            || r < 0 || g < 0 || b < 0 || a < 0) {
      throw new IllegalArgumentException("invalid pixel");
    }
      this.r = r;
      this.g = g;
      this.b = b;
      this.a = a;
    }


  public RGBA(int r, int g, int b) {
    if (r > 255 || g > 255 || b > 255
            || r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("invalid pixel");
    }
      this.r = r;
      this.g = g;
      this.b = b;
      this.a = 255;
    }


  public int getRed() {
    return this.r;
  }


  public int getBlue() {
    return this.b;
  }

  public double getAlpha() { return this.a; }


  public int getGreen() {
    return this.g;
  }


  public void setAlpha(double newValue) {
    this.a = newValue;
  }


  public int value(int r, int b, int g) {
    return Math.max(Math.max(r, b), g);

  }


  public double luma(int r, int b, int g) {
    return 0.2126 * r + 0.7152 * g + 0.0722 * b;
  }


  public int intensity(int r, int b, int g) {
    return (r + b + g) / 3;
  }


  public static int clamp(int value) {
    if (value > 255) {
      value = 255;
    }
    if (value < 0) {
      value = 0;
    }
    return value;
  }


  public RGBA transparency(RGBA bg) {
    double a_2 = ((this.a / 255) + ((bg.a / 255) * (1 - (this.a / 255))));
    if (a_2 == 0.0) {
      return this.copy();
    }
    int r = (int) (((((this.a / 255) * this.r) + (((bg.a / 255) * bg.r)
            * (1 - (this.a / 255))))) * (1 / a_2));
    int g = (int) (((((this.a / 255) * this.g) + (((bg.a / 255) * bg.g)
            * (1 - (this.a / 255))))) * (1 / a_2));
    int b = (int) (int) (((((this.a / 255) * this.b) + (((bg.a / 255) * bg.b)
            * (1 - (this.a / 255))))) * (1 / a_2));
    return new RGBA(r, g, b,(a_2 * 255));
  }

  public RGBA copy() {
    return new RGBA(this.r, this.g, this.b, this.a);
  }


  @Override
  public String toString() {
    return this.r + " " + this.g + " " + this.b;
  }
}
