package cs3500.Filter;

import cs3500.ImageUtil.*;


public class BlendingDarken implements IBlending {
  @Override
  public RGBA apply(RGBA rgba1, RGBA rgba2) {
    HSL HSL1 = RepresentationConverter.convertRGBtoHSL(rgba1.getRed(), rgba1.getBlue(), rgba1.getGreen());
    HSL HSL2 = RepresentationConverter.convertRGBtoHSL(rgba2.getRed(), rgba2.getBlue(), rgba2.getGreen());
    double newLight = HSL1.getLight() * HSL2.getLight();
    HSL newHSL = RepresentationConverter.convertRGBtoHSL(HSL1.getHue(), HSL1.getSaturation(), newLight);
    return RepresentationConverter.convertHSLtoRGB(newHSL.getHue(), newHSL.getSaturation(), newHSL.getLight());
  }

  @Override
  public String toString() {
    return "BlendingDarken";
  }

}
