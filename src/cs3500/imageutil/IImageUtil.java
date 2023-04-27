package cs3500.imageutil;

/**
 * This is the ImageUtil interface. This interface represents the Utility function for a PPM file.
 * This also allows for extendable to deal with other Image file types such as "PNG" and "JPEG".
 */
public interface IImageUtil {

  /**
   * Gets the height field of an image.
   * @return the height field of class.
   */
  int getHeight();

  /**
   * Gets the width field of an image.
   * @return the width field of class.
   */
  int getWidth();

  /**
   * This method is used to take in a file path then read it's contents and
   * transform the ppm image to 2D array of RGBA components.
   * @param filename is the filename to be read into the program.
   */
  void readPPM(String filename);

  /**
   * This method is used to take in a file of either type "jpg" or "png" and read the
   * contents and transform it to a 2D array of RGBA components.
   * @param filename is the filename to be read and modified.
   */
  void readFiles(String filename);

  /**
   * Finds the x and y coordinates of a pixel on a photo.
   * Uses the 2D RGBA of the ppm as a copy to avoid aliasing problems.
   * @param y the y coordinate of the pixel.
   * @param x the x coordinate of the pixel.
   * @return the x and y coordinates of the pixel.
   * @throws IllegalArgumentException if the coordinates are not in bound.
   */
  IRGBA getPixelAt(int y, int x);

  /**
   * The method is used to get the contents of all the layers photos and make it
   * into a PPM-style format.
   * @param filepath the path to be saved as.
   */
  void savePPM(String filepath);

  /**
   * The method is used to get the contents of all the layers and format the contents
   * into that of either a jpg or a png image.
   * @param filepath the path to be saved as.
   */
  void saveFile(String filepath);


}
