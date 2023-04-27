package cs3500.imageutil;

/**
 * The RGBA class represents a pixel for an image, it has a red, green, blue, alpha component
 * to each pixel. This is the building block for a layer.
 */
public interface IRGBA {
  /**
   * The method is a toString override meant for this class.
   * @return a string representation of the RGBA object of just the red, green, and blue values.
   */
  String toString();

  /**
   * Returns the deep copy of an RGBA object, while avoiding aliasing issues.
   * @return a copy of the RGBA object of the red, green, blue, and alpha values.
   */
  IRGBA copy();

  /**
   * This method is an algorithm used for allowing transparency between a current layer and layer
   * previous to the current layer, specifically RGBA components using the alpha component.
   * @param bg is the background image that will be used transparency.
   * @return a modified RGBA component to show the change.
   */
  IRGBA transparency(IRGBA bg);

  /**
   * This is the intensity value method, which is used later to filter photos.
   * @param r is the red component of the pixel.
   * @param b is the blue component of the pixel.
   * @param g is the green component of the pixel.
   * @return an int number of which is takes the average of the RGB components.
   */
  int intensity(int r, int b, int g);

  /**
   * This is the luma value method, which is used later to filter photos.
   * It multiplies each RGB component by certain values.
   * @param r is the red component of the pixel.
   * @param b is the blue component of the pixel.
   * @param g is the green component of the pixel.
   * @return the sum of RGB components modified to certain parameters.
   */
  double luma(int r, int b, int g);

  /**
   * This is the value method, which is used later to filter photos.
   * It gets the max value of out an RGB component.
   * @param r is the red component of the pixel.
   * @param b is the blue component of the pixel.
   * @param g is the green component of the pixel.
   * @return the maximum value of each RGB component.
   */
  int value(int r, int b, int g);

  /**
   * This is a getter for the red component.
   * @return the red field of the RGBA class.
   */
  int getRed();

  /**
   * This is a getter for the green component.
   * @return the green field of the RGBA class.
   */
  int getGreen();

  /**
   * This is a getter for the blue component.
   * @return the blue field of the RGBA class.
   */
  int getBlue();

  /**
   * This is a getter for the alpha.
   * @return the alpha field of the RGBA class.
   */
  double getAlpha();
}
