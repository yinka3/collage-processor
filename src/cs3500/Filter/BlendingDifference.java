package cs3500.Filter;

import cs3500.ImageUtil.*;

public class BlendingDifference implements IBlending {

  @Override
  public RGBA apply(RGBA rgba1, RGBA rgba2) {
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
