public class RGBA {

  private int r;
  private int g;
  private int b;

  private int a;

  public RGBA(int r, int g, int b, int a) {

    if (r < 0) {
      this.r = 0;
    }
    if (r > 255) {
      this.r = 255;
    }
    if (b < 0) {
      this.b = 0;
    }
    if (b > 255) {
      this.b = 255;
    }
    if (g < 0) {
      this.g = 0;
    }
    if (g > 255) {
      this.g = 255;
    }
    if (a < 0) {
      this.a = 0;
    }
    if (a > 255) {
      this.a = 255;
    }else {
      this.r = r;
      this.g = g;
      this.b = b;
      this.a = a;
    }
  }

  public RGBA(int r, int g, int b) {

    if (r < 0) {
      this.r = 0;
    }
    if (r > 255) {
      this.r = 255;
    }
    if (b < 0) {
      this.b = 0;
    }
    if (b > 255) {
      this.b = 255;
    }
    if (g < 0) {
      this.g = 0;
    }
    if (g > 255) {
      this.g = 255;
    } else {
      this.r = r;
      this.g = g;
      this.b = b;
    }
  }




  /**
   * Returns the red-component of a pixel.
   *
   * @return the red-component of a pixel.
   */
  public int getRed() {
    return this.r;
  }

  /**
   * Returns the blue-component of a pixel.
   *
   * @return the blue-component of a pixel.
   */
  public int getBlue() {
    return this.b;
  }

  public int getAlpha() { return this.a; }

  /**
   * Returns the green-component of a pixel.
   *
   * @return the green-component of a pixel.
   */
  public int getGreen() {
    return this.g;
  }

  // sets the red-component to the value provided
  public int setRed(int newValue) {
    return this.r = newValue;
  }

  // sets the blue-component to the value provided
  public int setBlue(int newValue) {
    return this.b = newValue;
  }

  // sets the green-component to the value provided
  public int setGreen(int newValue) {
    return this.g = newValue;
  }


  public int setAlpha(int newValue) {return this.a = newValue;}

  // returns the max value from the 3 integers given
  public int value(int r, int b, int g) {
    return Math.max(Math.max(r, b), g);

  }

  // returns the double of the weighted sum of 0.2126 * r + 0.7152 * g +0.0722 * b
  public double luma(int r, int b, int g) {
    return 0.2126 * r + 0.7152 * g + 0.0722 * b;
  }

  // returns the average of the 3 integers provided
  public int intensity(int r, int b, int g) {
    return (r + b + g) / 3;
  }


  int clamp(int value) {
    if (value > 255) {
      value = 255;
    }
    if (value < 0) {
      value = 0;
    }
    return value;
  }


  public RGBA transparency(RGBA bg) {
    int a_2 = (int) (this.a / 225 + bg.a / 225 * (1 - (this.a / 225)));
    int a_1 = a_2 * 255;
    int r = (int) ((this.a / 225 * this.r + bg.r * (bg.a / 225)
            * (1 - this.a / 225)) * (1 / a_2));
    int g = (int) ((this.a / 225 * this.g + bg.g * (bg.a / 225)
            * (1 - this.a / 225)) * (1 / a_2));
    int b = (int) ((this.a / 225 * this.b + bg.b * (bg.a / 225)
            * (1 - this.a / 225)) * (1 / a_2));
    return new RGBA(r, g, b, a_1);
  }

  public RGBA copy() {
    return new RGBA(this.r, this.g, this.b, this.a);
  }
}
