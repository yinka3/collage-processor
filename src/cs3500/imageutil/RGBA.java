package cs3500.imageutil;

/**
 * The RGBA class holds the entity of a pixel for an image,
 * it has a red, green, blue, alpha component to each pixel.
 * Alpha values are used for transparency reasons and a double instead of integer
 * due to it being a decimal value.
 */
public class RGBA implements IRGBA {

  private final int r;
  private final int g;
  private final int b;
  private final double a;


  /**
   * This is the main constructor for the RGBA class as it holds all the components
   * and the alpha values.
   * There is a limit to amount for each component, as it stops at 255.
   * @param r is the red component for the pixel.
   * @param g is the green component for the pixel.
   * @param b is the blue component for the pixel.
   * @param a is the alpha component for the pixel.
   */
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

  /**
   * This is the constructor used for needing to acquire only the red, green, and blue
   * components, alpha is automatically set to 255 for this.
   * The component values are also manually clamped between 0 and 255 with the use of the
   * IllegalArgumentException.
   * @param r is the red component of the pixel.
   * @param g is the green component of the pixel.
   * @param b is the blue component of the pixel.
   */
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

  /**
   *  This is a convenience constructor for having to return an RGBA with the use of an HSL
   *  component, a new implementation we used.
   * @param hsl is the HSL class, which is a new version of a pixel.
   */
  public RGBA(IHSL hsl) {
    IRGBA temp = RepresentationConverter.convertHSLToRGB(hsl.getHue(),
            hsl.getSaturation(), hsl.getLight(), this.getAlpha());
    this.r = clamp(temp.getRed());
    this.g = clamp(temp.getGreen());
    this.b = clamp(temp.getBlue());
    this.a = clamp((int) temp.getAlpha());
  }

  /**
   * This is a getter for the red component.
   * @return the red field of the RGBA class.
   */
  public int getRed() {
    return this.r;
  }

  /**
   * This is a getter for the blue component.
   * @return the blue field of the RGBA class.
   */
  public int getBlue() {
    return this.b;
  }

  /**
   * This is a getter for the alpha component.
   * @return the alpha field of the RGBA class.
   */
  public double getAlpha() {
    return this.a;
  }

  /**
   * This is a getter for the green component.
   * @return the green field of the RGBA class.
   */
  public int getGreen() {
    return this.g;
  }

  /**
   * This is the value method, which is used later to filter photos.
   * It gets the max value of out an RGB component.
   * @param r is the red component of the pixel.
   * @param b is the blue component of the pixel.
   * @param g is the green component of the pixel.
   * @return the maximum value of each RGB component.
   */
  public int value(int r, int b, int g) {
    return Math.max(Math.max(r, b), g);
  }


  /**
   * This is the luma value method, which is used later to filter photos.
   * It multiplies each RGB component by certain values.
   * @param r is the red component of the pixel.
   * @param b is the blue component of the pixel.
   * @param g is the green component of the pixel.
   * @return the sum of RGB components modified to certain parameters.
   */
  public double luma(int r, int b, int g) {
    return 0.2126 * r + 0.7152 * g + 0.0722 * b;
  }

  /**
   * This is the intensity value method, which is used later to filter photos.
   * @param r is the red component of the pixel.
   * @param b is the blue component of the pixel.
   * @param g is the green component of the pixel.
   * @return an int number of which is takes the average of the RGB components.
   */
  public int intensity(int r, int b, int g) {
    return (r + b + g) / 3;
  }

  /**
   * The clamp method is used to make sure when calculates are made with RGBA components, they
   * stay between 0 and 255.
   * @param value the value compare for clamping.
   * @return value based on how the value is clamped.
   */
  public static int clamp(int value) {
    if (value > 255) {
      value = 255;
    }
    if (value < 0) {
      value = 0;
    }
    return value;
  }

  /**
   * This method is an algorithm used for allowing transparency between a current layer and layer
   * previous to the current layer, specifically RGBA components using the alpha component.
   * @param bg is the background image that will be used transparency.
   * @return a modified RGBA component to show the change.
   */
  public IRGBA transparency(IRGBA bg) {
    double a_2 = ((this.a / 255) + ((bg.getAlpha() / 255) * (1 - (this.a / 255))));
    if (a_2 == 0.0) {
      return this.copy();
    }
    int r = (int) (((((this.a / 255) * this.r) + (((bg.getAlpha() / 255) * bg.getRed())
            * (1 - (this.a / 255))))) * (1 / a_2));
    int g = (int) (((((this.a / 255) * this.g) + (((bg.getAlpha() / 255) * bg.getGreen())
            * (1 - (this.a / 255))))) * (1 / a_2));
    int b = (int) (int) (((((this.a / 255) * this.b) + (((bg.getAlpha() / 255) * bg.getBlue())
            * (1 - (this.a / 255))))) * (1 / a_2));
    return new RGBA(r, g, b,(a_2 * 255));
  }

  /**
   * Returns the deep copy of an RGBA object, while avoiding aliasing issues.
   * @return a copy of the RGBA object of the red, green, blue, and alpha values.
   */
  public IRGBA copy() {
    return new RGBA(this.r, this.g, this.b, this.a);
  }

  /**
   * The method is a to String override meant for this class.
   * @return a string representation of the RGBA object of just the red, green, and blue values.
   */
  @Override
  public String toString() {
    return this.r + " " + this.g + " " + this.b;
  }
}
