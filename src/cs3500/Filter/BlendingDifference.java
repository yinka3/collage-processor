package cs3500.Filter;

import cs3500.ImageUtil.*;

public class BlendingDifference implements IBlending {

  @Override
  public RGBA apply(RGBA rgba1, RGBA rgba2) {
    int newRed = applyComp(rgba1.getRed(), rgba2.getRed());
    int newGreen = applyComp(rgba1.getGreen(), rgba2.getRed());
    int newBlue = applyComp(rgba1.getRed(), rgba2.getRed());
    return new RGBA(newRed, newGreen, newBlue);
  }

  private int applyComp(int comp1, int comp2) {
    return comp1 - comp2;
  }

}
