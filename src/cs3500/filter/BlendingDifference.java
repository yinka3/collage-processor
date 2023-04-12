package cs3500.filter;

import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;

/**
 * This filter is used to take the layers underneath a certain layer and have a composite image to
 * apply this filter to. The effect gives an inversion effect to the layers.
 * It implements the IBlending interface that gives the main method of applying this to two
 * different rgba components.
 */
public class BlendingDifference implements IBlending {

  @Override
  public IRGBA apply(IRGBA rgba1, IRGBA rgba2) {
    int newRed = Math.abs(rgba1.getRed() - rgba2.getRed());
    int newGreen = Math.abs(rgba1.getGreen() - rgba2.getGreen());
    int newBlue = Math.abs(rgba1.getBlue() - rgba2.getBlue());
    return new RGBA(newRed, newGreen, newBlue, rgba1.getAlpha());
  }

  @Override
  public String toString() {
    return "BlendingDifference";
  }
}
